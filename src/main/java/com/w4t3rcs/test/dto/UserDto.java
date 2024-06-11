package com.w4t3rcs.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.w4t3rcs.test.entity.User;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String name;
    private String password;
    private String email;

    public static UserDto fromUser(@Valid User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    @Valid
    public User toUser() {
        return User.builder()
                .id(this.getId())
                .name(this.getName())
                .password(this.getPassword())
                .email(this.getEmail())
                .build();
    }
}
