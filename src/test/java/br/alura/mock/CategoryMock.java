package br.alura.mock;

import br.alura.dto.CategoryDTO;
import br.alura.dto.CategoryRequestDTO;
import br.alura.entity.Category;

import java.util.Arrays;
import java.util.List;

public class CategoryMock {

    public static Category createCategory() {
        return new Category().builder()
                .id("id")
                .title("description")
                .color("color")
                .build();
    }

    public static List<Category> createCategoryList() {
        return Arrays.asList(createCategory(), createCategory());
    }

    public static CategoryRequestDTO createDTO() {
        return new CategoryRequestDTO()
                .builder()
                .id("id")
                .title("titles")
                .build();
    }

    public static CategoryDTO createCategoryDTO() {
        return new CategoryDTO()
                .builder()
                .id("id")
                .title("titles")
                .build();
    }
}
