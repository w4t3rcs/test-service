package com.w4t3rcs.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Article hasn't been found")
public class ArticleNotFoundException extends RuntimeException {
}
