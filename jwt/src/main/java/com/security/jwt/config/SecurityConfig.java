//package com.security.jwt.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@RequiredArgsConstructor
//@EnableWebSecurity
//public class SecurityConfig {
//
////    private final JwtTokenProvider jwtTokenProvider;
//
//    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf().disable()  // csrf  보안 x
//
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt token으로 인증 > 세션 필요없음
//
//                .and()
//                .authorizeRequests()    // HttpServletRequest를 사용하는 요청들에 대한 접근제한을 설정
//                .antMatchers("/**").permitAll() // 모든 주소 허용
//                .anyRequest().authenticated() // Authentication 필요한 주소
//
//                .and()                  // exception handling for jwt
//                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint);
//
//
//
//
//
//
//
//
//
//
//
//
//
//    }
//
//}
