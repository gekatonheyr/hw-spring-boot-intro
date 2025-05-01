package mate.academy.hwspringbootintro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.dto.book.BookDto;
import mate.academy.hwspringbootintro.dto.book.BookSearchParameters;
import mate.academy.hwspringbootintro.dto.book.CreateBookRequestDto;
import mate.academy.hwspringbootintro.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Bookstore API documentation", description = "Bookstore management endpoint API "
        + "description.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Operation(summary = "Get all books catalog", description = "Use this endpoint to get all "
            + "books data. Endpoint supports pagination and sorting by using GET method path"
            + "parameters 'page', 'size' and 'sort")
    @GetMapping
    public Page<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @Operation(summary = "Get book by id", description = "This endpoint gives all the data about"
            + " the book with given id.")
    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @Operation(summary = "Create new book.", description = "This endpoint allows to create new "
            + "book record by using JSON body to carry the data about the book object. See the "
            + "example to give right format of the request.")
    @PostMapping
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto bookDto) {
        return bookService.save(bookDto);
    }

    @Operation(summary = "Update the book record.", description = "Use this endpoint to update "
            + "the book data. You can update the book data with ID given as path variable and "
            + "putting the needed data to the JSON body f the request. See example to put the "
            + "right data to the request to avoid of errors.")
    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable Long id,
                              @RequestBody @Valid CreateBookRequestDto bookDto) {
        return bookService.updateBook(id, bookDto);
    }

    @Operation(summary = "Delete book.", description = "This endpoint allows you to delete the "
            + "book record by giving book ID as the path variable. If such ID is absent in DB "
            + "you will be responded by exception message, otherwise if operation succeed "
            + "NO_CONTENT status will be sent.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @Operation(summary = "Search the books DB by parameters", description = "This endpoint "
            + "allows you to search through the books DB and find books by giving such "
            + "parameters as 'author', 'title' and/or 'isbn'. By the way 'title' parameter could"
            + "be given partially, other params need to strictly match the data in the DB.")
    @GetMapping("/search")
    public Page<BookDto> search(BookSearchParameters requestParams, Pageable pageable) {
        return bookService.search(requestParams, pageable);
    }
}
