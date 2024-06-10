package com.w4t3rcs.test.entity;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Builder
@Data
@AllArgsConstructor @NoArgsConstructor
@Document(indexName = "article")
public class Article {
    @Id
    private String id;
    @NotBlank
    private String author;
    @NotBlank
    private String title;
    @NotBlank
    private String body;
}
