package mate.academy.hwspringbootintro.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.model.Book;
import mate.academy.hwspringbootintro.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    @Autowired
    private final BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
