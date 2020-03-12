package com.ln.community.entity;

import lombok.Data;

import java.util.List;

@Data
public class ArticleDetail {
  ArticleInfo articleInfo;
  List<QuestionComment> comments;
}
