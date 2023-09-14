package org.dzmdre.food.ordering.system.order.service.domain.event;

import lombok.AllArgsConstructor;
import org.dzmdre.food.ordering.system.order.service.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderCreatedEvent extends OrderEvent {

    public OrderCreatedEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
