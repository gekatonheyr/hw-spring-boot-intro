package mate.academy.hwspringbootintro.service;

import mate.academy.hwspringbootintro.model.Book;

import java.util.List;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();

}
