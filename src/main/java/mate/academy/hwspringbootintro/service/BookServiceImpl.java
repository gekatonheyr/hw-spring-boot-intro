package mate.academy.hwspringbootintro.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.dto.BookDto;
import mate.academy.hwspringbootintro.dto.CreateBookRequestDto;
import mate.academy.hwspringbootintro.exception.EntityNotFoundException;
import mate.academy.hwspringbootintro.mapper.BookMapper;
import mate.academy.hwspringbootintro.model.Book;
import mate.academy.hwspringbootintro.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto requestBook) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(requestBook)));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getBookById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Book not found with id " + id)
        ));
    }

    @Override
    public BookDto updateBook(Long id, CreateBookRequestDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        book.setId(id);
        bookRepository.save(book);
        return null;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
