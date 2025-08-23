# Spring Security + JWT 인증 예제

Spring Boot 2.7.7 / Gradle / JDK 17 환경에서 구현한 **JWT 기반 인증 학습 프로젝트**입니다.  
세션을 사용하지 않고 Access / Refresh Token을 활용한 **Stateless 인증** 흐름을 구현했습니다.

---

## 🚀 개발 환경

- Java 17
- Spring Boot 2.7.7
- Gradle 8.4.4
- Spring Security
- JJWT (io.jsonwebtoken)
- H2 Database (학습용)

---

## ✨ 주요 기능

- 로그인
- Access Token
- Access Token 만료 시 Refresh Token으로 재발급 (추가 예정)
- 권한(Role) 기반 접근 제어 (예: USER / ADMIN)

---

```
🔐 인증 흐름
1.	클라이언트가 /api/auth/login 요청 (ID/PW)
2.	서버가 인증 성공 시 Access Token 발급
3.	클라이언트는 API 호출 시 Authorization: Bearer <AccessToken> 헤더 포함
```

---
🛠️ 세팅 방법
1.	H2 데이터베이스 생성
Spring Boot 실행 시 H2 인메모리 DB가 자동으로 생성됩니다.
2.	테스트 데이터 로딩
src/main/resources/data.sql 파일 내부의 SQL 문을 참고하여 사용자 데이터를 삽입합니다.
3.	Spring Boot 애플리케이션 실행
4.  postman 등 API 클라이언트를 사용하여 /api/auth/login 엔드포인트로 로그인 요청을 보냅니다.

