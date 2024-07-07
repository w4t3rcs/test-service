package com.w4t3rcs.test.controller;

import com.w4t3rcs.test.dto.UserDto;
import com.w4t3rcs.test.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Getting all users from DB")
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getUsers();
    }

    @Operation(summary = "Getting an user from DB")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @Operation(summary = "Getting an user from DB by user's name")
    @GetMapping(path = "/{name}", params = "name")
    public ResponseEntity<UserDto> getUserByName(@PathVariable String name) {
        return ResponseEntity.ok(userService.getUser(name));
    }

    @Operation(summary = "Creating and saving an user to DB")
    @PostMapping
    public ResponseEntity<UserDto> postUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    @Operation(summary = "Updating an user from DB and saving those changes to DB")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> putUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @Operation(summary = "Deleting an user from DB")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
