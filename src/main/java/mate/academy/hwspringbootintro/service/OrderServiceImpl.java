package mate.academy.hwspringbootintro.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.dto.order.CreateOrderRequestDto;
import mate.academy.hwspringbootintro.dto.order.CreateOrderResponseDto;
import mate.academy.hwspringbootintro.dto.order.OrderItemsResponseDto;
import mate.academy.hwspringbootintro.dto.order.OrderSetStatusRequestDto;
import mate.academy.hwspringbootintro.exception.DataProcessingException;
import mate.academy.hwspringbootintro.exception.EntityNotFoundException;
import mate.academy.hwspringbootintro.mapper.OrderItemMapper;
import mate.academy.hwspringbootintro.mapper.OrderMapper;
import mate.academy.hwspringbootintro.model.Order;
import mate.academy.hwspringbootintro.model.ShoppingCart;
import mate.academy.hwspringbootintro.model.User;
import mate.academy.hwspringbootintro.repository.order.OrderRepository;
import mate.academy.hwspringbootintro.repository.shoppingcart.ShoppingCartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ShoppingCartService shoppingCartService;
    private final OrderItemMapper orderItemMapper;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public CreateOrderResponseDto createNewOrder(User user,
                                                 CreateOrderRequestDto createOrderRequestDto) {
        ShoppingCart shoppingCart = shoppingCartService.retrieveShoppingCartByUserId(user.getId());
        if (shoppingCart.getCartItems().isEmpty()) {
            throw new DataProcessingException("There is no items in shopping cart to create "
                    + "order for user: " + user.getId());
        }
        Order order = new Order();
        orderMapper.shoppingCartToOrder(order, shoppingCart,
                createOrderRequestDto.shippingAddress() == null
                        ? user.getShippingAddress() : createOrderRequestDto.shippingAddress());
        orderRepository.save(order);
        shoppingCart.getCartItems().clear();
        shoppingCartRepository.save(shoppingCart);
        return orderMapper.toDto(order);
    }

    @Override
    public List<CreateOrderResponseDto> getOrdersHistory(User user) {
        return orderRepository.findAllByUser_Id(user.getId()).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public CreateOrderResponseDto setNewOrderStatus(Long id,
                                               OrderSetStatusRequestDto orderSetStatusRequestDto) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't find order with id: " + id));
        orderMapper.updateEntity(order, orderSetStatusRequestDto);
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderItemsResponseDto> getOrderItems(User user, Long orderId) {
        return orderRepository.findAllByUser_Id(user.getId()).stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst().orElseThrow(() -> new EntityNotFoundException(
                        "User with id: " + user.getId() + " does not have order with id: "
                        + orderId))
                .getOrderItems().stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public OrderItemsResponseDto getSpecifiedOrderItem(User user, Long orderId, Long itemId) {
        return orderItemMapper.toDto(orderRepository.findAllByUser_Id(user.getId()).stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst().orElseThrow(() -> new EntityNotFoundException(
                        "User with id: " + user.getId() + " does not have order with id: "
                                + orderId))
                .getOrderItems().stream()
                .filter(orderItem -> orderItem.getId().equals(itemId))
                .findFirst().orElseThrow(() -> new EntityNotFoundException("Order with id: "
                        + orderId + " does not have item with id: " + itemId)));
    }
}
