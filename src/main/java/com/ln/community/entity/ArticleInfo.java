package com.ln.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tribe")
public class ArticleInfo {
  @TableId(type= IdType.ASSIGN_UUID)
  String id; // 问题id
  @TableField("userId")
  String userId; // 用户id
  @TableField(exist = false)
  String nickname; // 提问人昵称
  String title; // 问题标题
  String content; // 问题内容
  Date createTime; // 提问时间
  Integer commentNum; // 评论数
  Integer approvalNum; // 点赞数
  @TableField("isPass")
  Integer isPass; // 审核状态  1 待审核 2 审核通过 3 禁用
  @TableField(exist = false)
  boolean isCollection; //当前用户是否已关注
  @TableField(exist = false)
  boolean isApproval; // 是否以点赞
}
