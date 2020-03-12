package com.ln.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ln.community.entity.ArticleInfo;
import com.ln.community.entity.QuestionComment;
import com.ln.community.entity.QuestionInfo;
import com.ln.community.mapper.CommentMapper;
import com.ln.community.service.CommentService;
import com.ln.community.service.NoticeService;
import com.ln.community.service.QuestionService;
import com.ln.community.service.TribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, QuestionComment> implements CommentService {
  @Autowired
  QuestionService questionService;
  @Autowired
  TribeService tribeService;
  @Autowired
  NoticeService noticeService;
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
    this.noticeService.addNotice(userId,questionInfo.getUserId(),"回答了您的问题",questionId,1);
    if(questionCommentFlag) {
      questionInfo.setCommentNum(questionInfo.getCommentNum() + 1);
      questionInfoFlag = this.questionService.updateById(questionInfo);
    }
    return (questionCommentFlag && questionInfoFlag);
  }

  @Override
  @Transactional
  public boolean commentArticle(String questionId, String userId, String content) {
    boolean articleInfoFlag = false;
    boolean questionCommentFlag ;
    QuestionComment questionComment = new QuestionComment();
    ArticleInfo articleInfo = this.tribeService.getById(questionId);
    questionComment.setQuestionId(questionId);
    questionComment.setUserId(userId);
    questionComment.setCommentTime(new Date());
    questionComment.setCommentContent(content);
    questionComment.setApprovalNum(0);
    questionComment.setDisapprovalNum(0);
    questionCommentFlag = this.save(questionComment);
    this.noticeService.addNotice(userId,articleInfo.getUserId(),"评论了您的文章",questionId,2);
    if(questionCommentFlag) {
      articleInfo.setCommentNum(articleInfo.getCommentNum() + 1);
      articleInfoFlag = this.tribeService.updateById(articleInfo);
    }
    return (questionCommentFlag && articleInfoFlag);
  }
}
