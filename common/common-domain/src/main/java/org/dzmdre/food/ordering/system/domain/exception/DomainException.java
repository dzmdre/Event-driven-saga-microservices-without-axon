package org.dzmdre.food.ordering.system.domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
        log.error(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
        log.error(message, cause);
    }
}
