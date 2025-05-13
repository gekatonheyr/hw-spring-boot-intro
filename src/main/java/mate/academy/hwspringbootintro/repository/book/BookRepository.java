package mate.academy.hwspringbootintro.repository.book;

import java.util.Set;
import mate.academy.hwspringbootintro.model.Book;
import mate.academy.hwspringbootintro.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepository
        extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    Page<Book> findAllByCategories(Set<Category> categories, Pageable pageable);

}
