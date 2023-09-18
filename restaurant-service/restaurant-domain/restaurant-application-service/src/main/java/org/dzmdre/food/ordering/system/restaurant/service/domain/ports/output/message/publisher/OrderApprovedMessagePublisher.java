package org.dzmdre.food.ordering.system.restaurant.service.domain.ports.output.message.publisher;

import org.dzmdre.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import org.dzmdre.food.ordering.system.restaurant.service.domain.event.OrderApprovedEvent;

public interface OrderApprovedMessagePublisher extends DomainEventPublisher<OrderApprovedEvent> {
    public static final String RESTAURANT_APPROVAL_RESPONSE_AVRO_MODEL = "RestaurantApprovalResponseAvroModel";
}
