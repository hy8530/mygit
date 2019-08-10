package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService{

    @Autowired
    private CheckGroupDao checkGroupDao;

    /**
     * 新增检查组
     * @param checkitemIds
     * @param checkGroup
     */
    @Override
    public void add(List<Integer> checkitemIds, CheckGroup checkGroup) {
        // 先增加检查组,返回自增主键，id
        checkGroupDao.addCheckGroup(checkGroup);
        Integer groupId = checkGroup.getId();
        // 再增加中间表，
        this.addCheckGroupAndItem(groupId,checkitemIds);
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkGroupDao.findByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 根据id查询检查组
     * @param id
     * @return
     */
    @Override
    public CheckGroup findGroupById(Integer id) {
        return checkGroupDao.findGroupById(id);
    }

    /**
     * 根据id查询中间表记录
     * @param id
     * @return
     */
    @Override
    public List<CheckItem> findGroupAndItemByGroupId(Integer id) {
        return checkGroupDao.findGroupAndItemByGroupId(id);
    }

    /**
     * 编辑检查组
     * @param checkitemIds
     * @param checkGroup
     */
    @Override
    public void update(List<Integer> checkitemIds, CheckGroup checkGroup) {
        Integer groupId = checkGroup.getId();
        // 更新检查组
        checkGroupDao.update(checkGroup);
        // 根据id删除中间关联
        checkGroupDao.deleteGroupAndItemById(groupId);
        // 根据id和checjitemIds添加关联
        this.addCheckGroupAndItem(groupId,checkitemIds);
    }

    /**
     * 删除检查组
     * @param id
     */
    @Override
    public void deleteGroup(Integer id) {
        // 先根据id删除中间表的记录
        checkGroupDao.deleteGroupAndItemById(id);
        // 再根据id删除检查组中的记录
        checkGroupDao.deleteGroupById(id);
    }

    /**
     * 查询所有检查组
     * @return
     */
    @Override
    public List<CheckGroup> findAllGroups() {
        return checkGroupDao.findAllGroups();
    }

    /**
     * 添加中间表记录
     * @param groupId
     * @param checkitemIds
     */
    public void addCheckGroupAndItem(Integer groupId, List<Integer> checkitemIds){
        Map<String,Integer> map = new HashMap<>();
        map.put("groupId",groupId);
        if(checkitemIds != null && checkitemIds.size() > 0){
            for (Integer checkitemId : checkitemIds) {
                map.put("checkitemId",checkitemId);
                checkGroupDao.addCheckGroupAndItem(map);
            }
        }
    }
}
