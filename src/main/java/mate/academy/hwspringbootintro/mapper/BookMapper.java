package mate.academy.hwspringbootintro.mapper;

import mate.academy.hwspringbootintro.config.MapperConfig;
import mate.academy.hwspringbootintro.dto.BookDto;
import mate.academy.hwspringbootintro.dto.CreateBookRequestDto;
import mate.academy.hwspringbootintro.model.Book;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(config = MapperConfig.class)
@Component
public interface BookMapper {
    BookDto toDto(Book book);

    Book toEntity(CreateBookRequestDto bookDto);
}
