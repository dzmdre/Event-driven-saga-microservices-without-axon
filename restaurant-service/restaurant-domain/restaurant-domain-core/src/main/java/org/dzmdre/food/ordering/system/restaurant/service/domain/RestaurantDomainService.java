package org.dzmdre.food.ordering.system.restaurant.service.domain;

import org.dzmdre.food.ordering.system.restaurant.service.domain.entity.Restaurant;
import org.dzmdre.food.ordering.system.restaurant.service.domain.event.OrderApprovalEvent;

import java.util.List;

public interface RestaurantDomainService {

    OrderApprovalEvent validateOrder(Restaurant restaurant, List<String> failureMessages);
}
