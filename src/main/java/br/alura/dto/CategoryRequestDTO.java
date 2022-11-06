package br.alura.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO {

    private String id;

    @NotEmpty(message = "Field title is required")
    private String title;

    @NotEmpty(message = "Field color is required")
    private String color;

}
