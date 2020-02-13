package com.ln.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ln.community.entity.User;

public interface UserService extends IService<User> {
   boolean existUser(String username);
   User getUser(String username);
}
