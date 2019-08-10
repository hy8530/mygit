package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private OrderService orderService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public Result submit(@RequestBody Map map){
        try {
            map.put("orderType","微信预约");
            String telephone = (String) map.get("telephone");
            // 校验验证码
            String tableCode = (String) map.get("validateCode");
            String redisCode = jedisPool.getResource().get(RedisMessageConstant.SENDTYPE_ORDER+"_"+telephone);

            if ((!StringUtils.isEmpty(tableCode) && !StringUtils.isEmpty(redisCode) && tableCode.equals(redisCode))){
                Result result = orderService.submit(map);
                return result;
            }else {
                return new Result(false, MessageConstant.VALIDATECODE_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDER_FAIL);
        }
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public Result findById(Integer id){
        try {
            Map<String,Object> map = orderService.findById(id);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
