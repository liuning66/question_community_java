package com.ln.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {
  @TableId(type=IdType.ASSIGN_UUID)
  private String id;
  private String username;
  private String password;
  private String telephone;
  private String email;
  private String token;
  private Integer state; //账号状态
  @TableField("secret_key")
  private String secretKey; //密钥
  private String remark;
  @TableField("create_time")
  private Date createTime;
  private String nickname; // 昵称
}
