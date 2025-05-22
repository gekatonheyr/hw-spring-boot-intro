package mate.academy.hwspringbootintro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.dto.shoppingcart.CartItemRequestDto;
import mate.academy.hwspringbootintro.dto.shoppingcart.CartItemUpdateRequestDto;
import mate.academy.hwspringbootintro.dto.shoppingcart.ShoppingCartDto;
import mate.academy.hwspringbootintro.model.User;
import mate.academy.hwspringbootintro.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Description of shopping cart endpoints", description = "This part of API "
        + "is dedicated to shopping shopping cart functionality. Using of this endpoints allowed"
        + " to authorised users only.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
@PreAuthorize("hasRole('ROLE_USER')")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @Operation(summary = "Get all items of shopping cart", description = "Use this endpoint to "
            + "get all items in specified user shopping cart.")
    @GetMapping
    public ShoppingCartDto getShoppingCart() {
        return shoppingCartService.getAllCartItems(getCurrentUserId());
    }

    @Operation(summary = "Put iven items to shopping cart", description = "User should use this "
            + "endpoint to place selected items to cart by POST request.")
    @PostMapping
    public ShoppingCartDto addItemsToShoppingCart(
            @RequestBody @Valid CartItemRequestDto cartItem) {
        return shoppingCartService.addItemsToShoppingCart(cartItem, getCurrentUserId());
    }

    @Operation(summary = "Update items quantity in the cart", description = "This endpoint will "
            + "help you to change items quantity in the cart by using PUT request.")
    @PutMapping("/items/{cartItemId}")
    public ShoppingCartDto updateItemQuantity(@PathVariable Long cartItemId,
                                       @RequestBody @Valid CartItemUpdateRequestDto cartItem) {
        return shoppingCartService.updateItemQuantity(cartItemId, cartItem, getCurrentUserId());
    }

    @Operation(summary = "Delete items from cart.", description = "This endpoint s dedicated to "
            + "deletion of selected cart item by sending DELETE request.")
    @DeleteMapping("/items/{cartItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItemsFromShoppingCart(@PathVariable Long cartItemId) {
        shoppingCartService.deleteItemsFromShoppingCart(cartItemId, getCurrentUserId());
    }

    private Long getCurrentUserId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }
}
