package com.ln.community.controller;

import com.ln.community.entity.QuestionComment;
import com.ln.community.entity.Result;
import com.ln.community.service.CommentService;
import com.ln.community.service.NoticeService;
import com.ln.community.service.OperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("operate")
public class OperateController {
  @Autowired
  OperateService operateService;
  @Autowired
  CommentService commentService;
  @Autowired
  NoticeService noticeService;
  @PostMapping("")
  public Result operateArticle(String articleId, String userId, Integer operateType, Integer type) {
    Result result = new Result();
    boolean flag = this.operateService.operateArticle(articleId, userId, operateType, type);
    if (flag) {
      result.setStatus(200);
      result.setMsg("success!");
    } else {
      result.setStatus(400);
      result.setMsg("error!");
    }
    return result;
  }

  @PostMapping("/comment")
  public Result operateComment(String userId,String commentId, Integer type) {
    Result result = new Result();
    boolean flag;
    QuestionComment questionComment = this.commentService.getById(commentId);
    if (type == 1) {
      questionComment.setApprovalNum(questionComment.getApprovalNum() + 1);
      this.noticeService.addNotice(userId,questionComment.getUserId(),"赞了您的评论",questionComment.getId(),3);
    } else {
      questionComment.setDisapprovalNum(questionComment.getDisapprovalNum() + 1);
    }
     flag =  this.commentService.updateById(questionComment);
    if (flag) {
      result.setStatus(200);
      result.setMsg("success!");
    } else {
      result.setStatus(400);
      result.setMsg("error!");
    }
    return result;
  }
}
