package mate.academy.hwspringbootintro.dto.order;

import jakarta.validation.constraints.NotNull;
import mate.academy.hwspringbootintro.model.Order;
import mate.academy.hwspringbootintro.validator.annotation.ValidStatus;

public record UpdateOrderRequestDto(@NotNull @ValidStatus Order.Status status) {
}
