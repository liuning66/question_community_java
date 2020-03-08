package com.ln.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("follow")
public class Follow {
  @TableId(type= IdType.ASSIGN_UUID)
  String id;
  @TableField("userId")
  String userId;
  @TableField("questionId")
  String questionId;
  Integer isDelete;
}
