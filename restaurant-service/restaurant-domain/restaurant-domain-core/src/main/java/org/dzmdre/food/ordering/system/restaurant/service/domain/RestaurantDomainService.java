package org.dzmdre.food.ordering.system.restaurant.service.domain;

import org.dzmdre.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import org.dzmdre.food.ordering.system.restaurant.service.domain.entity.Restaurant;
import org.dzmdre.food.ordering.system.restaurant.service.domain.event.OrderApprovalEvent;
import org.dzmdre.food.ordering.system.restaurant.service.domain.event.OrderApprovedEvent;
import org.dzmdre.food.ordering.system.restaurant.service.domain.event.OrderRejectedEvent;

import java.util.List;

public interface RestaurantDomainService {

    OrderApprovalEvent validateOrder(Restaurant restaurant,
                                     List<String> failureMessages,
                                     DomainEventPublisher<OrderApprovedEvent> orderApprovedEventDomainEventPublisher,
                                     DomainEventPublisher<OrderRejectedEvent> orderRejectedEventDomainEventPublisher);
}
