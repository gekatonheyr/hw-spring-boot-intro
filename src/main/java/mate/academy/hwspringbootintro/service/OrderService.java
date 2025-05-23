package mate.academy.hwspringbootintro.service;

import java.util.List;
import mate.academy.hwspringbootintro.dto.order.CreateOrderRequestDto;
import mate.academy.hwspringbootintro.dto.order.CreateOrderResponseDto;
import mate.academy.hwspringbootintro.dto.order.OrderItemsResponseDto;
import mate.academy.hwspringbootintro.dto.order.OrderSetStatusRequestDto;
import mate.academy.hwspringbootintro.model.User;

public interface OrderService {
    List<CreateOrderResponseDto> getOrdersHistory(User user);

    CreateOrderResponseDto createNewOrder(User user,
                                          CreateOrderRequestDto createOrderRequestDto);

    CreateOrderResponseDto setNewOrderStatus(Long id,
                                              OrderSetStatusRequestDto orderSetStatusRequestDto);

    List<OrderItemsResponseDto> getOrderItems(User user, Long orderId);

    OrderItemsResponseDto getSpecifiedOrderItem(User user, Long orderId, Long itemId);
}
