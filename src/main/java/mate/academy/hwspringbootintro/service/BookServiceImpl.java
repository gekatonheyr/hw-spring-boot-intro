package mate.academy.hwspringbootintro.service;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.dto.book.BookDto;
import mate.academy.hwspringbootintro.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.hwspringbootintro.dto.book.BookSearchParameters;
import mate.academy.hwspringbootintro.dto.book.CreateBookRequestDto;
import mate.academy.hwspringbootintro.exception.EntityNotFoundException;
import mate.academy.hwspringbootintro.mapper.BookMapper;
import mate.academy.hwspringbootintro.model.Book;
import mate.academy.hwspringbootintro.model.Category;
import mate.academy.hwspringbootintro.repository.book.BookRepository;
import mate.academy.hwspringbootintro.repository.book.spec.BookSpecificationBuilder;
import mate.academy.hwspringbootintro.repository.category.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    public BookDto save(CreateBookRequestDto requestBook) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(requestBook)));
    }

    @Override
    public Page<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).map(bookMapper::toDto);
    }

    @Override
    public BookDto getBookById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Book not found with id " + id)
        ));
    }

    @Override
    public BookDto updateBook(Long id, CreateBookRequestDto bookDto) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find book by id:" + id));
        bookMapper.updateEntity(book, bookDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Page<BookDto> search(BookSearchParameters params, Pageable pageable) {
        return bookRepository.findAll(bookSpecificationBuilder.buildSpecification(params),
                        pageable).map(bookMapper::toDto);
    }

    @Override
    public Page<BookDtoWithoutCategoryIds> findAllByCategoryId(Long id, Pageable pageable) {
        return bookRepository.findAllByCategories(Set.of(new Category(id)), pageable)
                .map(bookMapper::toDtoWithoutCategories);
    }
}
