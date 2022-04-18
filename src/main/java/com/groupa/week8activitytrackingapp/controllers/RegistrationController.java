package com.groupa.week8activitytrackingapp.controllers;

import com.groupa.week8activitytrackingapp.dtos.LoginDto;
import com.groupa.week8activitytrackingapp.dtos.UserDto;
import com.groupa.week8activitytrackingapp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class RegistrationController {
    private UserServices userServices;
    @Autowired
    public RegistrationController(UserServices userServices){
        this.userServices = userServices;
    }
    @GetMapping("/signUp")
    public String register(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "signUp";
    }
    @PostMapping("/signUp")
    public String register(@ModelAttribute("user") UserDto userDto, Model model){
        boolean done = userServices.createUser(userDto);
        if(done){
            return "home";
        }
        model.addAttribute("failed", "Email Already Exists In the system");
        return "redirect:/signIn";
    }

    @GetMapping("/login")
    public String loginView(Model model){
        LoginDto loginDto = new LoginDto();
        model.addAttribute("loginDto", loginDto);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginDto") LoginDto loginDto){
       userServices.login(loginDto);

        return "redirect:/tasks";
    }
}


