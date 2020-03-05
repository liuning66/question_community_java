package com.ln.community.controller;

import com.ln.community.entity.QuestionInfo;
import com.ln.community.entity.Result;
import com.ln.community.entity.User;
import com.ln.community.service.QuestionService;
import com.ln.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
  @Autowired
  public QuestionService questionService;
  @Autowired
  public UserService userService;
  @PostMapping("")
  public Result question(String userId, String title, String content) {
    Result result = new Result();
    QuestionInfo questionInfo = new QuestionInfo();
    User user = this.userService.selectUserById(userId);
    boolean status = false;
    if(user != null) {
      questionInfo.setUserId(userId);
      questionInfo.setCommentNum(0);
      questionInfo.setIsPass(1);
      questionInfo.setStatus(1);
      questionInfo.setTitle(title);
      questionInfo.setContent(content);
      questionInfo.setQuestionTime(new Date());
      status = this.questionService.save(questionInfo);
    }
    if(status) {
      result.setStatus(200);
      result.setMsg("insert data success!");
    } else {
      result.setStatus(400);
      result.setMsg("插入失败,请重试!");
    }
    return result;
  }

  @PostMapping("/getAllQuestionInfoList")
  public List<QuestionInfo> getAllQuestionInfo() {
    return this.questionService.getAllQuestionInfo();
  };
}
