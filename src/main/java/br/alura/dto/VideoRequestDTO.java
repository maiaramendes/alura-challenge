package br.alura.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoRequestDTO {

    @NotEmpty(message = "Field title is required")
    private String title;

    @NotEmpty(message = "Field description is required")
    private String description;

    @NotEmpty(message = "Field url is required")
    private String url;
}
