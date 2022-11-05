package br.alura.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
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
