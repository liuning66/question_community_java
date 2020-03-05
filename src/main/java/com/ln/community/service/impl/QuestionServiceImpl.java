package com.ln.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ln.community.entity.QuestionInfo;
import com.ln.community.mapper.QuestionMapper;
import com.ln.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, QuestionInfo> implements QuestionService {
  @Autowired
  public QuestionMapper questionMapper;

  @Override
  public List<QuestionInfo> getAllQuestionInfo() {
    List<QuestionInfo> questionInfos = this.questionMapper.getAllQuestionInfo();
    if(questionInfos.size() > 0) {
      return questionInfos;
    } else {
      return null;
    }
  }
}
