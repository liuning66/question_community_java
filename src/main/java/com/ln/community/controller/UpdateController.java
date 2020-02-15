package com.ln.community.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ln.community.entity.LoginResult;
import com.ln.community.entity.User;
import com.ln.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/update")
public class UpdateController {
  @Autowired
  private UserService userService;
  @PostMapping("/nickname")
  public LoginResult updateNickName(String id, String nickname) {
    boolean isExists = true; // 昵称是否可用
    LoginResult loginResult = new LoginResult();
    User user = new User();
    if(nickname != null && nickname.length() > 0) {
       isExists = this.userService.existUser(nickname,1);
    } else {
      loginResult.setCode(400);
      loginResult.setMsg("用户名不合法!");
    }
   if(isExists) {
     user.setId(id);
     user.setNickname(nickname);
     boolean updateResult = this.userService.updateById(user);
     if(updateResult) {
       loginResult.setCode(200);
       loginResult.setMsg("update nickname success!");
       loginResult.setRecord(this.userService.getById(id));
     }
   } else {
     loginResult.setCode(400);
     loginResult.setMsg("该昵称已存在!");
   }
    return loginResult;
  }
}