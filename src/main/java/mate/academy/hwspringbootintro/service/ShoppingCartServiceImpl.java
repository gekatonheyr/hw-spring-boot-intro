package mate.academy.hwspringbootintro.service;

import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.dto.shoppingcart.CartItemRequestDto;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository cartRepository;
    private final BookRepository bookRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    public ShoppingCartDto getAllCartItems(Long currentUserId) {
        ShoppingCart shoppingCart = cartRepository
                .findShoppingCartByUserId(currentUserId)
                        .orElseThrow(()
                                -> new EntityNotFoundException("Shopping cart not found for user "
                                + "with ID: " + currentUserId));
        return shoppingCartMapper.toShoppingCartDto(shoppingCart);
    }

    @Override
    public void deleteItemsFromShoppingCart(Long cartItemId, Long currentUserId) {
        ShoppingCart shoppingCart = cartRepository.findShoppingCartByUserId(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping cart not found for user "
                        + currentUserId));
        shoppingCart.getCartItems().removeIf(cartItem -> cartItem.getId().equals(cartItemId));
        cartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCartDto updateItemQuantity(Long cartItemId,
                                              CartItemUpdateRequestDto updatedCartItem,
                                              Long currentUserId) {
        ShoppingCart shoppingCart = cartRepository.findShoppingCartByUserId(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping cart not found for user "
                        + currentUserId));
        shoppingCart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .ifPresent(item -> item.setQuantity(updatedCartItem.quantity()));
        cartRepository.save(shoppingCart);
        return getAllCartItems(currentUserId);
    }

    @Override
    public ShoppingCartDto addItemsToShoppingCart(CartItemRequestDto cartItemRequestDto,
                                                  Long currentUserId) {
        ShoppingCart shoppingCart = cartRepository
                .findShoppingCartByUserId(currentUserId)
                        .orElseThrow(()
                                -> new EntityNotFoundException("Shopping cart not found for user "
                        + "with ID: " + currentUserId));
        Set<CartItem> presentCartItems = shoppingCart.getCartItems();
        Optional<CartItem> foundBook = presentCartItems.stream()
                .filter(item -> item.getBook().getId().equals(cartItemRequestDto.bookId()))
                .findFirst();
        if (foundBook.isPresent()) {
            foundBook.get()
                    .setQuantity(foundBook.get().getQuantity() + cartItemRequestDto.quantity());
            cartRepository.save(shoppingCart);
            return getAllCartItems(currentUserId);
        }
        Book book = bookRepository.findById(cartItemRequestDto.bookId()).orElseThrow(()
                        -> new EntityNotFoundException("Book not found by given ID "
                        + cartItemRequestDto.bookId()));
        CartItem addedBook = new CartItem();
        addedBook.setBook(book);
        addedBook.setQuantity(cartItemRequestDto.quantity());
        addedBook.setShoppingCart(shoppingCart);
        presentCartItems.add(addedBook);
        cartRepository.save(shoppingCart);
        return getAllCartItems(currentUserId);
    }

    @Override
    public void createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        cartRepository.save(shoppingCart);
    }
}
