package mate.academy.hwspringbootintro.repository;

import mate.academy.hwspringbootintro.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
