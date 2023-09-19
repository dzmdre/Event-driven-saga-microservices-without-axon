package org.dzmdre.food.ordering.system.customer.service.domain;

import org.dzmdre.food.ordering.system.customer.service.domain.entity.Customer;
import org.dzmdre.food.ordering.system.customer.service.domain.event.CustomerCreatedEvent;

public interface CustomerDomainService {

    CustomerCreatedEvent validateAndInitiateCustomer(Customer customer);

}
