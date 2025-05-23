package mate.academy.hwspringbootintro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.dto.order.CreateOrderRequestDto;
import mate.academy.hwspringbootintro.dto.order.OrderItemsResponseDto;
import mate.academy.hwspringbootintro.dto.order.OrderResponseDto;
import mate.academy.hwspringbootintro.dto.order.UpdateOrderRequestDto;
import mate.academy.hwspringbootintro.model.User;
import mate.academy.hwspringbootintro.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Bookstore orders API", description = """
This part of API is responsible for orders management. See endpoints descriptions to us it. \s
        """)
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Create new order.", description = "Use this endpoint to create new "
            + "order from the items placed to shopping cart. ATTENTION!!! After forming the order "
            + "shopping cart wil be cleared!")
    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public OrderResponseDto createOrder(@RequestBody @Valid
                                              CreateOrderRequestDto createOrderRequestDto) {
        return orderService.createNewOrder(getCurrentUserId(), createOrderRequestDto);
    }

    @Operation(summary = "Get all orders of user.", description = "This endpoint is "
            + "used to retrieve all placed orders for specific user.")
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<OrderResponseDto> getOrdersHistory() {
        return orderService.getOrdersHistory(getCurrentUserId());
    }

    @Operation(summary = "Set or change order status.", description = "This endpoint dedicated "
            + "to order status changing. By default for new orders CREATED status assigned. "
            + "ATTENTION!! You will need administrator privileges to use endpoint!")
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public OrderResponseDto setNewOrderStatus(@PathVariable Long id,
                                              @RequestBody @Valid
                                              UpdateOrderRequestDto updateOrderRequestDto) {
        return orderService.setNewOrderStatus(id, updateOrderRequestDto);
    }

    @Operation(summary = "Get the items of specified order.", description = "This endpoint "
            + "have to be used to retrieve all items of specified order.")
    @GetMapping("/{orderId}/items")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<OrderItemsResponseDto> getOrderItems(@PathVariable @Valid Long orderId) {
        return orderService.getOrderItems(getCurrentUserId(), orderId);
    }

    @Operation(summary = "Get SPECIFIED item of SPECIFIED order.", description = "This endpoint "
            + "will how you one specified by ID item of the order specified by ID. ATTENTION!! "
            + "If there is such item with ID BUT it not included into order of current user with"
            + " specified ID - you will get the exception!")
    @GetMapping("/{orderId}/items/{itemId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public OrderItemsResponseDto getSpecifiedOrderItem(@PathVariable @Valid Long orderId,
                                                       @PathVariable @Valid Long itemId) {
        return orderService.getSpecifiedOrderItem(getCurrentUserId(), orderId, itemId);
    }

    private Long getCurrentUserId() {
        return ((User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getId();
    }
}
