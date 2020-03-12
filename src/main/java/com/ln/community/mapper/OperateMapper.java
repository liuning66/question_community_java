package com.ln.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ln.community.entity.Operate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OperateMapper extends BaseMapper<Operate> {
  Operate getCollectionInfo(@Param("articleId") String articleId, @Param("userId") String userId, @Param("type") Integer type);

  Operate getCollection(@Param("articleId") String articleId, @Param("userId") String userId, @Param("type") Integer type);

}
