package com.pix.pix_service.api.exceptions;

import com.pix.pix_service.domain.exceptions.DomainException;
import com.pix.pix_service.domain.exceptions.QrCodeCreationException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    private static final Map<Class<? extends DomainException>, HttpStatus> STATUS_MAP = Map.of(
        QrCodeCreationException.class, HttpStatus.UNPROCESSABLE_CONTENT
    );

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handle(DomainException ex, Locale locale) {
        HttpStatus status = STATUS_MAP.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        String message = messageSource.getMessage(ex.getMessageKey(), null, locale);

        return ResponseEntity
            .status(status)
            .body(new ErrorResponse(
                status.getReasonPhrase(),
                message,
                ex.getCode()
            ));
    }
}
