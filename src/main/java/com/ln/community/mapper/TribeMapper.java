package com.ln.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ln.community.entity.ArticleInfo;
import com.ln.community.entity.QuestionComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TribeMapper extends BaseMapper<ArticleInfo> {
  List<ArticleInfo> getAllArticle();
  List<QuestionComment> getArticleComments(@Param("articleId") String articleId);
  List<ArticleInfo> getMyCollectionList(@Param("userId") String userId);
}
