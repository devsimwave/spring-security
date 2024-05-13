package com.security.jwt.module.users.service;

import com.security.jwt.module.users.entity.Users;
import com.security.jwt.module.users.repository.UsersRepository;
import java.nio.ByteBuffer;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersService {

  private final UsersRepository usersRepository;

  @Autowired
  public UsersService(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }


}
