package br.alura.dto;

import br.alura.entity.Category;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class CategoryDTO {

    private String id;

    private String title;

    private String color;

    public CategoryDTO() {
        final var category = new Category();
        this.id = category.getId();
        this.title = category.getTitle();
        this.color = category.getColor();
    }

}
