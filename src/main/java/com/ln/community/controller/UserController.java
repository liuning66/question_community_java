package com.ln.community.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @RequestMapping("/hello")
  public String say() {
    return "hello springboot!";
  }
}
