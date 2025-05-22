package mate.academy.hwspringbootintro.dto.shoppingcart;

public record CartItemResponseDto(Long id,
                                  Long bookId,
                                  String bookTitle,
                                  int quantity) {
}
