package com.security.jwt.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

//  private UserDetailsService

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Value("${jwt.secret}")
  private String secretKey;

  @Value("${jwt.token.access-expiration-time}")
  private long accessExpirationTime;

  @Value("${jwt.token.refresh-expiration-time}")
  private long refreshExpirationTime;

  private Key key;


  public String createToken(Authentication authentication) {

    String authorities = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));

    long now = (new Date()).getTime();
    Date validity = new Date(now + accessExpirationTime); // 30분 유효

    return Jwts.builder()
        .setSubject(authentication.getName())
        .claim("ROLE", authorities)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }


  // Request Header 에서 토큰 정보를 꺼내오기 위한 메소드
  public String resolveToken(HttpServletRequest request) {

    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  // 토큰의 유효성 검사
  public Claims validateToken(String token) {
    try {
      logger.info("secretKey : " + Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token));
      return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    } catch (ExpiredJwtException e) {
      throw new IllegalArgumentException("만료된 토큰입니다.");
    } catch (JwtException e) {
      throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
    }
  }

  // 토큰으로 클레임을 만들고 이를 이용해 유저 객체를 만들어서 최종적으로 authentication 객체를 리턴
  public Authentication getAuthentication(String token) {

    Claims claims = Jwts
        .parserBuilder()
        .setSigningKey(secretKey)
        .build()
        .parseClaimsJws(token)
        .getBody();

    Collection<? extends GrantedAuthority> authorities =
        Arrays.stream(claims.get("ROLE").toString().split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

    User principal = new User(claims.getSubject(), "", authorities);

    return new UsernamePasswordAuthenticationToken(principal, token, authorities);
  }
}
