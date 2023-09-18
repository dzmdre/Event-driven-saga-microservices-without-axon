package org.dzmdre.food.ordering.system.restaurant.service.domain.ports.output.message.publisher;

import org.dzmdre.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import org.dzmdre.food.ordering.system.restaurant.service.domain.event.OrderRejectedEvent;

public interface OrderRejectedMessagePublisher extends DomainEventPublisher<OrderRejectedEvent> {
}
