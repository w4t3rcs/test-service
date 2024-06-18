package com.w4t3rcs.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
public class SecureController {
    @GetMapping
    public ResponseEntity<SecurityContext> getSecurityContext() {
        return ResponseEntity.ok(SecurityContextHolder.getContext());
    }
}
