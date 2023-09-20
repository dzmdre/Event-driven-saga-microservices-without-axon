package org.dzmdre.food.ordering.system.customer.service.domain.event;

import lombok.Getter;
import org.dzmdre.food.ordering.system.customer.service.domain.entity.Customer;
import org.dzmdre.food.ordering.system.domain.event.DomainEvent;

import java.time.ZonedDateTime;

@Getter
public class CustomerCreatedEvent implements DomainEvent<Customer> {

    private final Customer customer;

    private final ZonedDateTime createdAt;

    public CustomerCreatedEvent(Customer customer, ZonedDateTime createdAt) {
        this.customer = customer;
        this.createdAt = createdAt;
    }

}
