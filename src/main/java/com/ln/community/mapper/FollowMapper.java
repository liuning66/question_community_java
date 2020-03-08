package com.ln.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ln.community.entity.Follow;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface FollowMapper extends BaseMapper<Follow> {
  Follow getFollow(@Param("questionId") String questionId, @Param("userId") String userId); // 获取关注信息

}
