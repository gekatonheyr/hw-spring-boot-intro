package mate.academy.hwspringbootintro.dto.book;

public record BookSearchParameters(String title,
                                   String author,
                                   String isbn,
                                   String category) {
}
