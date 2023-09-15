package org.dzmdre.food.ordering.system.order.service.domain.mapper;

import org.dzmdre.food.ordering.system.domain.valueobject.*;
import org.dzmdre.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import org.dzmdre.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import org.dzmdre.food.ordering.system.order.service.domain.dto.create.OrderAddress;
import org.dzmdre.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import org.dzmdre.food.ordering.system.order.service.domain.entity.Order;
import org.dzmdre.food.ordering.system.order.service.domain.entity.OrderItem;
import org.dzmdre.food.ordering.system.order.service.domain.entity.Product;
import org.dzmdre.food.ordering.system.order.service.domain.entity.Restaurant;
import org.dzmdre.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {

    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
        return Restaurant.builder()
                //TODO: add restaurant ID .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .products(createOrderCommand.getItems().stream().map(orderItem ->
                        new Product(new ProductId(orderItem.getProductId())))
                        .collect(Collectors.toList()))
                .build();
    }
    
    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                //TODO: add .items(orderItemsToOrderItemEntities(createOrderCommand.getItems()))
                .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order, String message) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .message(message)
                .build();
    }

    public TrackOrderResponse orderToTrackOrderResponse(Order order) {
        return TrackOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .failureMessages(order.getFailureMessages())
                .build();
    }

    private List<OrderItem> orderItemsToOrderItemEntities(
            List<org.dzmdre.food.ordering.system.order.service.domain.dto.create.OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem ->
                        OrderItem.builder()
                                //TODO:  .product(new Product(new ProductId(orderItem.getProductId())))
                                //TODO:    .price(new Money(orderItem.getPrice()))
                                .quantity(orderItem.getQuantity())
                                //TODO:  .subTotal(new Money(orderItem.getSubTotal()))
                                .build()).collect(Collectors.toList());
    }

    private StreetAddress orderAddressToStreetAddress(OrderAddress orderAddress) {
        return new StreetAddress(
                UUID.randomUUID(),
                orderAddress.getStreet(),
                orderAddress.getPostalCode(),
                orderAddress.getCity()
        );
    }
}
