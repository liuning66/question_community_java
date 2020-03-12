package com.ln.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ln.community.entity.QuestionDetail;
import com.ln.community.entity.QuestionInfo;

import java.util.List;

public interface QuestionService extends IService<QuestionInfo> {
  List<QuestionInfo> getAllQuestionInfo();

  QuestionDetail getQuestionDetail(String questionId, String userId);

  List<QuestionInfo> getMyFollowList(String userId);
}
