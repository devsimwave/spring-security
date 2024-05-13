package com.security.jwt.module.users.service;

import com.security.jwt.module.users.entity.Authority;
import com.security.jwt.module.users.entity.Users;
import com.security.jwt.module.users.repository.UsersRepository;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UsersRepository usersRepository;

  @Autowired
  public UserDetailsServiceImpl(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Users users = usersRepository.findByEmail(username);

    System.out.println(users.getAuthorities().stream().map(Authority::getAuthorityName).collect(Collectors.joining(",")));

    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    grantedAuthorities.add((GrantedAuthority) () -> users.getAuthorities().stream().map(Authority::getAuthorityName).collect(Collectors.joining(",")));

    return new User(users.getEmail(), users.getPassword(), grantedAuthorities);
  }
}
