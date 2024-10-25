package com.prasad.news.dao;

import com.prasad.news.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NewsRepo extends JpaRepository<News, UUID> {
}
