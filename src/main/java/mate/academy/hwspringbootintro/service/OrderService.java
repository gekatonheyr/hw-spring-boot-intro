package mate.academy.hwspringbootintro.service;

import java.util.List;
import mate.academy.hwspringbootintro.dto.order.CreateOrderRequestDto;
import mate.academy.hwspringbootintro.dto.order.OrderItemsResponseDto;
import mate.academy.hwspringbootintro.dto.order.OrderResponseDto;
import mate.academy.hwspringbootintro.dto.order.UpdateOrderRequestDto;

public interface OrderService {
    List<OrderResponseDto> getOrdersHistory(Long userId);

    OrderResponseDto createNewOrder(Long userId,
                                    CreateOrderRequestDto createOrderRequestDto);

    OrderResponseDto setNewOrderStatus(Long id,
                                       UpdateOrderRequestDto updateOrderRequestDto);

    List<OrderItemsResponseDto> getOrderItems(Long userId, Long orderId);

    OrderItemsResponseDto getSpecifiedOrderItem(Long userId, Long orderId, Long itemId);
}
