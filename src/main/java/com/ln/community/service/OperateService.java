package com.ln.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ln.community.entity.Operate;

public interface OperateService extends IService<Operate> {
  Operate getCollectionInfo(String articleId, String userId,Integer type);
  Operate getCollection(String articleId, String userId,Integer type);

  boolean operateArticle(String articleId, String userId,Integer operateType,Integer type);
}
