package br.alura.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@Document(collection = "category")
public class Category {

    @Id
    private String id;

    private String title;

    private String color;

    @CreatedDate
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Category() {
        this.id = "1";
        this.title = "Livre";
        this.color = "Branco";
    }
}
