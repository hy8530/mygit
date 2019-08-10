package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.PermissionDao;
import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;
    /**
     * 根据用户名查找用户信息
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        User user = userDao.findUserByUsername(username);
        if (user != null){
            Set<Role> roleSet = roleDao.findRoleByUserId(user.getId());
            if(roleSet != null && roleSet.size()>0){
                for (Role role : roleSet) {
                    Set<Permission> permissionSet = permissionDao.findPerByRoleId(role.getId());
                    if(permissionSet != null && permissionSet.size()>0){
                        role.setPermissions(permissionSet);
                    }
                }
                user.setRoles(roleSet);
            }
            return user;
        }
        return null;
    }
}
