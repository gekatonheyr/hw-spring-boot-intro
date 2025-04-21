package mate.academy.hwspringbootintro.service;

import java.util.List;
import mate.academy.hwspringbootintro.dto.BookDto;
import mate.academy.hwspringbootintro.dto.BookSearchParameters;
import mate.academy.hwspringbootintro.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto book);

    List<BookDto> findAll();

    BookDto getBookById(Long id);

    BookDto updateBook(Long id, CreateBookRequestDto bookDto);

    void deleteBook(Long id);

    List<BookDto> search(BookSearchParameters params);
}
