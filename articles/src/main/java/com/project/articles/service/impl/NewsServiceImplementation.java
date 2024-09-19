package com.project.articles.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.articles.model.CityKey;
import com.project.articles.model.News;
import com.project.articles.model.NewsLabeled;
import com.project.articles.repository.NewsLabeledRepository;
import com.project.articles.repository.NewsRepository;
import com.project.articles.service.AiService;
import com.project.articles.service.NewsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewsServiceImplementation implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsLabeledRepository newsLabeledRepository;
    private final AiService aiService;

    @Override
    public List<News> getByTitle(String title) {
        return newsRepository.findByTitle(title);
    }

    @Override
    public List<NewsLabeled> getByLabel(String cityName, String cityState) {
        List<NewsLabeled> news1 = newsLabeledRepository.findByCityNameAndCityState(cityName, cityState);
        List<NewsLabeled> news2 = newsLabeledRepository.findByCityNameAndCityState("GLOBAL", "GLOBAL");
        news1.addAll(news2);
        return news1;
    }

    @Override
    public void insertArticle(NewsLabeled newsLabeled) {
        newsRepository.deleteById(newsLabeled.getId());
        newsLabeledRepository.save(newsLabeled);
    }

    @Override
    public NewsLabeled findLabeledById(Long id) {
        return newsLabeledRepository.findById(id).orElse(null);
    }

    @Override
    public void labelNews() {
        List<News> unlabeled = newsRepository.findAll();
        try {
            for(News news : unlabeled) {
                String answer = aiService.answer(news.getText());
                System.out.println("answer: "+ answer);
                Thread.sleep(2000L);
                CityKey label = new CityKey(null, null);
                if(answer.equals("GLOBAL")) {
                    label.setName("GLOBAL");
                    label.setState("GLOBAL");
                } else {
                    String[] nameAndState = answer.split(", ");
                    label.setName(nameAndState[0]);
                    label.setState(nameAndState[1]);
                }
                NewsLabeled newsLabeled = new NewsLabeled(news.getId(), news.getTitle(), news.getText(), news.getDate(), label.getName(), label.getState());
                newsLabeledRepository.save(newsLabeled);
                newsRepository.deleteById(news.getId());
            }
        } catch(InterruptedException e) {
        }
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }
    
}
