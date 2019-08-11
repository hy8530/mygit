package com.itheima.dao;

import com.itheima.pojo.Role;

import java.util.Set;

public interface RoleDao {
    /**
     * 根据用户id查找角色
     * @param id
     * @return
     */
    Set<Role> findRoleByUserId(Integer id);
}
