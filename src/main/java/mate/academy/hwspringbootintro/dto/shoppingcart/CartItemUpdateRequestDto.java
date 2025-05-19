package mate.academy.hwspringbootintro.dto.shoppingcart;

import jakarta.validation.constraints.Positive;

public record CartItemUpdateRequestDto(@Positive int quantity) {
}
