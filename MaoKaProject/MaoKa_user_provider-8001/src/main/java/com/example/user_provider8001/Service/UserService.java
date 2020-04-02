package com.example.user_provider8001.Service;

import Entity.User;
import Service.IUserService;
import Utils.RandomUtil;
import com.example.user_provider8001.Dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService implements IUserService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 判断用户是否存在，先从Redis中判断，然后再去数据库查询
     * @param user 传进来的用户
     * @return
     */
    @Override
    public boolean isExistByUser(User user) {
        //判断redis中是否存在,存在直接返回true
        if(user.getPassword().equals(stringRedisTemplate.opsForValue().get("user:"+user.getAccount()+":password")))
            return true;
        //判断是否成功
        else{
            boolean result = userMapper.isExistByUser(user);
            //如果存在就放入到redis中
            if(result) {
                stringRedisTemplate.opsForValue().set("user:" + user.getAccount() + ":password", user.getPassword());
                return true;
            }
            else{
                //如果是三方用户不存在，则完成注册
                if(user.getLoginType()==1){
                    if(userMapper.insertUser(user))
                        return true;
                }
                return false;
            }
        }
    }

    @Override
    public boolean isExistById(int id) {
        return userMapper.isExistById(id);
    }

    @Override
    public boolean isExistByAccount(String account,int loginType) {
        return userMapper.isExistByAccount(account,loginType);
    }

    //如果是猫咖用户注册
    @Override
    public boolean addUser(User user) {
       return userMapper.insertUser(user);
    }

    @Override
    public User getUserById(int id) {
        if(userMapper.isExistById(id))
            return userMapper.selectUserById(id);
        return null;
    }

    @Override
    public User getUserByAccount(String account,int loginType) {
        if(userMapper.isExistByAccount(account,loginType))
            return userMapper.selectUserByAccount(account,loginType);
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
