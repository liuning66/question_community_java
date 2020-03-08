package com.ln.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ln.community.entity.QuestionComment;
import com.ln.community.entity.QuestionInfo;
import com.ln.community.mapper.CommentMapper;
import com.ln.community.service.CommentService;
import com.ln.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, QuestionComment> implements CommentService {
  @Autowired
  QuestionService questionService;
  @Override
  @Transactional
  public boolean commentQuestion(String questionId, String userId, String content) {
    boolean questionInfoFlag = false;
    boolean questionCommentFlag ;
    QuestionComment questionComment = new QuestionComment();
    QuestionInfo questionInfo = this.questionService.getById(questionId);
    questionComment.setQuestionId(questionId);
    questionComment.setUserId(userId);
    questionComment.setCommentTime(new Date());
    questionComment.setCommentContent(content);
    questionComment.setApprovalNum(0);
    questionComment.setDisapprovalNum(0);
    questionCommentFlag = this.save(questionComment);
    if(questionCommentFlag) {
      questionInfo.setCommentNum(questionInfo.getCommentNum() + 1);
      questionInfoFlag = this.questionService.updateById(questionInfo);
    }
    return (questionCommentFlag && questionCommentFlag);
  }
}
