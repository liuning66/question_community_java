package com.ln.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ln.community.entity.Follow;

public interface FollowService  extends IService<Follow> {
  Follow getFollowInfo(String questionId, String userId);
  boolean followQuestion(Integer type,String questionId, String userId);
}

