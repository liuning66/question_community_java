package com.ln.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ln.community.entity.QuestionInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionMapper extends BaseMapper<QuestionInfo> {
  List<QuestionInfo> getAllQuestionInfo();
}
