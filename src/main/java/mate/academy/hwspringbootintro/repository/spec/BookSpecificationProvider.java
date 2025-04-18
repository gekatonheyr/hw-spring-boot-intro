package mate.academy.hwspringbootintro.repository.spec;

import java.util.Arrays;
import mate.academy.hwspringbootintro.model.Book;
import mate.academy.hwspringbootintro.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BookSpecificationProvider implements SpecificationProvider<Book> {
    public Specification<Book> getSpecification(String paramName, String[] paramValues) {
        return ((root, query, criteriaBuilder) ->
                root.get(paramName).in(Arrays.stream(paramValues).toArray()));
    }

    @Override
    public String getKey() {
        return "book";
    }
}
