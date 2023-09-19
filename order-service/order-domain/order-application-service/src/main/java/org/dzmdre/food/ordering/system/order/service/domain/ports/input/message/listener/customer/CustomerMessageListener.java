package org.dzmdre.food.ordering.system.order.service.domain.ports.input.message.listener.customer;

import org.dzmdre.food.ordering.system.order.service.domain.dto.message.CustomerModel;

public interface CustomerMessageListener {

    void customerCreated(CustomerModel customerModel);
}
