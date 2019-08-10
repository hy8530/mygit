package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Reference
    private LoginService loginService;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 校验手机快速登录
     * @param map
     * @return
     */
    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public Result check(HttpServletResponse response, @RequestBody Map map){
        try {
            String telephone = (String) map.get("telephone");
            String formCode = (String) map.get("validateCode");
            String redisCode = jedisPool.getResource().get(RedisMessageConstant.SENDTYPE_LOGIN+"_"+telephone);
            // 校验验证码
            if ((!StringUtils.isEmpty(formCode) && !StringUtils.isEmpty(redisCode) && formCode.equals(redisCode))){
                Result result = loginService.login(map);
                Cookie cookie = new Cookie("Login_member_telephone",telephone);
                cookie.setPath("/");
                cookie.setMaxAge(24*60*60*30);
                response.addCookie(cookie);
                return result;
            }else {
                return new Result(false, MessageConstant.VALIDATECODE_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.LOGIN_FAIL);
        }
    }
}
