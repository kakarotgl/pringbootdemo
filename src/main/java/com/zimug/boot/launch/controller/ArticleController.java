package com.zimug.boot.launch.controller;


import com.zimug.boot.launch.AjaxResponse;
import com.zimug.boot.launch.model.ArticleVO;
import com.zimug.boot.launch.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
//@RestController
@Controller
@RequestMapping("/rest")
public class ArticleController {

  @Resource
  ArticleService articleService;

  //获取一篇Article，使用GET方法,根据id查询一篇文章
  //@RequestMapping(value = "/articles/{id}",method = RequestMethod.GET)
  @GetMapping("/articles/{id}")
  public @ResponseBody AjaxResponse getArticle(@PathVariable("id") Long id){

    ArticleVO article = articleService.getArticle(id);

    log.info("article:" + article);

    return AjaxResponse.success(article);
  }


  //增加一篇Article ，使用POST方法
  //@RequestMapping(value = "/articles",method = RequestMethod.POST)
  @PostMapping("/articles")
  public @ResponseBody AjaxResponse saveArticle(@RequestBody ArticleVO article){

    articleService.saveArticle(article);

    //因为使用了lombok的Slf4j注解，这里可以直接使用log变量打印日志
    log.info("saveArticle:" + article);
    return AjaxResponse.success();
  }

  //更新一篇Article，使用PUT方法，以id为主键进行更新
  //@RequestMapping(value = "/articles",method = RequestMethod.PUT)
  @PutMapping("/articles")
  public @ResponseBody AjaxResponse updateArticle(@RequestBody ArticleVO article){

    articleService.updateArticle(article);
    log.info("updateArticle:" + article);
    return AjaxResponse.success();
  }

  //删除一篇Article，使用DELETE方法，参数是id
  //@RequestMapping(value = "/articles/{id}",method = RequestMethod.DELETE)
  @DeleteMapping("/articles/{id}")
  public @ResponseBody AjaxResponse deleteArticle(@PathVariable("id") Long id){

    articleService.deleteArticle(id);
    log.info("deleteArticle:" + id);
    return AjaxResponse.success();
  }

  @GetMapping("/articles")
  public @ResponseBody AjaxResponse getArticle(){

    List<ArticleVO> articles = articleService.getAll();

    log.info("articles:" + articles);

    return AjaxResponse.success(articles);
  }

}
