package mate.academy.hwspringbootintro.repository.order;

import java.util.List;
import mate.academy.hwspringbootintro.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser_Id(Long id);
}
