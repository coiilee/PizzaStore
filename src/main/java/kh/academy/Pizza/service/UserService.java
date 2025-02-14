package kh.academy.Pizza.service;

import kh.academy.Pizza.dto.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    kh.academy.Pizza.dto.User findUserId(@Param("userId") String userId);

    User loginUser(@Param("userId")String userId,
                   @Param("password")String password);
}