package com.ln.community.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // 过滤为null的空属性
public class LoginResult implements Serializable {
  private Integer code;
  private String msg;
  private User record;
}
