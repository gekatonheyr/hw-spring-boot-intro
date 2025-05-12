package mate.academy.hwspringbootintro.mapper;

import mate.academy.hwspringbootintro.config.MapperConfig;
import mate.academy.hwspringbootintro.dto.category.CategoryDto;
import mate.academy.hwspringbootintro.dto.category.CreateCategoryRequestDto;
import mate.academy.hwspringbootintro.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryRequestDto dto);

    void updateDto(@MappingTarget Category category, CreateCategoryRequestDto dto);
}
