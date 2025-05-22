package mate.academy.hwspringbootintro.mapper;

import mate.academy.hwspringbootintro.config.MapperConfig;
import mate.academy.hwspringbootintro.dto.shoppingcart.ShoppingCartDto;
import mate.academy.hwspringbootintro.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = {CartItemMapper.class})
public interface ShoppingCartMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "cartItems", target = "cartItems")
    ShoppingCartDto toShoppingCartDto(ShoppingCart shoppingCart);
}
