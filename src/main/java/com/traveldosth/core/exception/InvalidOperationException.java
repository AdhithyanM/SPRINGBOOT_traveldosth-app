package com.traveldosth.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Generic way of handling invalid operation exception across the project.
 * later this package can be scaled to handle individual module's error logic.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidOperationException extends RuntimeException {

    private static final long serialVersionUID = 2L;

    public InvalidOperationException() { super(); }

    public InvalidOperationException(String message) { super(message); }

    public InvalidOperationException(String message, Throwable cause) { super(message, cause); }
}
