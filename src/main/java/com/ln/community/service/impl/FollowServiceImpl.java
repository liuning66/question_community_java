package com.ln.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ln.community.entity.Follow;
import com.ln.community.entity.QuestionInfo;
import com.ln.community.mapper.FollowMapper;
import com.ln.community.service.FollowService;
import com.ln.community.service.NoticeService;
import com.ln.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {
  @Autowired
  QuestionService questionService;
  @Autowired
  NoticeService noticeService;

  @Override
  public Follow getFollowInfo(String questionId, String userId) {
    return baseMapper.getFollow(questionId,userId);
  }

  @Override
  @Transactional
  public boolean followQuestion(Integer type,String questionId, String userId) {
    QuestionInfo questionInfo = this.questionService.getById(questionId);
    Follow follow = this.getFollowInfo(questionId, userId);
    boolean followFlag;
    boolean questionFlag;
    if (follow != null) {
      if (type == 0) {
        follow.setIsDelete(1);
        questionInfo.setApprove(questionInfo.getApprove()-1);
      } else if (type == 1) {
        follow.setIsDelete(0);
        questionInfo.setApprove(questionInfo.getApprove()+1);
        this.noticeService.addNotice(userId,questionInfo.getUserId(),"关注了您的问题",questionId,1);
      }
      followFlag = this.updateById(follow);
      questionFlag = this.questionService.updateById(questionInfo);

    } else {
      Follow newFollow = new Follow();
      newFollow.setQuestionId(questionId);
      newFollow.setUserId(userId);
      newFollow.setIsDelete(0);
      followFlag = this.save(newFollow);
      questionInfo.setApprove(questionInfo.getApprove()+1);
      questionFlag = this.questionService.updateById(questionInfo);
      this.noticeService.addNotice(userId,questionInfo.getUserId(),"关注了您的问题",questionId,1);
    }
    return (followFlag && questionFlag);
  }
}
