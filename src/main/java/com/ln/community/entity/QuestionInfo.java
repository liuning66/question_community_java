package com.ln.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@TableName("question")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionInfo {
  @TableId(type= IdType.ASSIGN_UUID)
  String id; // 问题id
  @TableField("userId")
  String userId; // 用户id
  @TableField(exist = false)
  String nickname; // 提问人昵称
  String title; // 问题标题
  String content; // 问题内容
  Date questionTime; // 提问时间
  Integer commentNum; // 评论数
  Integer approve; // 点赞数
  String bestAnswerId; // 最佳评论id
  Integer status; // 问题状态   1 未解决 2  已解决
  @TableField("isPass")
  Integer isPass; // 审核状态  1 待审核 2 审核通过 3 禁用
  @TableField(exist = false)
  boolean hasBestAnswer; //是否已有最佳回答
  @TableField(exist = false)
  boolean isFavorite; //当前用户是否已关注
}
