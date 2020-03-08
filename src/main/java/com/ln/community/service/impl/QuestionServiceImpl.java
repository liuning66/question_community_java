package com.ln.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ln.community.entity.Follow;
import com.ln.community.entity.QuestionComment;
import com.ln.community.entity.QuestionDetail;
import com.ln.community.entity.QuestionInfo;
import com.ln.community.mapper.QuestionMapper;
import com.ln.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, QuestionInfo> implements QuestionService {
  @Autowired
  public QuestionMapper questionMapper;

  @Override
  public List<QuestionInfo> getAllQuestionInfo() {
    List<QuestionInfo> questionInfos = this.questionMapper.getAllQuestionInfo();
    if (questionInfos.size() > 0) {
      return questionInfos;
    } else {
      return null;
    }
  }

  @Override
  @Transactional
  public QuestionDetail getQuestionDetail(String questionId, String userId) {
    QuestionDetail questionDetail = new QuestionDetail();
    System.out.println(this);
    try {
      QuestionInfo questionInfo = this.getById(questionId);
      if (questionInfo.getBestAnswerId() != null && questionInfo.getBestAnswerId().length() > 0) {
        questionInfo.setHasBestAnswer(true);
      } else {
        questionInfo.setHasBestAnswer(false);
      }
      Follow follow = baseMapper.getFollowInfo(questionId,userId);
      if(follow != null) {
        questionInfo.setFavorite(true);
      } else  {
        questionInfo.setFavorite(false);
      }
      List<QuestionComment> questionComments = baseMapper.getQuestionComments(questionId);
      questionDetail.setQuestionInfo(questionInfo);
      questionDetail.setQuestionComments(questionComments);
      return questionDetail;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
