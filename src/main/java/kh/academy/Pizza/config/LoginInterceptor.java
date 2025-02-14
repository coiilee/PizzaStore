package kh.academy.Pizza.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession(); // 로그인을 했다면 request 에서 getsession 에 로그인 한 정보가 무엇인지 요청 후 가져올 것
        //위 작성된 session 에는 사용자가 로그인을 했다면 로그인한 정보가 담겨 있을 것.

        User user = (User) session.getAttribute("user"); //만약에 로그인해서 가져온 정보가 user 라면

        if (user ==null) {
            response.sendRedirect("/");
            return false; // 유저가 null 값이어서 로그인이 안되어 있으면 메인 페이지로 리다이렉트
        }

        return true; //유저로 로그인이 된 경우가 확인되면 요청 허용
    }

}
