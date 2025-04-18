package mate.academy.hwspringbootintro.repository;

import java.util.Map;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> buildSpecification(Map<String, String> params);
}
