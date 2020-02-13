package com.ln.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ln.community.entity.User;

public interface UserService extends IService<User> {
   boolean existUser(String validate, Integer type);
   User getUser(String username);
   void updateToken(String id, String token);
}
