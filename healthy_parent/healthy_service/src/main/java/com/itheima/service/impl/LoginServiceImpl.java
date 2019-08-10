package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.MemberDao;
import com.itheima.entity.Result;
import com.itheima.pojo.Member;
import com.itheima.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import java.util.Date;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private MemberDao memberDao;

    /**
     * 手机快速登录
     * @param map
     * @return
     */
    @Override
    public Result login(Map map) {
        // 判断用户是否为会员，不是则快速注册
        System.out.println(1111111111);
        String telephone = (String) map.get("telephone");
        Member member = memberDao.isMember(telephone);
        if (member == null){
            member = new Member();
            member.setPhoneNumber(telephone);
            member.setRegTime(new Date());
            memberDao.add(member);
        }
        return new Result(true, MessageConstant.LOGIN_SUCCESS);
    }
}
