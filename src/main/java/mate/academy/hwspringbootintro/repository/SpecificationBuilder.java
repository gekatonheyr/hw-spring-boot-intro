package mate.academy.hwspringbootintro.repository;

import mate.academy.hwspringbootintro.dto.BookSearchParameters;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> buildSpecification(BookSearchParameters searchParameters);
}
