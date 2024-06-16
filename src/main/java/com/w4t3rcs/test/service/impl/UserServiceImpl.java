package com.w4t3rcs.test.service.impl;

import com.w4t3rcs.test.dto.UserDto;
import com.w4t3rcs.test.entity.User;
import com.w4t3rcs.test.exception.UserNotFoundException;
import com.w4t3rcs.test.repository.UserRepository;
import com.w4t3rcs.test.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
        UserDto saved = UserDto.fromUser(userRepository.save(userDto.toUser()));
        log.info("User: \"{}\" has been saved into DB", saved);
        return saved;
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> userDtos = userRepository.findAll()
                .stream()
                .map(UserDto::fromUser)
                .toList();
        log.info("Users have been got from DB");
        return userDtos;
    }

    @Override
    @Cacheable(value = "UserService::getUser", key = "#id")
    public UserDto getUser(Long id) {
        UserDto userDto = UserDto.fromUser(userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new));
        log.info("User by id: \"{}\" has been got from DB", id);
        return userDto;
    }

    @Override
    @Cacheable(value = "UserService::getUser", key = "#name")
    public UserDto getUser(String name) {
        UserDto userDto = UserDto.fromUser(userRepository.findByName(name)
                .orElseThrow(UserNotFoundException::new));
        log.info("User by name: \"{}\" has been got from DB", name);
        return userDto;
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
        if (userDto.getRole() != null) user.setRole(userDto.getRole());

        UserDto saved = UserDto.fromUser(userRepository.save(user));
        log.info("User: \"{}\" has been updated into DB (old version of user: \"{}\")", saved, user);
        return saved;
    }

    @Override
    public Long deleteUser(Long id) {
        userRepository.deleteById(id);
        log.info("User by id: \"{}\" has been removed from DB", id);
        return id;
    }
}
