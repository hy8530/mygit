package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberDao memberDao;

    /**
     * 根据月份查找会员
     * @param monthList
     * @return
     */
    @Override
    public List<Integer> findMemberCountByMonth(List<String> monthList) {
        List<Integer> monthCount = new ArrayList<>();
        for (String month : monthList) {
            month = month + "-31";
            Integer count = memberDao.findCountByMonth(month);
            monthCount.add(count);
        }
        return monthCount;
    }
}
