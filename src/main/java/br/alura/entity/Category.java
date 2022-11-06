package br.alura.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    private String id;

    private String title;

    private String color;
}
