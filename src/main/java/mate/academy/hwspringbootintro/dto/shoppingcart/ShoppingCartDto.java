package mate.academy.hwspringbootintro.dto.shoppingcart;

import java.util.Set;

public record ShoppingCartDto(Long id,
                              Long userId,
                              Set<CartItemResponseDto> cartItems) {
}
