package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.aliyuncs.exceptions.ClientException;

public interface ValidateCodeService {
    /**
     * 预约发送验证码
     * @param telephone
     */
    void send4Order(String telephone) throws ClientException;

    /**
     * 登录发送验证码
     * @param telephone
     */
    void send4Login(String telephone);
}
