package mate.academy.hwspringbootintro.repository.order;

import mate.academy.hwspringbootintro.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
