package com.ln.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@TableName("comment")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionComment {
  @TableId(type= IdType.ASSIGN_UUID)
  String id; //评论id
  String commentContent; // 评论内容
  Date commentTime; // 评论时间
  @TableField("questionId")
  String questionId; // 问题id
  @TableField(exist = false)
  String nickname; // 评论人昵称
  @TableField("userId")
  String userId; // 评论人id
  Integer approvalNum; //赞成数
  Integer disapprovalNum; //不赞成数
}
