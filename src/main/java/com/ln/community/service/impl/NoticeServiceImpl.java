package com.ln.community.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ln.community.entity.ArticleInfo;
import com.ln.community.entity.Notice;
import com.ln.community.entity.QuestionComment;
import com.ln.community.entity.QuestionInfo;
import com.ln.community.mapper.NoticeMapper;
import com.ln.community.service.CommentService;
import com.ln.community.service.NoticeService;
import com.ln.community.service.QuestionService;
import com.ln.community.service.TribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
  @Autowired
  CommentService commentService; // 3
  @Autowired
  QuestionService questionService; // 1
  @Autowired
  TribeService tribeService; // 2

  @Override
  public List<Notice> getNotice(String userId) {
    try {
      List<Notice> notices = baseMapper.getNotice(userId);
      for (Notice notice : notices) {
        if (notice.getType() == 1) {
          QuestionInfo questionInfo = this.questionService.getById(notice.getOperateId());
          notice.setTitle(questionInfo.getTitle());
        } else if (notice.getType() == 2) {
          ArticleInfo articleInfo = this.tribeService.getById(notice.getOperateId());
          notice.setTitle(articleInfo.getTitle());
        } else if (notice.getType() == 3) {
          QuestionComment questionComment = this.commentService.getById(notice.getOperateId());
          notice.setTitle(questionComment.getCommentContent());
        }
      }
      return notices;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public boolean addNotice(String operatorId, String userId, String content, String operateId, Integer type) {
    Notice notice = new Notice();
    notice.setContent(content);
    notice.setUserId(userId);
    notice.setOperatorId(operatorId);
    notice.setIsRead(0);
    notice.setOperateTime(new Date());
    notice.setOperateId(operateId);
    notice.setType(type);
    return this.save(notice);
  }
}
