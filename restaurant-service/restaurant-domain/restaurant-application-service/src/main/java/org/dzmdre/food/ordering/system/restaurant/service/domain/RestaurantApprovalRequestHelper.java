package org.dzmdre.food.ordering.system.restaurant.service.domain;

import lombok.AllArgsConstructor;
import org.dzmdre.food.ordering.system.domain.valueobject.OrderId;
import org.dzmdre.food.ordering.system.restaurant.service.domain.dto.RestaurantApprovalRequest;
import org.dzmdre.food.ordering.system.restaurant.service.domain.entity.Restaurant;
import org.dzmdre.food.ordering.system.restaurant.service.domain.event.OrderApprovalEvent;
import org.dzmdre.food.ordering.system.restaurant.service.domain.exception.RestaurantNotFoundException;
import org.dzmdre.food.ordering.system.restaurant.service.domain.mapper.RestaurantDataMapper;
import org.dzmdre.food.ordering.system.restaurant.service.domain.ports.output.message.publisher.OrderApprovedMessagePublisher;
import org.dzmdre.food.ordering.system.restaurant.service.domain.ports.output.message.publisher.OrderRejectedMessagePublisher;
import org.dzmdre.food.ordering.system.restaurant.service.domain.ports.output.repository.OrderApprovalRepository;
import org.dzmdre.food.ordering.system.restaurant.service.domain.ports.output.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class RestaurantApprovalRequestHelper {

    private final RestaurantDomainService restaurantDomainService;
    private final RestaurantDataMapper restaurantDataMapper;
    private final RestaurantRepository restaurantRepository;
    private final OrderApprovalRepository orderApprovalRepository;
    private final OrderApprovedMessagePublisher orderApprovedMessagePublisher;
    private final OrderRejectedMessagePublisher orderRejectedMessagePublisher;


    @Transactional
    public OrderApprovalEvent persistOrderApproval(RestaurantApprovalRequest restaurantApprovalRequest) {
        log.info("Processing restaurant approval for order id: {}", restaurantApprovalRequest.getOrderId());
        final List<String> failureMessages = new ArrayList<>();
        final Restaurant restaurant = findRestaurant(restaurantApprovalRequest);
        final  OrderApprovalEvent orderApprovalEvent =
                restaurantDomainService.validateOrder(
                        restaurant,
                        failureMessages,
                        orderApprovedMessagePublisher,
                        orderRejectedMessagePublisher);
        orderApprovalRepository.save(restaurant.getOrderApproval());
        return orderApprovalEvent;
    }

    private Restaurant findRestaurant(RestaurantApprovalRequest restaurantApprovalRequest) {
        final Restaurant restaurant = restaurantDataMapper
                .restaurantApprovalRequestToRestaurant(restaurantApprovalRequest);
        final Optional<Restaurant> restaurantResult = restaurantRepository.findRestaurantInformation(restaurant);
        if (restaurantResult.isEmpty()) {
            log.error("Restaurant with id " + restaurant.getId().getValue() + " not found!");
            throw new RestaurantNotFoundException("Restaurant with id " + restaurant.getId().getValue() +
                    " not found!");
        }
        //TODO: rewrite  this
        final Restaurant restaurantEntity = restaurantResult.get();
        restaurant.setActive(restaurantEntity.isActive());
        restaurant.getOrderDetail().getProducts().forEach(product ->
                restaurantEntity.getOrderDetail().getProducts().forEach(p -> {
            if (p.getId().equals(product.getId())) {
                product.updateWithConfirmedNamePriceAndAvailability(p.getName(), p.getPrice(), p.isAvailable());
            }
        }));
        restaurant.getOrderDetail().setId(new OrderId(UUID.fromString(restaurantApprovalRequest.getOrderId())));
        return restaurant;
    }
}
