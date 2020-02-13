package com.ln.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ln.community.entity.User;
import com.ln.community.mapper.UserMapper;
import com.ln.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
  @Autowired
  private UserMapper userMapper;
  @Override
  public boolean existUser(String username) {
    List<User> user = this.userMapper.existUser(username);
    return user.size() <= 0;
  }

  @Override
  public User getUser(String username) {
    return this.userMapper.getUser(username);
  }

}
