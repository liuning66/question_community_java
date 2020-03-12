package com.ln.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ln.community.entity.Follow;
import com.ln.community.entity.QuestionComment;
import com.ln.community.entity.QuestionInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionMapper extends BaseMapper<QuestionInfo> {
  List<QuestionInfo> getAllQuestionInfo();
  List<QuestionComment> getQuestionComments(@Param("questionId") String questionId);
  Follow getFollowInfo(@Param("questionId") String questionId, @Param("userId") String userId); // 获取关注信息
  List<QuestionInfo> getMyFollowList(@Param("userId") String userId);
}
