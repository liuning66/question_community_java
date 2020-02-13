package com.ln.community.config.croseConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CroseConfig {
  @Bean
  public CorsFilter CorsFilter(){
    CorsConfiguration conf=new CorsConfiguration();

    //请求头允许携带的字段
    conf.addAllowedHeader("*");
    conf.addExposedHeader("Access-control-Allow-Headers");


    //请求允许的方法
    conf.addAllowedMethod("*");
    conf.addAllowedMethod("Access-control-Allow-Methods");

    //允许哪些原始域及逆行跨域访问
    conf.addAllowedOrigin("*");
    conf.addAllowedMethod("Access-control-Allow-Origin");

    //允许访问携带cookie信息
    conf.setAllowCredentials(true);
    conf.addExposedHeader("Access-control-Allow-Credentials");

        /*假如请求服务器并在a时刻返回响应结果,则在max-age规定的秒数内，浏览器不会发送对应的请求到服务区没数据由缓
        存直接返回，超过这一时间段才进一步由服务器决定是返回数据还是仍由缓存提供*/
    conf.setMaxAge(3600L);
    conf.addExposedHeader("Access-control-Max-Age");

    conf.addExposedHeader("xy-token");
    UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**",conf);//Cros 配置对象所有接口都有效
    return new CorsFilter(source);
  }
}
