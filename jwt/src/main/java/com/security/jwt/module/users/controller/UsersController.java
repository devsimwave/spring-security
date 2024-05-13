package com.security.jwt.module.users.controller;

import com.security.jwt.module.users.dto.UsersDto;
import com.security.jwt.module.users.entity.Users;
import com.security.jwt.module.users.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    //TODO 인가처리 필요
    @GetMapping("me")
    @PreAuthorize("hasRole('ADMIN')")
    public UsersDto getMe() {

        System.out.println("getMe");

        return null;
    }


//    @GetMapping("/me")
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//    public ResponseEntity<Users> getMyUserInfo() {
//        return ResponseEntity.ok(usersService.getMyUserWithAuthorities().get());
//    }

}
