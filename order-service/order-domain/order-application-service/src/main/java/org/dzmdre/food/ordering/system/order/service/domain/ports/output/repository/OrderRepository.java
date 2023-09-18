package org.dzmdre.food.ordering.system.order.service.domain.ports.output.repository;


import org.dzmdre.food.ordering.system.domain.valueobject.OrderId;
import org.dzmdre.food.ordering.system.domain.valueobject.TrackingId;
import org.dzmdre.food.ordering.system.order.service.domain.entity.Order;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findByTrackingId(TrackingId trackingId);

    Optional<Order> findById(OrderId orderId);
}
