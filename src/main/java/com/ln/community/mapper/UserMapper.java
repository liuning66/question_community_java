package com.ln.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ln.community.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {
   List<User> existUser(@Param("username") String username);
   User getUser(@Param("username") String username);
}
