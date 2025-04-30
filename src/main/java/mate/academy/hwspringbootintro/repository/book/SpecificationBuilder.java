package mate.academy.hwspringbootintro.repository.book;

import mate.academy.hwspringbootintro.dto.book.BookSearchParameters;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> buildSpecification(BookSearchParameters searchParameters);
}
