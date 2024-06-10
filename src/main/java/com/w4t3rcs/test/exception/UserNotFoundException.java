package com.w4t3rcs.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User hasn't been found")
public class UserNotFoundException extends RuntimeException {
}
