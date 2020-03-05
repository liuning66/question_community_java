package com.ln.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ln.community.entity.User;
import com.ln.community.mapper.UserMapper;
import com.ln.community.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
  @Autowired
  private UserMapper userMapper;
  @Override
  public boolean existUser(String validate,Integer type) {
    QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
    if(type == 1){
      queryWrapper.eq("nickname",validate);
    } else if(type == 2) {
      queryWrapper.eq("username",validate);
    }
    List<User> user = this.userMapper.selectList(queryWrapper);
    return user.size() <= 0;
  }


  @Override
  public User getUser(String username) {
    return this.userMapper.getUser(username);
  }

  @Override
  public void updateToken(String id, String token) {
    this.userMapper.updateToken(id,token);
  }

  @Override
  public User selectUserById(String id) {
    User user = this.userMapper.selectById(id);
    if(user != null) {
      return user;
    } else {
      return null;
    }
  }

}
