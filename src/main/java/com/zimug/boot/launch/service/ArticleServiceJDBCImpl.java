package com.zimug.boot.launch.service;

import com.zimug.boot.launch.dao.ArticleJDBCDAO;
import com.zimug.boot.launch.model.Article;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceJDBCImpl implements  ArticleService {

  @Resource
  private
  ArticleJDBCDAO articleJDBCDAO;

  @Override
  public void saveArticle(Article article) {
    articleJDBCDAO.save(article);
  }

  @Override
  public void deleteArticle(Long id) {
    articleJDBCDAO.deleteById(id);
  }

  @Override
  @Transactional
  public void updateArticle(Article article) {
    if(article.getId() == null){
      //article.id是必传参数，因为通常根据id去修改数据
      //TODO 抛出一个自定义的异常
    }

    articleJDBCDAO.deleteById(article.getId());
    articleJDBCDAO.save(article);
    //articleJDBCDAO.updateById(article);

    //int a = 10/0;
  }

  @Override
  public Article getArticle(Long id) {
    return articleJDBCDAO.findById(id);
  }

  @Override
  public List<Article> getAll() {
    return articleJDBCDAO.findAll();
  }
}
