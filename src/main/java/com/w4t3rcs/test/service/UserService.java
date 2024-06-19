package com.w4t3rcs.test.service;

import com.w4t3rcs.test.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> saveUsers(List<UserDto> userDtos);

    UserDto saveUser(UserDto userDto);

    List<UserDto> getUsers();

    UserDto getUser(Long id);

    UserDto getUser(String name);

    UserDto updateUser(Long id, UserDto userDto);

    Long deleteUser(Long id);
}
