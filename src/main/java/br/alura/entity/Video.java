package br.alura.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "video")
public class Video {

    @Id
    private String id;

    private String title;

    private String description;

    private String url;

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();
}
