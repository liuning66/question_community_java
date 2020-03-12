package com.ln.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("operate")
public class Operate {
  @TableId(type= IdType.ASSIGN_UUID)
  String id;
  String userId;
  String articleId;
  Integer isDelete;
  Integer type; // 1 收藏 2 赞
}
