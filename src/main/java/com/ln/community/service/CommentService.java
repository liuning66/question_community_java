package com.ln.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ln.community.entity.QuestionComment;

public interface CommentService extends IService<QuestionComment> {
    boolean commentQuestion(String questionId, String userId, String content);
}
