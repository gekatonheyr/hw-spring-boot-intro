package mate.academy.hwspringbootintro.mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import mate.academy.hwspringbootintro.config.MapperConfig;
import mate.academy.hwspringbootintro.dto.order.CreateOrderRequestDto;
import mate.academy.hwspringbootintro.dto.order.OrderResponseDto;
import mate.academy.hwspringbootintro.dto.order.UpdateOrderRequestDto;
import mate.academy.hwspringbootintro.model.Order;
import mate.academy.hwspringbootintro.model.OrderItem;
import mate.academy.hwspringbootintro.model.ShoppingCart;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {
    Order toEntity(CreateOrderRequestDto createOrderRequestDto);

    @Mapping(target = "userId",
            source = "user.id")
    @Mapping(target = "orderItems",
            source = "orderItems")
    OrderResponseDto toDto(Order order);

    @Mapping(target = "id",
            ignore = true)
    @Mapping(target = "user",
            source = "shoppingCart.user")
    void shoppingCartToOrder(@MappingTarget Order order, ShoppingCart shoppingCart,
                              String shippingAddress);

    @AfterMapping
    default void setOrderItemsAndDetails(@MappingTarget Order order, ShoppingCart shoppingCart) {
        AtomicReference<BigDecimal> totalPrice = new AtomicReference<>(BigDecimal.ZERO);
        order.setOrderItems(shoppingCart.getCartItems().stream()
                .map(item -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setBook(item.getBook());
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setPrice(orderItem.getBook()
                            .getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
                    totalPrice.updateAndGet(v -> v.add(orderItem.getPrice()));
                    return orderItem;
                }).collect(Collectors.toSet()));
        order.setStatus(Order.Status.CREATED);
        order.setTotal(totalPrice.get());
        order.setOrderDate(LocalDateTime.now());
    }

    void updateEntity(@MappingTarget Order order,
                      UpdateOrderRequestDto updateOrderRequestDto);
}
