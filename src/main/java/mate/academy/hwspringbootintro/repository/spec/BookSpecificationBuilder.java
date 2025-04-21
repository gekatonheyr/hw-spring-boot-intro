package mate.academy.hwspringbootintro.repository.spec;

import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.dto.BookSearchParameters;
import mate.academy.hwspringbootintro.model.Book;
import mate.academy.hwspringbootintro.repository.SpecificationBuilder;
import mate.academy.hwspringbootintro.repository.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private static final String AUTHOR_PROVIDER_KEY = "author";
    private static final String TITLE_PROVIDER_KEY = "title";
    private static final String ISBN_PROVIDER_KEY = "isbn";
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> buildSpecification(BookSearchParameters params) {
        Specification<Book> spec = Specification.where(null);
        if (params.author() != null && !params.author().isEmpty()) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider(AUTHOR_PROVIDER_KEY)
                    .getSpecification(params.author()));
        }
        if (params.title() != null && !params.title().isEmpty()) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider(TITLE_PROVIDER_KEY)
                    .getSpecification(params.title()));
        }
        if (params.isbn() != null && !params.isbn().isEmpty()) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider(ISBN_PROVIDER_KEY)
                    .getSpecification(params.isbn()));
        }
        return spec;
    }

}
