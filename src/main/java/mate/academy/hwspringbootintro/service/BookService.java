package mate.academy.hwspringbootintro.service;

import mate.academy.hwspringbootintro.dto.BookDto;
import mate.academy.hwspringbootintro.dto.BookSearchParameters;
import mate.academy.hwspringbootintro.dto.CreateBookRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto book);

    Page<BookDto> findAll(Pageable pageable);

    BookDto getBookById(Long id);

    BookDto updateBook(Long id, CreateBookRequestDto bookDto);

    void deleteBook(Long id);

    Page<BookDto> search(BookSearchParameters params, Pageable pageable);
}
