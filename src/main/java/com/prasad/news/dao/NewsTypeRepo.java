package com.prasad.news.dao;

import com.prasad.news.model.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface NewsTypeRepo extends JpaRepository<NewsType, String> {


}
