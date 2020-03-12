package com.ln.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ln.community.entity.ArticleDetail;
import com.ln.community.entity.ArticleInfo;
import com.ln.community.entity.QuestionComment;

import java.util.List;

public interface TribeService extends IService<ArticleInfo> {

  boolean addArticle(String userId,String title, String content);

  List<ArticleInfo> getAllArticle(String userId);
  ArticleDetail getArticleDetail(String articleId, String userId);
  List<ArticleInfo> getMyCollectionList(String userId);
}
