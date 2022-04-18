package com.groupa.week8activitytrackingapp.services;

import com.groupa.week8activitytrackingapp.dtos.LoginDto;
import com.groupa.week8activitytrackingapp.dtos.UserDto;
import com.groupa.week8activitytrackingapp.model.User;

public interface UserServices {
    boolean createUser(UserDto userDto);
    User findByEmail(String email);
    User login(LoginDto loginDto);
}