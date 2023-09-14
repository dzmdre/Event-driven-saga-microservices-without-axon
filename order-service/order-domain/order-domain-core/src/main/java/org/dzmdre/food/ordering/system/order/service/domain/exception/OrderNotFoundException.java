package org.dzmdre.food.ordering.system.order.service.domain.exception;


import lombok.extern.slf4j.Slf4j;
import org.dzmdre.food.ordering.system.domain.exception.DomainException;
@Slf4j
public class OrderNotFoundException extends DomainException {

    public OrderNotFoundException(String message) {
        super(message);
        log.error(message);
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
        log.error(message, cause);
    }
}
