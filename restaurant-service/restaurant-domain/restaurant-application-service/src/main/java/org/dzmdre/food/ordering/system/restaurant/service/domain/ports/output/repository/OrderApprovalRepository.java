package org.dzmdre.food.ordering.system.restaurant.service.domain.ports.output.repository;

import org.dzmdre.food.ordering.system.restaurant.service.domain.entity.OrderApproval;

public interface OrderApprovalRepository {
    OrderApproval save(OrderApproval orderApproval);
}
