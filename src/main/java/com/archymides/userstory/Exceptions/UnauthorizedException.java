package com.archymides.userstory.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason="Incorrect email or password")
public class UnauthorizedException extends RuntimeException {}
