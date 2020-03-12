package com.ln.community.controller;

import com.ln.community.entity.Notice;
import com.ln.community.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("notice")
public class NoticeController {
  @Autowired
  NoticeService noticeService;

  /**
   * 获取通知
   * @param userId 要通知人的id
   * @return 返回通知列表
   */
  @PostMapping("/getNotice")
  public List<Notice> getNotice(String userId) {
    return this.noticeService.getNotice(userId);
  }
}
