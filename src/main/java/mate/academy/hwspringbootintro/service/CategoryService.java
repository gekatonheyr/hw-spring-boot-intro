package mate.academy.hwspringbootintro.service;

import mate.academy.hwspringbootintro.dto.category.CategoryDto;
import mate.academy.hwspringbootintro.dto.category.CreateCategoryRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<CategoryDto> findAll(Pageable pageable);

    CategoryDto getCategoryById(Long id);

    CategoryDto save(CreateCategoryRequestDto categoryDto);

    CategoryDto update(Long id, CreateCategoryRequestDto categoryDto);

    void deleteCategoryById(Long id);
}
