package com.security.jwt.module.auth.controller;

//import com.security.jwt.module.auth.service.AuthService;

import com.security.jwt.config.security.JwtTokenProvider;
import com.security.jwt.module.users.dto.LoginDto;
import com.security.jwt.module.users.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationManagerBuilder authenticationManager;

//  private final AuthService authService;

//  public AuthController(AuthService authService) {
//    this.authService = authService;
//  }

  @GetMapping("test")
  public String test() {
    String a = "sdfjdsfjls";
    return "test";
  }

  @PostMapping("login")
  public TokenDto login(@RequestBody LoginDto body) {

    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

    // authenticate 메소드가 실행이 될 때 customUserDetailsService class의 loadUserByUsername 메소드가 실행
     Authentication authentication = authenticationManager.getObject().authenticate(authenticationToken);

      // authentication 객체를 SecurityContext에 저장
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = jwtTokenProvider.createToken(authentication);

    return new TokenDto(jwt);

  }

}
