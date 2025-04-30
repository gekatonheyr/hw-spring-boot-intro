package mate.academy.hwspringbootintro.repository.book.spec;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.model.Book;
import mate.academy.hwspringbootintro.repository.book.SpecificationProvider;
import mate.academy.hwspringbootintro.repository.book.SpecificationProviderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    @Autowired
    private final List<SpecificationProvider<Book>> specificationProviders;

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return specificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Can't get appropriate provider"
                        + "for key: " + key));
    }
}
