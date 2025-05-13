package mate.academy.hwspringbootintro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.hwspringbootintro.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.hwspringbootintro.dto.category.CategoryDto;
import mate.academy.hwspringbootintro.dto.category.CreateCategoryRequestDto;
import mate.academy.hwspringbootintro.service.BookService;
import mate.academy.hwspringbootintro.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Bookstore categories endpoints description.", description = "This API is intended "
        + "to give the information about the book categories.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @Operation(summary = "Show all categories.", description = "This endpoint shows the hole "
            + "categories catalog.")
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public Page<CategoryDto> getAllCategories(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @Operation(summary = "View category data by id", description = "This endpoint intended to "
            + "give all the category data with specific ID.")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @Operation(summary = "Get all books in specific category", description = "Use this API "
            + "endpoint if you want to get all the books in specified by ID category.")
    @GetMapping("/{id}/books")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Page<BookDtoWithoutCategoryIds> getBooksByCategoryId(@PathVariable Long id,
                                                                Pageable pageable) {
        return bookService.findAllByCategoryId(id, pageable);
    }

    @Operation(summary = "Create new category.", description = "Use his endpoint to create new "
            + "category.")
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CategoryDto createCategory(@RequestBody @Valid CreateCategoryRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @Operation(summary = "Update category data.", description = "This endpoints purpose is to "
            + "update existing category data.")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CategoryDto updateCategory(@PathVariable Long id,
                                      @RequestBody @Valid CreateCategoryRequestDto category) {
        return categoryService.update(id, category);
    }

    @Operation(summary = "Delete category", description = "This endpoint deletes the specified "
            + "category.")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }

}
