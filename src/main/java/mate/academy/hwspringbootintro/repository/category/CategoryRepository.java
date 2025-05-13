package mate.academy.hwspringbootintro.repository.category;

import mate.academy.hwspringbootintro.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
