package com.ln.community;

import cn.hutool.core.convert.Convert;
import com.ln.community.util.CryptoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class CommunityApplicationTests {
  @Test
  void contextLoads() {
    String secret_key = CryptoUtil.generateKey();
    System.out.println(secret_key);
    String encrypt = CryptoUtil.encryptStr("abc",secret_key);
    System.out.println(encrypt);
  }

}
