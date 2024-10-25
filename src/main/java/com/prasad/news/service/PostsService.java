package com.prasad.news.service;


import com.prasad.news.dao.NewsTypeRepo;
import com.prasad.news.model.Images;
import com.prasad.news.model.News;
import com.prasad.news.model.NewsType;
import com.prasad.news.model.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PostsService {

    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsTypeRepo newsTypeRepo;


    public void upsertNews(Posts posts) {
        News news=posts.getNews();
        List<Images> images=posts.getImages();

        System.out.println(news.getNewsDescription());

        NewsType newsType=newsTypeRepo.findById(news.getNewsType().getNewsType()).orElseThrow(()->new RuntimeException("Invalid news type"));

        news.setNewsType(newsType);
        for (Images image : images) {
            image.setNews(news);
        }


        news.setImages(images);
        newsService.insertNews(news);

    }
}
