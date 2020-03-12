package com.ln.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ln.community.entity.ArticleInfo;
import com.ln.community.entity.QuestionInfo;
import com.ln.community.entity.User;
import com.ln.community.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  UserService userService;
  @Autowired
  QuestionService questionService;
  @Autowired
  TribeService tribeService;
  @Autowired
  OperateService operateService;
  @Autowired
  FollowService followService;
  @RequestMapping("/getAllUser")
  public List<User> say() {
    List<User> users = this.userService.list();
    for (User user : users) {
      user.setToken(null);
      user.setPassword(null);
      user.setSecretKey(null);
    }
    return users;
  }

  @PostMapping("/getMyQuestionList")
  public List<QuestionInfo> getMyQuestionList(String userId) {
    QueryWrapper<QuestionInfo> questionInfoQueryWrapper = new QueryWrapper<>();
    questionInfoQueryWrapper.eq("userId",userId);
    List<QuestionInfo> questionInfos = this.questionService.list(questionInfoQueryWrapper);
    if(questionInfos != null && questionInfos.size() > 0) {
      return questionInfos;
    } else {
      return null;
    }
  }

  @PostMapping("/getMyArticleList")
  public List<ArticleInfo> getMyArticleList(String userId) {
    QueryWrapper<ArticleInfo> articleInfoQueryWrapper = new QueryWrapper<>();
    articleInfoQueryWrapper.eq("userId",userId);
    List<ArticleInfo> articleInfos = this.tribeService.list(articleInfoQueryWrapper);
    if(articleInfos != null && articleInfos.size() > 0) {
      return articleInfos;
    } else {
      return null;
    }
  }

  @PostMapping("/getMyCollectionList")
  public List<ArticleInfo> getMyCollectionList(String userId) {
    return this.tribeService.getMyCollectionList(userId);
  }

  @PostMapping("/getMyFollowList")
  public List<QuestionInfo> getMyFollowList(String userId) {
    return this.questionService.getMyFollowList(userId);
  }
}
