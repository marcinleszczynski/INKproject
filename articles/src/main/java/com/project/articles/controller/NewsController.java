package com.project.articles.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.articles.model.News;
import com.project.articles.model.NewsHeader;
import com.project.articles.model.NewsLabeled;
import com.project.articles.service.NewsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/news")
@CrossOrigin(origins = "http://localhost:3000")
public class NewsController {
    private final NewsService newsService;

    @GetMapping("/byCity")
    public List<NewsHeader> getCorrectNews(@RequestParam String name, @RequestParam String state) {
        List<NewsLabeled> news = newsService.getByLabel(name, state);
        return news.stream().map(n -> new NewsHeader(n)).toList();
    }

    @PostMapping("/label")
    public String label() {
        newsService.labelNews();
        return "News are labeled!";
    }

    @GetMapping("")
    public NewsLabeled getNewsLabeledById(@RequestParam Long id) {
        return newsService.findLabeledById(id);
    }

    @GetMapping("/test")
    public News getNewsById(@RequestParam Long id) {
        return newsService.findById(id);
    }
}
