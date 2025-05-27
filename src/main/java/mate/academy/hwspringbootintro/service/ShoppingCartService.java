package mate.academy.hwspringbootintro.service;

import mate.academy.hwspringbootintro.dto.shoppingcart.CartItemRequestDto;
import mate.academy.hwspringbootintro.dto.shoppingcart.CartItemUpdateRequestDto;
import mate.academy.hwspringbootintro.dto.shoppingcart.ShoppingCartDto;
import mate.academy.hwspringbootintro.model.ShoppingCart;
import mate.academy.hwspringbootintro.model.User;

public interface ShoppingCartService {
    ShoppingCartDto getAllCartItems(Long currentUserId);

    ShoppingCartDto addItemsToShoppingCart(CartItemRequestDto cartItemRequestDto,
                                           Long currentUserId);

    ShoppingCartDto updateItemQuantity(Long cartItemId,
                                       CartItemUpdateRequestDto cartItem,
                                       Long currentUserId);

    void deleteItemsFromShoppingCart(Long cartItemId, Long currentUserId);

    void createShoppingCart(User user);

    ShoppingCart retrieveShoppingCartByUserId(Long currentUserId);
}
