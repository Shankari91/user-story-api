package com.archymides.userstory.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason="You dont have access")
public class ForbiddenException extends RuntimeException {}
