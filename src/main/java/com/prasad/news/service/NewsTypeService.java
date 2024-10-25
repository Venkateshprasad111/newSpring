package com.prasad.news.service;

import com.prasad.news.dao.NewsTypeRepo;
import com.prasad.news.model.NewsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsTypeService {

    @Autowired
    private NewsTypeRepo newsType;
    public void upsertNewsType(NewsType news) {
        newsType.save(news);
    }
}
