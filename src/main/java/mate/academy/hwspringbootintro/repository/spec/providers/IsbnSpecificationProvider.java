package mate.academy.hwspringbootintro.repository.spec.providers;

import mate.academy.hwspringbootintro.model.Book;
import mate.academy.hwspringbootintro.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class IsbnSpecificationProvider implements SpecificationProvider<Book> {
    private static final String ISBN_PROVIDER_KEY = "isbn";

    @Override
    public String getKey() {
        return ISBN_PROVIDER_KEY;
    }

    @Override
    public Specification<Book> getSpecification(String params) {
        return (root, query, criteriaBuilder) ->
                root.get(ISBN_PROVIDER_KEY).in(criteriaBuilder.literal(params.trim()));
    }
}
