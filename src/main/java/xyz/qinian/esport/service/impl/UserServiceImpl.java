package xyz.qinian.esport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.qinian.esport.domain.User;
import xyz.qinian.esport.mapper.UserMapper;
import xyz.qinian.esport.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.selectByTelAndPassword(user.getTel(), user.getPassword());
    }

    @Override
    public int update(User user) {
        return userMapper.updateByTelSelective(user);
    }

    @Override
    public User queryUserMessage(String tel) {
        return userMapper.selectByPrimaryKey(tel);
    }

    @Override
    public int registerUser(User user) {
        return userMapper.insertSelective(user);
    }
}
