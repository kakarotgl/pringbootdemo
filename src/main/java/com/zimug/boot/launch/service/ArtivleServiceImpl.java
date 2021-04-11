package com.zimug.boot.launch.service;


import com.zimug.boot.launch.generator.testdb.Article;
import com.zimug.boot.launch.generator.testdb.ArticleMapper;
import com.zimug.boot.launch.generator.testdb2.Message;
import com.zimug.boot.launch.generator.testdb2.MessageMapper;
import com.zimug.boot.launch.model.ArticleVO;
import com.zimug.boot.launch.utils.DozerUtils;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArtivleServiceImpl implements  ArticleService {


  @Resource
  protected Mapper dozerMapper;

  @Resource
  private ArticleMapper articleMapper;

  @Resource
  private MessageMapper messageMapper;


  //新增
  @Override
  @Transactional
  public void saveArticle(ArticleVO article) {
    Article articlePO = dozerMapper.map(article, Article.class);
    articleMapper.insert(articlePO);

    Message message = new Message();
    message.setName("kobe");
    message.setContent("退役啦");
    messageMapper.insert(message);

    //int a = 2/0;     //认为制造被除数为0的异常
  }

  //删除
  @Override
  public void deleteArticle(Long id) {
    articleMapper.deleteByPrimaryKey(id);
  }

  //更新
  @Override
  public void updateArticle(ArticleVO article) {
    Article articlePO = dozerMapper.map(article,Article.class);
    articleMapper.updateByPrimaryKeySelective(articlePO);
  }

  //查询
  @Override
  public ArticleVO getArticle(Long id) {

    return dozerMapper.map(articleMapper.selectByPrimaryKey(id),ArticleVO.class);
  }

  //查询所有
  @Override
  public List<ArticleVO> getAll() {
    List<Article> articles = articleMapper.selectByExample(null);
    return DozerUtils.mapList(articles,ArticleVO.class);
  }
}
