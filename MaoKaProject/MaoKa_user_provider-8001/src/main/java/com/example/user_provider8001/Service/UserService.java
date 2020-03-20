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
        return userMapper.isExist(user);
    }

    @Override
    public boolean isExistById(int id) {
        return userMapper.isExist(id);
    }

    @Override
    public boolean isExistByAccount(String account) {
        return userMapper.isExist(account);
    }

    @Override
    public boolean addUser(User user) {
        if(userMapper.isExist(user.getAccount()))
            return false;
        else
            return userMapper.insertUser(user);
    }

    @Override
    public User getUserById(int id) {
        if(userMapper.isExist(id))
            return userMapper.selectUser(id);
        return null;
    }

    @Override
    public User getUserByAccount(String account) {
        if(userMapper.isExist(account))
            return userMapper.selectUser(account);
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        if(userMapper.isExist(user.getId()))
            return userMapper.updateUser(user);
        return false;
    }

    @Override
    public boolean deleteUserById(int id) {
        if(userMapper.isExist(id))
            return userMapper.deleteUser(id);
        return false;
    }

}
