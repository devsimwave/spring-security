package com.security.session.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    /**
     * Spring Security 로그인 전송 시 AuthenticationFilter로 요청이 먼저 오게 되는데
     * 아이디 비밀번호 기반으로 UsernamePasswordAuthenticationToken(인증 전) 생성한다.
     * 최초 검증 로직을 위해 필터를 만든다.
     *
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password); // UserPasswordAuthenticationToken(인증 전) 생성
        setDetails(request, authRequest);  // 요청 정보 부가 설정 클라이언의 요청 정보가 있다. 대표적으로 sessionId, IP주소 등이 있는데 보안 로그 기록, 추가 보안 정책, 인증 실패 분석 등에 활용한다.
        return this.getAuthenticationManager().authenticate(authRequest); // AuthenticationManager를 UsernamePasswordAuthenticationToken을 넘겨 인증 시도
    }
}
