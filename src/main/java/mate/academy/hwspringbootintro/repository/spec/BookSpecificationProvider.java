package mate.academy.hwspringbootintro.repository.spec;

import mate.academy.hwspringbootintro.model.Book;
import mate.academy.hwspringbootintro.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BookSpecificationProvider implements SpecificationProvider<Book> {
    private static final String BOOK_SPECIFICATION_PROVIDER_KEY = "book";

    public Specification<Book> getSpecification(String params) {
        return ((root, query, criteriaBuilder) ->
                root.get(params).in(params));
    }

    @Override
    public String getKey() {
        return BOOK_SPECIFICATION_PROVIDER_KEY;
    }
}
