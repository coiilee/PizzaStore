package kh.academy.Pizza.service;

import kh.academy.Pizza.mapper.UserMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


@Autowired
private UserMapper userMapper;

@Override
    public User findUserId(String userId) {
    return userMapper.findUserId(userId);
    }
@Override
    public User loginUser(String userId, String password) {
    return userMapper.loginUser(userId, password);
    }

}
