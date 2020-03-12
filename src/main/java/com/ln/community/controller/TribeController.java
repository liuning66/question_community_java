package com.ln.community.controller;

import com.ln.community.entity.ArticleDetail;
import com.ln.community.entity.ArticleInfo;
import com.ln.community.entity.Result;
import com.ln.community.service.TribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class TribeController {
  @Autowired
  TribeService tribeService;

  @PostMapping("/add")
  public Result addArticle(String userId, String title, String content) {
    Result result = new Result();
    if (title == null || title.length() <= 0) {
      result.setStatus(400);
      result.setMsg("文章标题不能为空!");
      return result;
    } else if (content == null || content.length() <= 0) {
      result.setStatus(400);
      result.setMsg("文章内容不能为空!");
      return result;
    }
    boolean flag = this.tribeService.addArticle(userId, title, content);
    if (flag) {
      result.setStatus(200);
      result.setMsg("success!");
    } else {
      result.setStatus(400);
      result.setMsg("error!");
    }
    return result;
  }

  @PostMapping("/getAllArticle")
  public List<ArticleInfo> getAllArticle(String userId) {
    return this.tribeService.getAllArticle(userId);
  }

  @PostMapping("/getArticleDetail")
  public ArticleDetail getARticleDetail(String articleId, String userId) {
    return this.tribeService.getArticleDetail(articleId, userId);
  }
}



