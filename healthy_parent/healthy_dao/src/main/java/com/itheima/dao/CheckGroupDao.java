package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {

    /**
     * 新增检查组，返回自增主键id
     * @param checkGroup
     * @return
     */
    Integer addCheckGroup(CheckGroup checkGroup);

    /**
     * 新增中间表记录
     * @param map
     */
    void addCheckGroupAndItem(Map<String,Integer> map);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<CheckItem> findByCondition(String queryString);

    /**
     * 根据id查询检查组
     * @param id
     * @return
     */
    CheckGroup findGroupById(Integer id);

    /**
     * 根据id查询中间表记录
     * @param id
     * @return
     */
    List<CheckItem> findGroupAndItemByGroupId(Integer id);

    /**
     * 更新检查组
     * @param checkGroup
     */
    void update(CheckGroup checkGroup);

    /**
     * 根据id删除中间表的记录
     * @param groupId
     */
    void deleteGroupAndItemById(Integer groupId);

    /**
     * 根据id删除检查组记录
     * @param id
     */
    void deleteGroupById(Integer id);

    /**
     * 查询所有检查组
     * @return
     */
    List<CheckGroup> findAllGroups();
}
