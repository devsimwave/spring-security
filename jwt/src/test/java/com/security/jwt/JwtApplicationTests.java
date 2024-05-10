package com.security.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootTest
class JwtApplicationTests {

	@Test
	void contextLoads() {
		// 시큰키 길이 지정 (256비트 이상을 권장)
		int keyLength = 32; // 바이트 단위

		// 무작위 바이트 생성
		byte[] keyBytes = new byte[keyLength];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(keyBytes);

		// Base64 인코딩을 통해 시큰키 생성
		String jwtSecretKey = Base64.getEncoder().encodeToString(keyBytes);
		System.out.println("JWT Secret Key: " + jwtSecretKey);
	}

}
