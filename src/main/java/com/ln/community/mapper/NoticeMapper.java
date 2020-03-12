package com.ln.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ln.community.entity.Notice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NoticeMapper extends BaseMapper<Notice> {
  List<Notice> getNotice(@Param("userId") String userId);
}
