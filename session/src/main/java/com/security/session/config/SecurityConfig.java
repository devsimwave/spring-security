package com.security.session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 보안 관련 필터 체인
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable() // CSRF 보호 비활성화 (개발 중에는 편의를 위해 비활성화, 실제 운영 환경에서는 활성화 권장)

                // spring security 6부터 authorizeRequests() -> authorizeHttpRequests() 변경 람다 사용 가능
                .authorizeHttpRequests(auth -> auth
                    .antMatchers("/users/login", "/static/**").permitAll() // 로그인 페이지와 정적 자원은 모두 접근 허용
                    .antMatchers("/admin/**").hasRole("ADMIN") // /admin/** 경로는 ADMIN 권한 필요
                    .anyRequest().authenticated() // 그 외의 요청은 인증 필요
                )

                .formLogin(form -> form
                        .loginPage("/users/login")    // 커스텀 로그인 페이지 경로
                        .defaultSuccessUrl("/main", true) // 로그인 성공 시 이동할 경로
                        .permitAll()            // 로그인 페이지는 모두 접근 허용
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")   // 로그아웃 처리 경로)
//                        .logoutSuccessUrl("/login") // 로그아웃 성공 시 이동할 경로
                        .invalidateHttpSession(true)    // 세션 무효화
                        .deleteCookies("JSESSIONID") // was 고유 세션 식별자인 JSESSIONID 쿠키 삭제
                        .permitAll()  // 로그아웃은 모두 접근 허용
                );
        return httpSecurity.build();



    }



}
