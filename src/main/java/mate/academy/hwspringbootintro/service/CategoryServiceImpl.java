package mate.academy.hwspringbootintro.service;

import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.dto.category.CategoryDto;
import mate.academy.hwspringbootintro.dto.category.CreateCategoryRequestDto;
import mate.academy.hwspringbootintro.exception.EntityNotFoundException;
import mate.academy.hwspringbootintro.mapper.CategoryMapper;
import mate.academy.hwspringbootintro.model.Category;
import mate.academy.hwspringbootintro.repository.category.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Page<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(categoryMapper::toDto);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        return categoryMapper.toDto(categoryRepository
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Can't find category by id: " + id)));
    }

    @Override
    public CategoryDto save(CreateCategoryRequestDto categoryDto) {
        return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(categoryDto)));
    }

    @Override
    public CategoryDto update(Long id, CreateCategoryRequestDto categoryDto) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Can't find category by id: " + id));
        categoryMapper.updateDto(category, categoryDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
