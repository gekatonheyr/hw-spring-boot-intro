package mate.academy.hwspringbootintro.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.dto.order.CreateOrderRequestDto;
import mate.academy.hwspringbootintro.dto.order.OrderItemsResponseDto;
import mate.academy.hwspringbootintro.dto.order.OrderResponseDto;
import mate.academy.hwspringbootintro.dto.order.UpdateOrderRequestDto;
import mate.academy.hwspringbootintro.exception.EntityNotFoundException;
import mate.academy.hwspringbootintro.exception.OrderProcessingException;
import mate.academy.hwspringbootintro.mapper.OrderItemMapper;
import mate.academy.hwspringbootintro.mapper.OrderMapper;
import mate.academy.hwspringbootintro.model.Order;
import mate.academy.hwspringbootintro.model.ShoppingCart;
import mate.academy.hwspringbootintro.repository.order.OrderItemRepository;
import mate.academy.hwspringbootintro.repository.order.OrderRepository;
import mate.academy.hwspringbootintro.repository.shoppingcart.ShoppingCartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ShoppingCartService shoppingCartService;

    @Override
    public OrderResponseDto createNewOrder(Long userId,
                                           CreateOrderRequestDto createOrderRequestDto) {
        ShoppingCart shoppingCart = shoppingCartService.retrieveShoppingCartByUserId(userId);
        if (shoppingCart.getCartItems().isEmpty()) {
            throw new OrderProcessingException("There is no items in shopping cart to create "
                    + "order for user: " + userId);
        }
        Order order = new Order();
        orderMapper.shoppingCartToOrder(order, shoppingCart,
                createOrderRequestDto.shippingAddress() == null
                        ? shoppingCart.getUser().getShippingAddress()
                        : createOrderRequestDto.shippingAddress());
        orderRepository.save(order);
        shoppingCart.getCartItems().clear();
        shoppingCartRepository.save(shoppingCart);
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderResponseDto> getOrdersHistory(Long userId) {
        return orderRepository.findAllByUser_Id(userId).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderResponseDto setNewOrderStatus(Long id,
                                              UpdateOrderRequestDto updateOrderRequestDto) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't find order with id: " + id));
        orderMapper.updateEntity(order, updateOrderRequestDto);
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderItemsResponseDto> getOrderItems(Long userId, Long orderId) {
        return orderItemRepository.findAllByOrderIdAndOrder_UserId(orderId, userId)
                .orElseThrow(() -> new OrderProcessingException("Can't find items for order: "
                + orderId + " for user: " + userId)).stream().map(orderItemMapper::toDto).toList();
    }

    @Override
    public OrderItemsResponseDto getSpecifiedOrderItem(Long userId, Long orderId, Long itemId) {
        return orderItemMapper.toDto(orderItemRepository.findByIdAndOrderIdAndOrder_UserId(itemId,
                        orderId, userId).orElseThrow(() -> new OrderProcessingException("Can't "
                + "find item with such parameters - user ID: " + userId
                + " order ID: " + orderId
                + " item ID: " + itemId)));
    }
}
