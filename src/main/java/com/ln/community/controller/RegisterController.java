package com.ln.community.controller;

import com.ln.community.entity.Result;
import com.ln.community.entity.User;
import com.ln.community.service.impl.UserServiceImpl;
import com.ln.community.util.CryptoUtil;
import com.ln.community.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
@RestController
@RequestMapping("/reg")
public class RegisterController {
  @Autowired
  private UserServiceImpl userServiceImpl;

  /**
   *  用户注册
   */
  @PostMapping("")
  public Result register(User user) {
    Result result = new Result();
    String key = CryptoUtil.generateKey(); //生成key
    String newPwd = CryptoUtil.encryptStr(user.getPassword(),key);
    String token = JwtUtils.createJWT(user.getUsername(),newPwd);
    Date date = new Date();
    user.setPassword(newPwd);
    user.setSecretKey(key);
    user.setToken(token);
    user.setCreateTime(date);
    user.setState(0);
    boolean res = this.userServiceImpl.save(user);
    if(res) {
      result.setStatus(200);
    } else {
      result.setStatus(400);
      result.setErrorMsg("insert error!");
    }
    return result;
  }

  /**
   * 用户名是否存在
   * @param username 用户名
   * @return boolean
   */
  @PostMapping("/validateUser")
  public boolean validateUser(String username) {
    return this.userServiceImpl.existUser(username);
  }
}
