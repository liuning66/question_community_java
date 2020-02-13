package com.ln.community.util;

import com.ln.community.entity.CheckResult;
import io.jsonwebtoken.*;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JwtUtils {
  private static long EXPIRATION_DATE = 30 * 24 * 60 * 60 * 1000L;  // 过期时间
  public static String createJWT(String user, String key) {
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    long curMills = System.currentTimeMillis();
    // 生成当前时间
    Date now = new Date(curMills);
    //设置过期时间
    Date expDate = new Date(curMills + EXPIRATION_DATE);
    SecretKey secretKey = generalKey(key);
    JwtBuilder jwtBuilder = Jwts.builder()
            .setIssuer(user) //签发者
            .setIssuedAt(now) //签名时间
            .setExpiration(expDate) // 过期时间
            .signWith(signatureAlgorithm, secretKey); // 签名算法，密钥
    return jwtBuilder.compact();
  }

  public static CheckResult validateJWT(String jwtStr, String key) {
    CheckResult checkResult = new CheckResult();
    Claims claims = null;
    try{
      claims = parseJWT(jwtStr,key);
      checkResult.setSuccess(true);
    } catch (ExpiredJwtException e) {
      checkResult.setErrorCode("token已过期，请重新登录!");
      checkResult.setSuccess(false);
    } catch (Exception e) {
      checkResult.setErrorCode("token校验失败，请重试!");
      checkResult.setSuccess(false);
    }
    return checkResult;
  }


  private static Claims parseJWT(String jwt,String key) throws Exception {
    SecretKey secretKey = generalKey(key);
    return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(jwt)
            .getBody();
  }

  private static SecretKey generalKey(String key) {
    byte[] encodedKey = Base64.decode(key.getBytes());
    return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
  }
}
