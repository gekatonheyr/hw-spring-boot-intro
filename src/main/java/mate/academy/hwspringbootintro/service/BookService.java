package mate.academy.hwspringbootintro.service;

import java.util.List;
import mate.academy.hwspringbootintro.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();

}
