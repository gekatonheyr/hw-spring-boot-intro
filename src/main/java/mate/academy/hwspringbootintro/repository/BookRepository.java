package mate.academy.hwspringbootintro.repository;

import java.util.List;
import mate.academy.hwspringbootintro.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
