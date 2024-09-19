package com.project.articles.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class News {
    @Id
    private Long id;
    private String title;
    private String text;
    private String date;
}
