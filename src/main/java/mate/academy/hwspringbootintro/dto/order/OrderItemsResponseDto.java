package mate.academy.hwspringbootintro.dto.order;

public record OrderItemsResponseDto(Long id,
                                    Long bookId,
                                    int quantity) {
}
