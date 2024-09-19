package com.project.articles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.articles.model.NewsLabeled;

public interface NewsLabeledRepository extends JpaRepository<NewsLabeled, Long> {
    public List<NewsLabeled> findByCityNameAndCityState(String cityName, String cityState);
}
