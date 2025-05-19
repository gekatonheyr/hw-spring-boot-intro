package mate.academy.hwspringbootintro.service;

import mate.academy.hwspringbootintro.dto.shoppingcart.CartItemRequestDto;
import mate.academy.hwspringbootintro.dto.shoppingcart.CartItemResponseDto;
import mate.academy.hwspringbootintro.dto.shoppingcart.CartItemUpdateRequestDto;
import mate.academy.hwspringbootintro.dto.shoppingcart.ShoppingCartDto;

public interface ShoppingCartService {
    ShoppingCartDto getAllCartItems();

    CartItemResponseDto addItemsToShoppingCart(CartItemRequestDto cartItemRequestDto);

    CartItemResponseDto updateItemQuantity(Long cartItemId, CartItemUpdateRequestDto cartItem);

    void deleteItemsFromShoppingCart(Long cartItemId);
}
