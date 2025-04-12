package mate.academy.hwspringbootintro.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.hwspringbootintro.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();

    Optional<Book> getById(Long id);
}
