package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckGroupService {
    /**
     * 新增检查组
     * @param checkitemIds
     * @param checkGroup
     */
    void add(List<Integer> checkitemIds, CheckGroup checkGroup);

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

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
     * 编辑检查组
     * @param checkitemIds
     * @param checkGroup
     */
    void update(List<Integer> checkitemIds, CheckGroup checkGroup);

    /**
     * 删除检查组
     * @param id
     */
    void deleteGroup(Integer id);

    /**
     * 查询所有检查组
     * @return
     */
    List<CheckGroup> findAllGroups();
}
