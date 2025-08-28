package com.security.session.module.users.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/login")
    public ModelAndView login(ModelAndView mv){
        mv.setViewName("/auth/login");
        return mv;
    }
}
