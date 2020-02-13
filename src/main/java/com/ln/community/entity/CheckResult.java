package com.ln.community.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckResult implements Serializable {
  Boolean success;
  String errorCode;
}
