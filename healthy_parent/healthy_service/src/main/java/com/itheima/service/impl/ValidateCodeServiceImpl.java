package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.aliyuncs.exceptions.ClientException;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.service.ValidateCodeService;
import com.itheima.utils.SMSUtils;
import com.itheima.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService{

    @Autowired
    private JedisPool jedisPool;

    /**
     * 预约发送验证码
     * @param telephone
     */
    @Override
    public void send4Order(String telephone) throws ClientException {
        // 1.生成随机的验证码
        //Integer code = ValidateCodeUtils.generateValidateCode(6);
        Integer code = 123456;
        // 2.发送VALIDATE_CODE短信
        //SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code.toString());
        // 3.将验证码存入redis中
        jedisPool.getResource().setex(RedisMessageConstant.SENDTYPE_ORDER+"_"+telephone,5*60,code.toString());
    }

    /**
     * 登录发sing验证码
     * @param telephone
     */
    @Override
    public void send4Login(String telephone) {
        // 1.生成随机的验证码
        //Integer code = ValidateCodeUtils.generateValidateCode(6);
        Integer code = 123456;
        // 2.发送VALIDATE_CODE短信
        //SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code.toString());
        // 3.将验证码存入redis中
        jedisPool.getResource().setex(RedisMessageConstant.SENDTYPE_LOGIN+"_"+telephone,5*60,code.toString());
    }
}
