package mate.academy.hwspringbootintro.repository.book;

public interface SpecificationProviderManager<T> {
    SpecificationProvider<T> getSpecificationProvider(String key);
}
