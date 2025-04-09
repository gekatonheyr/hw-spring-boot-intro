package mate.academy.hwspringbootintro.repository;

import mate.academy.hwspringbootintro.model.Book;

import java.util.List;

public interface BookRepository {
    Book save(Book book);
    List<Book> findAll();
}
