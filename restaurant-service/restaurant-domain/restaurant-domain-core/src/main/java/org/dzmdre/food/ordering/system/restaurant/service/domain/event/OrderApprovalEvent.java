package org.dzmdre.food.ordering.system.restaurant.service.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dzmdre.food.ordering.system.domain.event.DomainEvent;
import org.dzmdre.food.ordering.system.domain.valueobject.RestaurantId;
import org.dzmdre.food.ordering.system.restaurant.service.domain.entity.OrderApproval;

import java.time.ZonedDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public abstract class OrderApprovalEvent implements DomainEvent<OrderApproval> {
    private final OrderApproval orderApproval;
    private final RestaurantId restaurantId;
    private final List<String> failureMessages;
    private final ZonedDateTime createdAt;
}
