package mate.academy.hwspringbootintro;

import java.math.BigDecimal;
import mate.academy.hwspringbootintro.model.Book;
import mate.academy.hwspringbootintro.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HwSpringBootIntroApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(HwSpringBootIntroApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            Book book = new Book();
            book.setIsbn("123");
            book.setTitle("Book Title");
            book.setAuthor("Author");
            book.setPrice(BigDecimal.valueOf(299.40));
            bookService.save(book);
        };
    }
}
