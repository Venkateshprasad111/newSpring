package com.prasad.news.service;
import com.prasad.news.dao.NewsRepo;
import com.prasad.news.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NewsService {

    @Autowired
    private NewsRepo newsRepo;

    public News insertNews(News news) {
        return newsRepo.save(news);

    }
}
