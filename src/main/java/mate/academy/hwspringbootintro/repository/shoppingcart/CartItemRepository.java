package mate.academy.hwspringbootintro.repository.shoppingcart;

import mate.academy.hwspringbootintro.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
