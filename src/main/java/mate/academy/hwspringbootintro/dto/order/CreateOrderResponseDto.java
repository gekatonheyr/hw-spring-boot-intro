package mate.academy.hwspringbootintro.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import mate.academy.hwspringbootintro.model.Order;

public record CreateOrderResponseDto(Long id,
                                     Long userId,
                                     Set<OrderItemsResponseDto> orderItems,
                                     LocalDateTime orderDate,
                                     BigDecimal total,
                                     Order.Status status,
                                     String shippingAddress) {
}
