package com.project.articles.service;

import java.util.List;

import com.project.articles.model.News;
import com.project.articles.model.NewsLabeled;

public interface NewsService {
    //public List<News> getNewsByCity(String city);
    public List<News> getByTitle(String title);
    public List<NewsLabeled> getByLabel(String cityName, String cityState);
    public void insertArticle(NewsLabeled newsLabeled);
    public News findById(Long id);
    public NewsLabeled findLabeledById(Long id);
    public void labelNews();
}
