# Spring Security + 세션 인증 예제

Spring Boot 3.5.5 / Gradle  / JDK 17 환경에서 구현한 **세션 기반 인증 학습 프로젝트**입니다.  
Thymeleaf를 사용하여 간단한 로그인 페이지를 제공하며, Spring Security를 활용한 인증 및 권한 관리를 다룹니다.

---

## 🚀 개발 환경

- Java 17
- Spring Boot 2.7.7
- Gradle 8.14.3
- Spring Security
- H2 Database (학습용)

---

## ✨ 주요 기능

- 로그인 페이지
- 세션 기반 인증
- 사용자 권한 관리

---

```
🔐 인증 흐름
1.	클라이언트가 /users/login 요청 (ID/PW)
2.	2. `AuthenticationFilter`가 요청을 가로챈다. 이 때 가로챈 정보를 통해 `UsernamePasswordAuthenticationToken` 객체(사용자가 입력한 데이터를 기반으로 생성, 즉 현 상태는 미검증 Authentication)가 생성 후 AuthenticationManager에 전달
3.	3. AuthenticationManager는 등록된 AuthenticationProvider들을 조회해 인증 요구
4.  4. AuthenticationProvider는 UserDetailsService에서 입력 받은 아이디에 대한 정보를 DB로부터 조회함
5.  5. UserDetailsService는 넘겨받은 정보를 통해 DB에서 찾은 사용자 정보인  `UserDetails` 객체를 생성
6.  6. `AuthenticationProvider`는 `UserDetails`를 넘겨받고 사용자 정보를 비교
3.	7. 인증이 완료되면, 인증된 UserNameAuthenticationToken을 AuthenticationManager로 반환함
```

---
🛠️ 세팅 방법
1.	H2 데이터베이스 생성
Spring Boot 실행 시 H2 인메모리 DB가 자동으로 생성됩니다.
2.	테스트 데이터 로딩
src/main/resources/data.sql 파일 내부의 SQL 문을 참고하여 사용자 데이터를 삽입합니다.
3.	Spring Boot 애플리케이션 실행
4.  postman 등 API 클라이언트를 사용하여 /api/auth/login 엔드포인트로 로그인 요청을 보냅니다.

