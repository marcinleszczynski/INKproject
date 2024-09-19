package com.project.articles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.articles.model.News;

public interface NewsRepository extends JpaRepository<News, Long> {
    public List<News> findByTitleIn(List<String> titles);
    public List<News> findByTitle(String title);
}
