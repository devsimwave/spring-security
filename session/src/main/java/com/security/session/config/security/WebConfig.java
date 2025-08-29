package com.security.session.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /*
        정적 자원 위치 설정
        classpath: 자바 애플리케이션이 실행될 때 JVM이 클래스(.class 파일)를 어디서 읽어올지 경로를 모안둔 것
        META-INF : 자바 어플리케이션이나 라이브러리(JAR< WAR 파일 등) 안에서 사용되는 특수한 디렉토리다. 일종의 메타데이터 저장소 역할을 한다.
     */
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/static/",
            "classpath:/public/",
            "classpath:/",
            "classpath:/resources/",
            "classpath:/META-INF/resources/",
            "classpath:/META-INF/resources/webjars/"
    };

    // 정적 자원 핸들러 설정
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS)
                .setCachePeriod(3600); // 캐싱 1시간
    }




}
