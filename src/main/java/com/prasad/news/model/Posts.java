package com.prasad.news.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Posts {

    private News news;
    private List<Images> images;

   
}
