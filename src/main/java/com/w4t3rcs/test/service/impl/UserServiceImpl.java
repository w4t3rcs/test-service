package com.w4t3rcs.test.service.impl;

import com.w4t3rcs.test.dto.UserDto;
import com.w4t3rcs.test.entity.User;
import com.w4t3rcs.test.exception.UserNotFoundException;
import com.w4t3rcs.test.repository.UserRepository;
import com.w4t3rcs.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Caching(cacheable = {
            @Cacheable(value = "UserService::getUser", key = "#userDto.id"),
            @Cacheable(value = "UserService::getUser", key = "#userDto.name")
    })
    public UserDto createUser(UserDto userDto) {
        return UserDto.fromUser(userRepository.save(userDto.toUser()));
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::fromUser)
                .toList();
    }

    @Override
    @Cacheable(value = "UserService::getUser", key = "#id")
    public UserDto getUser(Long id) {
        return UserDto.fromUser(userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new));
    }

    @Override
    @Cacheable(value = "UserService::getUser", key = "#name")
    public UserDto getUser(String name) {
        return UserDto.fromUser(userRepository.findByName(name)
                .orElseThrow(UserNotFoundException::new));
    }

    @Override
    @Caching(put = {
            @CachePut(value = "UserService::getUser", key = "#id"),
    })
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        if (userDto.getName() != null) user.setName(userDto.getName());
        if (userDto.getPassword() != null) user.setPassword(userDto.getPassword());
        if (userDto.getEmail() != null) user.setEmail(userDto.getEmail());

        return UserDto.fromUser(userRepository.save(user));
    }

    @Override
    public Long deleteUser(Long id) {
        userRepository.deleteById(id);
        return id;
    }
}
