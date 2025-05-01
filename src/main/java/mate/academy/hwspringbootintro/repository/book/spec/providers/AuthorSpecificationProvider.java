package mate.academy.hwspringbootintro.repository.book.spec.providers;

import mate.academy.hwspringbootintro.model.Book;
import mate.academy.hwspringbootintro.repository.book.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<Book> {
    private static final String AUTHOR_PROVIDER_KEY = "author";

    @Override
    public String getKey() {
        return AUTHOR_PROVIDER_KEY;
    }

    @Override
    public Specification<Book> getSpecification(String params) {

        return (root, query, criteriaBuilder) ->
            root.get(AUTHOR_PROVIDER_KEY).in(criteriaBuilder.literal(params));
    }
}
