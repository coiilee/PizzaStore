package kh.academy.Pizza.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    private String name;
    private String password;
    private String email;
//    현재 기준 : 유저만 로그인 했는지 확인
//    private String role; 1:관리자 2:기업 3:사용자


}
