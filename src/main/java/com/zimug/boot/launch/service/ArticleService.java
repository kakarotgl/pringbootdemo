package com.zimug.boot.launch.service;

import com.zimug.boot.launch.model.Article;

import java.util.List;


public interface ArticleService {
  public void saveArticle(Article article);


  void deleteArticle(Long id);

  void updateArticle(Article article);

  Article getArticle(Long id);

  List<Article> getAll();

}
