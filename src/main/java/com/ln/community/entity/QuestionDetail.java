package com.ln.community.entity;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDetail {
  QuestionInfo questionInfo;
  List<QuestionComment> questionComments;
}
