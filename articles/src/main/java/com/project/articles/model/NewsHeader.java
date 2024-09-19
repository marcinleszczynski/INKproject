package com.project.articles.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsHeader {
    private Long id;
    private String title;
    private String date;

    public NewsHeader(NewsLabeled news) {
        id = news.getId();
        title = news.getTitle();
        date = news.getDate();
    }   
}
