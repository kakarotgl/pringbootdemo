package com.zimug.boot.launch.service;

import com.zimug.boot.launch.model.ArticleVO;

import java.util.List;


public interface ArticleService {
  void saveArticle(ArticleVO article);

  void deleteArticle(Long id);

  void updateArticle(ArticleVO article);

  ArticleVO getArticle(Long id);

  List<ArticleVO> getAll();
}
