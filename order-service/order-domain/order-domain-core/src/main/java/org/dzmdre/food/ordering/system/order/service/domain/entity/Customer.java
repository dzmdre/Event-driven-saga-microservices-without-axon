package org.dzmdre.food.ordering.system.order.service.domain.entity;

import org.dzmdre.food.ordering.system.domain.entity.AggregateRoot;
import org.dzmdre.food.ordering.system.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {
    public Customer() {
    }

    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }
}
