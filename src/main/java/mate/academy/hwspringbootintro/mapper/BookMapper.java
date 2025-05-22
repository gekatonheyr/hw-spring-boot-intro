package mate.academy.hwspringbootintro.mapper;

import java.util.Optional;
import java.util.stream.Collectors;
import mate.academy.hwspringbootintro.config.MapperConfig;
import mate.academy.hwspringbootintro.dto.book.BookDto;
import mate.academy.hwspringbootintro.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.hwspringbootintro.dto.book.CreateBookRequestDto;
import mate.academy.hwspringbootintro.model.Book;
import mate.academy.hwspringbootintro.model.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = {CategoryMapper.class})
public interface BookMapper {
    BookDto toDto(Book book);

    Book toEntity(CreateBookRequestDto bookDto);

    void updateEntity(@MappingTarget Book book, CreateBookRequestDto bookDto);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @AfterMapping
    default void setCategories(@MappingTarget Book book,
                              CreateBookRequestDto createBookRequestDto) {
        book.setCategories(createBookRequestDto.getCategoryIds().stream()
                .map(Category::new)
                .collect(Collectors.toSet()));
    }

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        bookDto.setCategory(book.getCategories().stream()
                .map(Category::getId)
                .toArray(Long[]::new));
    }

    @Named("bookFromId")
    default Book bookFromId(Long id) {
        return Optional.ofNullable(id)
                .map(Book::new)
                .orElse(null);
    }
}
