package com.itheima.jobs;

import com.itheima.constant.RedisConstant;
import com.itheima.utils.QiniuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.util.Set;

@Component
public class DeleteImg {

    @Autowired
    private JedisPool jedisPool;

    public void deleteRubbish(){
        // 对redis中两个集合相减，得出垃圾图片集合
        Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        // 遍历集合，删除垃圾
        for (String fileName : set) {
            // 先在七牛云上删除
            QiniuUtil.deleteFileFromQiniu(fileName);
            // 再在redis中删除
            jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
        }
    }
}
