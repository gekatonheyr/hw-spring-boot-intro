package mate.academy.hwspringbootintro.dto.order;

import mate.academy.hwspringbootintro.model.Order;

public record OrderSetStatusRequestDto(Order.Status status) {
}
