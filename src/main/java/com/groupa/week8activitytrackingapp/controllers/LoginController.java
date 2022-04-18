package com.groupa.week8activitytrackingapp.controllers;

import com.groupa.week8activitytrackingapp.dtos.LoginDto;
import com.groupa.week8activitytrackingapp.model.User;
import com.groupa.week8activitytrackingapp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

//@Controller
//public class LoginController {
//
//    private final UserServices userService;
//
//    @Autowired
//    public LoginController(UserServices userService) {
//        this.userService = userService;
//    }
//
//
//    @GetMapping("/")
//    public String home(Model model) {
//        model.addAttribute("loginDto", new LoginDto());
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String login (@ModelAttribute("loginDto") LoginDto loginDto, HttpSession session) {
//        User user = userService.login(loginDto);
//        if (user != null) {
//            session.setAttribute("member",user);
//            return "redirect:/home";
//        }
//        return "redirect:/";
//    }
//}
