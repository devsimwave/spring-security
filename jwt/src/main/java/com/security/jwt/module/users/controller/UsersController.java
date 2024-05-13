package com.security.jwt.module.users.controller;

import com.security.jwt.module.users.dto.UsersDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    //TODO 인가처리 필요
    @GetMapping("me")
    @PreAuthorize("hasRole('ADMIN')")
    public UsersDto getMe() {

        System.out.println("getMe");

        return null;
    }

}
