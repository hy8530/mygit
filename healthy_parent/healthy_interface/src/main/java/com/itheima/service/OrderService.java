package com.itheima.service;

import com.itheima.entity.Result;

import java.util.Map;

public interface OrderService {
    /**
     * 预约
     * @param map
     * @return
     */
    Result submit(Map map) throws Exception;

    /**
     * 返回成功信息
     * @param id
     * @return
     */
    Map<String,Object> findById(Integer id);

    /**
     * 根据套餐id查找预约该套餐的数量
     * @param id
     * @return
     */
    Integer findCountByStemealId(Integer id);

}
