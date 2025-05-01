package mate.academy.hwspringbootintro.mapper;

import mate.academy.hwspringbootintro.config.MapperConfig;
import mate.academy.hwspringbootintro.dto.book.BookDto;
import mate.academy.hwspringbootintro.dto.book.CreateBookRequestDto;
import mate.academy.hwspringbootintro.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toEntity(CreateBookRequestDto bookDto);

    void updateEntity(@MappingTarget Book book, CreateBookRequestDto bookDto);
}
