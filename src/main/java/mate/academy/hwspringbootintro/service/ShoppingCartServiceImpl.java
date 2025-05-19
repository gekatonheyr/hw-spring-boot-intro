package mate.academy.hwspringbootintro.service;

import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.dto.shoppingcart.CartItemRequestDto;
import mate.academy.hwspringbootintro.dto.shoppingcart.CartItemResponseDto;
import mate.academy.hwspringbootintro.dto.shoppingcart.CartItemUpdateRequestDto;
import mate.academy.hwspringbootintro.dto.shoppingcart.ShoppingCartDto;
import mate.academy.hwspringbootintro.exception.EntityNotFoundException;
import mate.academy.hwspringbootintro.mapper.CartItemMapper;
import mate.academy.hwspringbootintro.mapper.ShoppingCartMapper;
import mate.academy.hwspringbootintro.model.Book;
import mate.academy.hwspringbootintro.model.CartItem;
import mate.academy.hwspringbootintro.model.ShoppingCart;
import mate.academy.hwspringbootintro.model.User;
import mate.academy.hwspringbootintro.repository.book.BookRepository;
import mate.academy.hwspringbootintro.repository.shoppingcart.CartItemRepository;
import mate.academy.hwspringbootintro.repository.shoppingcart.ShoppingCartRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemMapper cartItemMapper;
    private final BookRepository bookRepository;

    @Override
    public ShoppingCartDto getAllCartItems() {
        ShoppingCart shoppingCart = cartRepository
                .findShoppingCartByUserId(getCurrentUser()
                        .getId())
                        .orElseThrow(()
                                -> new EntityNotFoundException("Shopping cart not found for user "
                                + "with ID: " + getCurrentUser().getId()));
        return shoppingCartMapper.toShoppingCartDto(shoppingCart);
    }

    @Override
    public void deleteItemsFromShoppingCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public CartItemResponseDto updateItemQuantity(Long cartItemId,
                                                  CartItemUpdateRequestDto updatedCartItem) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(()
                -> new EntityNotFoundException("Cart item not found by given ID " + cartItemId));
        cartItemMapper.updateEntity(cartItem, updatedCartItem);
        return cartItemMapper.toDto(cartItemRepository.save(cartItem));
    }

    @Override
    public CartItemResponseDto addItemsToShoppingCart(CartItemRequestDto cartItemRequestDto) {
        ShoppingCart shoppingCart = cartRepository
                .findShoppingCartByUserId(getCurrentUser()
                        .getId())
                        .orElseThrow(()
                                -> new EntityNotFoundException("Shopping cart not found for user "
                        + "with ID: " + getCurrentUser().getId()));
        Book book = bookRepository.findById(cartItemRequestDto.bookId()).orElseThrow(()
                        -> new EntityNotFoundException("Book not found by given ID "
                        + cartItemRequestDto.bookId()));
        return cartItemMapper.toDto(cartItemRepository.save(new CartItem(shoppingCart,
                book,
                cartItemRequestDto.quantity())));
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
