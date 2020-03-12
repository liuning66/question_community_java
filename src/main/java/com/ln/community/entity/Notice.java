package com.ln.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("notice")
public class Notice {
  @TableId(type= IdType.ASSIGN_UUID)
  String id;
  String content;
  String userId;
  String operatorId;
  Integer isRead;
  Date operateTime;
  @TableField(exist = false)
  String nickname;
  String operateId; // 操作的文章 或评论的id
  Integer type;  // 1 问题 2 文章 3 评论
  @TableField(exist = false)
  String title; // 被操作的标题
}
