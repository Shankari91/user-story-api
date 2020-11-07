package com.archymides.userstory.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Resource is not available")
public class ResourceNotFoundException extends RuntimeException {}
