package com.project.articles.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class NewsLabeled {
    @Id
    private Long id;
    private String title;
    private String text;
    private String date;
    private String cityName;
    private String cityState;
}
