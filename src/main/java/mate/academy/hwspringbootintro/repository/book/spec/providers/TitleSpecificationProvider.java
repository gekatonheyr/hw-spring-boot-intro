package mate.academy.hwspringbootintro.repository.book.spec.providers;

import mate.academy.hwspringbootintro.model.Book;
import mate.academy.hwspringbootintro.repository.book.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TitleSpecificationProvider implements SpecificationProvider<Book> {
    private static final String TITLE_PROVIDER_KEY = "title";

    @Override
    public String getKey() {
        return TITLE_PROVIDER_KEY;
    }

    @Override
    public Specification<Book> getSpecification(String params) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(TITLE_PROVIDER_KEY), "%" + params + "%");
    }
}
