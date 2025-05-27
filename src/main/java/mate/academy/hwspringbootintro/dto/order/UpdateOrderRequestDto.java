package mate.academy.hwspringbootintro.dto.order;

import jakarta.validation.constraints.NotNull;
import mate.academy.hwspringbootintro.model.Order;

public record UpdateOrderRequestDto(@NotNull Order.Status status) {
}
