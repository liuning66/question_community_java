package com.ln.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ln.community.entity.QuestionComment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper extends BaseMapper<QuestionComment> {
}
