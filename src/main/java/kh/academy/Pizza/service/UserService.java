package kh.academy.Pizza.service;

import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface UserService {


//    User findUserId(String userId);
//    User loginUser(String userId, String password);

    User findUserId(@Param("userId")String userId);
    User loginUser(@Param("userId")String userId,@Param("userPassword") String password);

}
