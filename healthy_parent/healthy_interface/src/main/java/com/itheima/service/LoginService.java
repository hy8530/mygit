package com.itheima.service;

import com.itheima.entity.Result;

import java.util.Map;

public interface LoginService {

    /**
     * 手机快速登录
     *
     * @param map
     * @return
     */
    Result login(Map map);

}
