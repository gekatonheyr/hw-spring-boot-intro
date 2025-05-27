package mate.academy.hwspringbootintro.repository.order;

import java.util.List;
import java.util.Optional;
import mate.academy.hwspringbootintro.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Optional<List<OrderItem>> findAllByOrderIdAndOrder_UserId(Long orderId, Long userId);

    Optional<OrderItem> findByIdAndOrderIdAndOrder_UserId(Long itemId, Long orderId, Long userId);
}
