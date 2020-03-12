package com.ln.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ln.community.entity.*;
import com.ln.community.mapper.OperateMapper;
import com.ln.community.service.NoticeService;
import com.ln.community.service.OperateService;
import com.ln.community.service.TribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperateServiceImpl extends ServiceImpl<OperateMapper, Operate> implements OperateService {
  @Autowired
  TribeService tribeService;
  @Autowired
  NoticeService noticeService;

  @Override
  public Operate getCollectionInfo(String articleId, String userId, Integer type) {
    return baseMapper.getCollectionInfo(articleId, userId, type);
  }

  @Override
  public Operate getCollection(String articleId, String userId, Integer type) {
    return baseMapper.getCollection(articleId, userId, type);
  }

  @Override
  @Transactional
  public boolean operateArticle(String articleId, String userId, Integer operateType, Integer type) {
    ArticleInfo articleInfo = this.tribeService.getById(articleId);
    Operate operate = this.getCollectionInfo(articleId, userId, operateType);
    boolean operateFlag;
    boolean articleFlag;
    if (operateType == 1) {
      if (operate != null) {
        if (type == 0) {
          operate.setIsDelete(1);
        } else if (type == 1) {
          this.noticeService.addNotice(userId, articleInfo.getUserId(), "收藏了您的文章",articleId,2);
          operate.setIsDelete(0);
        }
        operateFlag = this.updateById(operate);
      } else {
        Operate newOperate = new Operate();
        newOperate.setArticleId(articleId);
        newOperate.setUserId(userId);
        newOperate.setIsDelete(0);
        newOperate.setType(1);
        operateFlag = this.save(newOperate);
        this.noticeService.addNotice(userId,articleInfo.getUserId(),"收藏了您的文章",articleId,2);
      }
      return operateFlag;
    } else if (operateType == 2) {
      if (operate != null) {
        if (type == 0) {
          operate.setIsDelete(1);
          articleInfo.setApprovalNum(articleInfo.getApprovalNum() - 1);
        } else if (type == 1) {
          operate.setIsDelete(0);
          this.noticeService.addNotice(userId, articleInfo.getUserId(), "赞了您的文章",articleId,2);
          articleInfo.setApprovalNum(articleInfo.getApprovalNum() + 1);
        }
        operateFlag = this.updateById(operate);
        articleFlag = this.tribeService.updateById(articleInfo);
      } else {
        Operate newOperate = new Operate();
        newOperate.setArticleId(articleId);
        newOperate.setUserId(userId);
        newOperate.setIsDelete(0);
        newOperate.setType(2);
        operateFlag = this.save(newOperate);
        articleInfo.setApprovalNum(articleInfo.getApprovalNum() + 1);
        articleFlag = this.tribeService.updateById(articleInfo);
        this.noticeService.addNotice(userId, articleInfo.getUserId(), "赞了您的文章",articleId,2);
      }
      return (operateFlag && articleFlag);
    } else {
      return false;
    }
  }
}
