package com.ln.community.controller;

import com.ln.community.entity.*;
import com.ln.community.service.CommentService;
import com.ln.community.service.FollowService;
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
  @Autowired
  public FollowService followService;
  @Autowired
  public CommentService commentService;

  @PostMapping("")
  public Result question(String userId, String title, String content) {
    Result result = new Result();
    QuestionInfo questionInfo = new QuestionInfo();
    User user = this.userService.selectUserById(userId);
    boolean status = false;
    if (user != null) {
      questionInfo.setUserId(userId);
      questionInfo.setCommentNum(0);
      questionInfo.setIsPass(1);
      questionInfo.setStatus(1);
      questionInfo.setTitle(title);
      questionInfo.setContent(content);
      questionInfo.setQuestionTime(new Date());
      status = this.questionService.save(questionInfo);
    }
    if (status) {
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
  }

  @PostMapping("/getQuestionDetail")
  public QuestionDetail getQuestionDetail(String questionId, String userId) {
    if (questionId == null || questionId.length() == 0) {
      return null;
    }
    return this.questionService.getQuestionDetail(questionId, userId);
  }

  @PostMapping("/follow")
  public Result follow(Integer type, String questionId, String userId) {
    Result result = new Result();
    boolean flag = this.followService.followQuestion(type,questionId,userId);
    if(flag) {
      result.setStatus(200);
      result.setMsg("操作成功!");
    } else {
      result.setStatus(400);
      result.setMsg("操作失败!");
    }
    return result;
  }

  @PostMapping("/comment")
  public Result commentQeestion(String questionId, String userId,String content) {
      Result result = new Result();
      if( content == null || content.length() <= 0 ) {
        result.setStatus(400);
        result.setMsg("回答内容不能为空!");
        return result;
      }
      boolean flag = this.commentService.commentQuestion(questionId,userId,content);
      if(flag) {
        result.setStatus(200);
        result.setMsg("success");
      } else {
        result.setStatus(400);
        result.setMsg("error");
      }
      return result;
  }
}
