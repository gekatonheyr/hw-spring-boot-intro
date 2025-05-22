package mate.academy.hwspringbootintro.repository.shoppingcart;

import java.util.Optional;
import mate.academy.hwspringbootintro.model.ShoppingCart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @EntityGraph(attributePaths = {"cartItems"})
    Optional<ShoppingCart> findShoppingCartByUserId(Long userId);
}
