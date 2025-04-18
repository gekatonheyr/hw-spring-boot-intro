package mate.academy.hwspringbootintro.service;

import java.util.List;
import java.util.Map;
import mate.academy.hwspringbootintro.dto.BookDto;
import mate.academy.hwspringbootintro.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto book);

    List<BookDto> findAll();

    BookDto getBookById(Long id);

    BookDto updateBook(Long id, CreateBookRequestDto bookDto);

    void deleteBook(Long id);

    List<BookDto> search(Map<String, String> params);
}
