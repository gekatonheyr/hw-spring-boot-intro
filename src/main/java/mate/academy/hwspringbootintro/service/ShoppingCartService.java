package mate.academy.hwspringbootintro.service;

import mate.academy.hwspringbootintro.dto.shoppingcart.CartItemRequestDto;
import mate.academy.hwspringbootintro.dto.shoppingcart.CartItemResponseDto;
import mate.academy.hwspringbootintro.dto.shoppingcart.CartItemUpdateRequestDto;
import mate.academy.hwspringbootintro.dto.shoppingcart.ShoppingCartDto;
import org.springframework.data.domain.Pageable;

public interface ShoppingCartService {
    ShoppingCartDto getAllCartItems(Pageable pageable);

    CartItemResponseDto addItemsToShoppingCart(CartItemRequestDto cartItemRequestDto);

    CartItemResponseDto updateItemQuantity(Long cartItemId, CartItemUpdateRequestDto cartItem);

    void deleteItemsFromShoppingCart(Long cartItemId);
}
