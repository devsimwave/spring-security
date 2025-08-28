package com.security.session.handler;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class FailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, org.springframework.security.core.AuthenticationException exception) throws java.io.IOException, javax.servlet.ServletException {
        // 로그인 실패 후 처리 로직 작성
        request.getSession().setAttribute("loginError", "아이디 또는 비밀번호가 일치하지 않습니다.");
        response.sendRedirect("/login?error"); // 예: 로그인 페이지로 리다이렉트하면서 에러 파라미터 추가
    }
}
