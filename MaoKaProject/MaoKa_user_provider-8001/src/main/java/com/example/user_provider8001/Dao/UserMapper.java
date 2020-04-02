package com.example.user_provider8001.Dao;

import Entity.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * 通过user的账号和密码判断用户是否存在
     * @param user 传进来的用户
     * @return 结果
     */
    public boolean isExistByUser(User user);

    /**
     * 根据id判断用户是否存在
     * @param id 用户id
     * @return 结果
     */
    public boolean isExistById(int id);

    /**
     * 根据account判断用户是否存在
     * @param account 用户账号
     * @return 结果
     */
    public boolean isExistByAccount(@Param("account") String account,@Param("loginType") int loginType);

    /**
     * 新增一个用户
     * @param user 新增用户的信息
     * @return 结果
     */
    public boolean insertUser(User user);

    /**
     * 通过用户的id获得一个用户
     * @param id 用户的id
     * @return 结果
     */
    public User selectUserById(int id);

    /**
     * 根据用户的账号获得用户
     * @param account 用户的账号
     * @return 结果
     */
    public User selectUserByAccount(@Param("account") String account,@Param("loginType") int loginType);

    /**
     * 更新用户的信息
     * @param user 新的用户信息
     * @return 结果
     */
    public boolean updateUser(User user);

    /**
     * 删除用户
     * @param id 用户的id
     * @return 结果
     */
    public boolean deleteUser(int id);
}
