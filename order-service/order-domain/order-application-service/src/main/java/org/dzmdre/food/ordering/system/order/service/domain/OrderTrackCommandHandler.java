package org.dzmdre.food.ordering.system.order.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.dzmdre.food.ordering.system.domain.valueobject.TrackingId;
import org.dzmdre.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import org.dzmdre.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import org.dzmdre.food.ordering.system.order.service.domain.entity.Order;
import org.dzmdre.food.ordering.system.order.service.domain.exception.OrderNotFoundException;
import org.dzmdre.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import org.dzmdre.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class OrderTrackCommandHandler {

    private final OrderDataMapper orderDataMapper;

    private final OrderRepository orderRepository;

    public OrderTrackCommandHandler(OrderDataMapper orderDataMapper, OrderRepository orderRepository) {
        this.orderDataMapper = orderDataMapper;
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        Optional<Order> orderResult =
                orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));
        if (orderResult.isEmpty()) {
            log.warn("Could not find order with tracking id: {}", trackOrderQuery.getOrderTrackingId());
            throw new OrderNotFoundException("Could not find order with tracking id: " +
                    trackOrderQuery.getOrderTrackingId());
        }
        return orderDataMapper.orderToTrackOrderResponse(orderResult.get());
    }
}
