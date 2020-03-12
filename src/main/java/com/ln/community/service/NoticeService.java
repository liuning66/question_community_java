package com.ln.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ln.community.entity.Notice;

import java.util.List;

public interface NoticeService extends IService<Notice> {
  List<Notice> getNotice(String userId);
  boolean addNotice(String operatorId,String userId,String content,String operateId,Integer type);
}
