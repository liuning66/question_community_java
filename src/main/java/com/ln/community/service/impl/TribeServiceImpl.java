package com.ln.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ln.community.entity.*;
import com.ln.community.mapper.TribeMapper;
import com.ln.community.service.OperateService;
import com.ln.community.service.TribeService;
import com.ln.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TribeServiceImpl extends ServiceImpl<TribeMapper, ArticleInfo> implements TribeService {
  @Autowired
  UserService userService;
  @Autowired
  OperateService operateService;

  @Override
  public boolean addArticle(String userId, String title, String content) {
    ArticleInfo articleInfo = new ArticleInfo();
    User user = this.userService.selectUserById(userId);
    boolean status = false;
    if (user != null) {
      articleInfo.setUserId(userId);
      articleInfo.setCommentNum(0);
      articleInfo.setIsPass(1);
      articleInfo.setTitle(title);
      articleInfo.setContent(content);
      articleInfo.setCreateTime(new Date());
      articleInfo.setApprovalNum(0);
      status = this.save(articleInfo);
    }
    return status;
  }

  @Override
  public List<ArticleInfo> getAllArticle(String userId) {
    List<ArticleInfo> articleInfos = baseMapper.getAllArticle();
    for (ArticleInfo articleInfo : articleInfos) {
      Operate collection = this.operateService.getCollection(articleInfo.getId(), userId, 1);
      Operate approval = this.operateService.getCollection(articleInfo.getId(), userId, 2);

      if (collection != null) {
        articleInfo.setCollection(true);
      } else {
        articleInfo.setCollection(false);
      }

      if (approval != null) {
        articleInfo.setApproval(true);
      } else {
        articleInfo.setApproval(false);
      }
    }
    return articleInfos;
  }

  @Override
  @Transactional
  public ArticleDetail getArticleDetail(String articleId, String userId) {
    ArticleDetail articleDetail = new ArticleDetail();
    try {
      ArticleInfo articleInfo = this.getById(articleId);
      Operate collection = this.operateService.getCollection(articleInfo.getId(), userId, 1);
      Operate approval = this.operateService.getCollection(articleInfo.getId(), userId, 2);
      if (collection != null) {
        articleInfo.setCollection(true);
      } else {
        articleInfo.setCollection(false);
      }
      if (approval != null) {
        articleInfo.setApproval(true);
      } else {
        articleInfo.setApproval(false);
      }
      List<QuestionComment> questionComments = baseMapper.getArticleComments(articleId);
      articleDetail.setArticleInfo(articleInfo);
      articleDetail.setComments(questionComments);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return articleDetail;
  }

  @Override
  public List<ArticleInfo> getMyCollectionList(String userId) {
    return baseMapper.getMyCollectionList(userId);
  }
}
