package br.alura.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "video")
public class Video {

    @Id
    private String id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @NotEmpty
    private String url;

    @DBRef(lazy = true)
    @Builder.Default
    private Category category = new Category();

    @CreatedDate
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

}
