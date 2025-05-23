package mate.academy.hwspringbootintro.mapper;

import mate.academy.hwspringbootintro.config.MapperConfig;
import mate.academy.hwspringbootintro.dto.order.OrderItemsResponseDto;
import mate.academy.hwspringbootintro.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mapping(target = "bookId",
            source = "orderItem.book.id")
    OrderItemsResponseDto toDto(OrderItem orderItem);

}
