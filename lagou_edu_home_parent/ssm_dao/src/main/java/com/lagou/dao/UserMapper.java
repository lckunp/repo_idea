package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {
    /**
     * 用户分页及多条件组合查询
     */
    public List<User> findAllUserByPage(UserVo userVo);

    /**
     * 用户登录（根据用户名查询用户信息）
     */
    public User login(User user);


    /**
     * 根据用户id清空中间表
     */
    public void deleteUserContextRole(Integer id);

    /**
     * 分配角色（向中间表添加记录）
     */
    public void userContextRole(User_Role_relation user_role_relation);

    /**
     * 1.根据用户id查询关联的角色信息
     */
    public List<Role> findUserRelationRoleById(Integer id);

    /**
     * 2.根据角色id查询父菜单(顶级菜单)parent_id为-1
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /**
     * 3.根据父菜单信息对父菜单关联的子菜单进行关联查询(pid)
     */
    public List<Menu> findSubMenuByPid(Integer pid);

    /**
     * 4.获取用户拥有的资源权限信息 本质是根据角色获取
     */
    public List<Resource> findResourceByRoleId(List<Integer> ids);



}
