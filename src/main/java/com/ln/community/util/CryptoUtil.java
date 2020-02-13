package com.ln.community.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

public class CryptoUtil {
  private static AES aes = null;

  public static String encryptStr(String content, String...key) {
    byte[] keys = (key.length == 0) ? generateKey().getBytes() : key[0].getBytes();
    aes = SecureUtil.aes(keys);
    return aes.encryptHex(content);
  }

  public static String decryptStr(String encryptHex) {
    return aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
  }

  public static String generateKey() {
    return RandomUtil.randomString(16);
  }

}
