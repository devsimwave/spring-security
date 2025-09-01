package com.security.session.config.security;

import com.security.session.filter.CustomAuthenticationFilter;
import com.security.session.handler.CustomAuthenticationFailureHandler;
import com.security.session.handler.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * AuthenticationManager 빈 등록
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /** CustomAuthenticationFilter 빈 등록
     * 기본 UsernamePasswordAuthenticationFilter를 대체하거나 확장하는 용도
     * 로그인 URL, 성공/실패 핸들러 등을 커스터마이징
     */
    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter(AuthenticationManager authenticationManager) {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter(authenticationManager);
        filter.setFilterProcessesUrl("/login");
        filter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
        return filter;
    }

    /**
     * 로그인 성공 핸들러 빈 등록
     */
    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    /**
     * 로그인 실패 핸들러 빈 등록
     */
    @Bean
    public CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    /**
     *  SecurityFilterChain 빈 등록
     *  HttpSecurity 객체를 사용하여 보안 설정 구성
     *  Spring boot 2.7.x 이상 변경된 점
     *  - WebSecurityConfigurerAdapter가 deprecated 되어 SecurityFilterChain을 빈으로 등록하여 사용
     *  - SecurityFilterChain 메소드 안에 필요한 bean을 파라미터로 주입받을 수 있다.
     */
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, CustomAuthenticationFilter customAuthenticationFilter) throws Exception {
        httpSecurity
                .csrf().disable() // CSRF 보호 비활성화 (개발 중에는 편의를 위해 비활성화, 실제 운영 환경에서는 활성화 권장)

                .authorizeHttpRequests(auth -> auth
                    .antMatchers("/users/login", "/static/**").permitAll() // 로그인 페이지와 정적 자원은 모두 접근 허용
                    .antMatchers("/admin/**").hasRole("ADMIN") // /admin/** 경로는 ADMIN 권한 필요
                    .anyRequest().authenticated() // 그 외의 요청은 인증 필요
                )

                .formLogin(form -> form // 폼 기반 로그인 설정
                        .loginPage("/users/login")    // 로그인 페이지 경로
                        .permitAll()            // 로그인 페이지는 모두 접근 허용
                )

                .addFilterBefore(customAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")   // 로그아웃 처리 경로)
                        .logoutSuccessUrl("/users/login") // 로그아웃 성공 시 이동할 경로
                        .invalidateHttpSession(true)    // 세션 무효화
                        .deleteCookies("JSESSIONID") // was 고유 세션 식별자인 JSESSIONID 쿠키 삭제
                        .permitAll()  // 로그아웃은 모두 접근 허용
                );
        return httpSecurity.build();

    }

}
