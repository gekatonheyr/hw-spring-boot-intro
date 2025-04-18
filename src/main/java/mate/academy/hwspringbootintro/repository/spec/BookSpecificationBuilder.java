package mate.academy.hwspringbootintro.repository.spec;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.model.Book;
import mate.academy.hwspringbootintro.repository.SpecificationBuilder;
import mate.academy.hwspringbootintro.repository.SpecificationProvider;
import mate.academy.hwspringbootintro.repository.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private static final String PARAMS_VALUES_DELIMITER = ",";
    private final SpecificationProviderManager<Book> specificationProviderManager;

    @Override
    public Specification<Book> buildSpecification(Map<String, String> params) {
        Specification<Book> spec = Specification.where(null);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            SpecificationProvider<Book> bookSpecProv = specificationProviderManager
                    .getSpecificationProvider("book");
            String key = entry.getKey();
            String value = entry.getValue();
            Specification<Book> bookSpec = bookSpecProv
                            .getSpecification(key, value.split(PARAMS_VALUES_DELIMITER));
            spec = spec.and(bookSpec);
        }
        return spec;
    }
}
