package com.w4t3rcs.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.w4t3rcs.test.entity.Role;
import com.w4t3rcs.test.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(name = "User")
public class UserDto {
    private Long id;
    private String name;
    private String password;
    private String email;
    private Role role;

    public static UserDto fromUser(@Valid User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Valid
    public User toUser() {
        return User.builder()
                .id(this.getId())
                .name(this.getName())
                .password(this.getPassword())
                .email(this.getEmail())
                .role(this.getRole())
                .build();
    }
}
