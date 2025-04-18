package mate.academy.hwspringbootintro.repository;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider<T> {
    String getKey();

    Specification<T> getSpecification(String paramName, String[] paramValues);
}
