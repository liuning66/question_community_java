package com.ln.community.controller;

import com.ln.community.entity.LoginResult;
import com.ln.community.entity.User;
import com.ln.community.service.UserService;
import com.ln.community.util.CryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class LoginController {
  @Autowired
  private UserService userService;
  @PostMapping("")
  public LoginResult login(String username, String password) {
    LoginResult result = new LoginResult();
    User newUser = new User();
    if(!this.userService.existUser(username)) {
      User user = this.userService.getUser(username);
      String newPwd = CryptoUtil.encryptStr(password,user.getSecretKey());
      if(newPwd.equals(user.getPassword())) {
        result.setCode(200);
        result.setMsg("登录成功");
        newUser.setId(user.getId());
        newUser.setToken(user.getToken());
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setTelephone(user.getTelephone());
        result.setRecord(newUser);
      } else {
        result.setCode(400);
        result.setMsg("密码错误!");
      }
    } else {
      result.setCode(400);
      result.setMsg("用户名不存在!");
    }
    return result;
  }
}
