package br.alura.dto;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoDTO {

    private String id;

    private String title;

    private String description;

    private String url;
}
