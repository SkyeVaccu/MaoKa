package com.example.user_provider8001.Service;

import Entity.User;
import Service.IUserService;
import com.example.user_provider8001.Dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean isExistByUser(User user) {
        return userMapper.isExistByUser(user);
    }

    @Override
    public boolean isExistById(int id) {
        return userMapper.isExistById(id);
    }

    @Override
    public boolean isExistByAccount(String account) {
        return userMapper.isExistByAccount(account);
    }

    @Override
    public boolean addUser(User user) {
        if(userMapper.isExistByAccount(user.getAccount()))
            return false;
        else
            return userMapper.insertUser(user);
    }

    @Override
    public User getUserById(int id) {
        if(userMapper.isExistById(id))
            return userMapper.selectUserById(id);
        return null;
    }

    @Override
    public User getUserByAccount(String account) {
        if(userMapper.isExistByAccount(account))
            return userMapper.selectUserByAccount(account);
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        if(userMapper.isExistById(user.getId()))
            return userMapper.updateUser(user);
        return false;
    }

    @Override
    public boolean deleteUserById(int id) {
        if(userMapper.isExistById(id))
            return userMapper.deleteUser(id);
        return false;
    }

}
