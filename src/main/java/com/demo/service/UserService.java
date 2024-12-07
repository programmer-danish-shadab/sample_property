package com.demo.service;

import com.demo.payload.LoginDto;
import com.demo.payload.UserDto;

public interface UserService {
    public UserDto addUser(UserDto userDto);

    String verifyLogin(LoginDto loginDto);
}
