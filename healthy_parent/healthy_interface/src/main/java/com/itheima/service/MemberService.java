package com.itheima.service;

import java.util.List;

public interface MemberService {
    /**
     * 根据月份查找会员数
     * @param monthList
     * @return
     */
    List<Integer> findMemberCountByMonth(List<String> monthList);
}
