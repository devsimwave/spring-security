package com.security.session.module.users.dto;

import com.security.session.module.users.entity.Users;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class UserDetailsImpl implements UserDetails {

    // 객체의 불변성을 방지하기 위해 final로 선언
    private final Users users;
    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; // 실제 권한 리스트 반환
    }

    @Override
    public String getPassword() {
        return users.getPassword(); // Users 엔티티에서 가져오기
    }

    @Override
    public String getUsername() {
        return users.getEmail(); // Users 엔티티의 식별자(보통 email/username)
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // true로 설정해야 계정 만료 안 됨
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // true로 설정해야 계정 잠금 안 됨
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // true로 설정해야 비밀번호 만료 안 됨
    }

    @Override
    public boolean isEnabled() {
        return users.isActivated(); // DB 상태 필드 활용 가능
    }
}
