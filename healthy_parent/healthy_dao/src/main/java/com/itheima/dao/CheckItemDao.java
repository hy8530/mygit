package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {
    /**
     * 添加检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 根据条件分页查询检查项
     * @param queryString
     * @return
     */
    Page<CheckItem> findByCondition(String queryString);

    /**
     * 根据id查询检查项和检查组是否关联
     * @param itemId
     * @return
     */
    int findCountByItemId(Integer itemId);

    /**
     * 根据id删除检查项
     * @param itemId
     */
    void deleteItem(Integer itemId);

    /**
     * 根据id查询检查项
     * @param id
     * @return
     */
    CheckItem findItemById(Integer id);

    /**
     * 更新检查项
     * @param checkItem
     */
    void update(CheckItem checkItem);

    /**
     * 查找所有检查项
     * @return
     */
    List<CheckItem> getAllItems();
}
