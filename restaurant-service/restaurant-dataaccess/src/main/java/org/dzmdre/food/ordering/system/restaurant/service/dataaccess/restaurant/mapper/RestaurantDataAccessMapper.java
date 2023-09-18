package org.dzmdre.food.ordering.system.restaurant.service.dataaccess.restaurant.mapper;

import org.dzmdre.food.ordering.system.dataaccess.restaurant.entity.RestaurantEntity;
import org.dzmdre.food.ordering.system.dataaccess.restaurant.exception.RestaurantDAOException;
import org.dzmdre.food.ordering.system.domain.valueobject.Money;
import org.dzmdre.food.ordering.system.domain.valueobject.OrderId;
import org.dzmdre.food.ordering.system.domain.valueobject.ProductId;
import org.dzmdre.food.ordering.system.domain.valueobject.RestaurantId;
import org.dzmdre.food.ordering.system.restaurant.service.dataaccess.restaurant.entity.OrderApprovalEntity;
import org.dzmdre.food.ordering.system.restaurant.service.domain.entity.OrderApproval;
import org.dzmdre.food.ordering.system.restaurant.service.domain.entity.OrderDetail;
import org.dzmdre.food.ordering.system.restaurant.service.domain.entity.Product;
import org.dzmdre.food.ordering.system.restaurant.service.domain.entity.Restaurant;
import org.dzmdre.food.ordering.system.restaurant.service.domain.valueobject.OrderApprovalId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantDataAccessMapper {

    public List<UUID> restaurantToRestaurantProducts(Restaurant restaurant) {
        return restaurant.getOrderDetail().getProducts().stream()
                .map(product -> product.getId().getValue())
                .collect(Collectors.toList());
    }

    public Restaurant restaurantEntityToRestaurant(List<RestaurantEntity> restaurantEntities) {
        final RestaurantEntity restaurantEntity =
                restaurantEntities.stream().findFirst().orElseThrow(() ->
                        new RestaurantDAOException("No restaurants found!"));
        final List<Product> restaurantProducts = restaurantEntities.stream().map(entity ->
                        Product.builder()
                                .productId(new ProductId(entity.getProductId()))
                                .name(entity.getProductName())
                                .price(new Money(entity.getProductPrice()))
                                .available(entity.getProductAvailable())
                                .build())
                .collect(Collectors.toList());
        return Restaurant.builder()
                .restaurantId(new RestaurantId(restaurantEntity.getRestaurantId()))
                .orderDetail(OrderDetail.builder()
                        .products(restaurantProducts)
                        .build())
                .active(restaurantEntity.getRestaurantActive())
                .build();
    }

    public OrderApprovalEntity orderApprovalToOrderApprovalEntity(OrderApproval orderApproval) {
        return OrderApprovalEntity.builder()
                .id(orderApproval.getId().getValue())
                .restaurantId(orderApproval.getRestaurantId().getValue())
                .orderId(orderApproval.getOrderId().getValue())
                .status(orderApproval.getApprovalStatus())
                .build();
    }

    public OrderApproval orderApprovalEntityToOrderApproval(OrderApprovalEntity orderApprovalEntity) {
        return OrderApproval.builder()
                .orderApprovalId(new OrderApprovalId(orderApprovalEntity.getId()))
                .restaurantId(new RestaurantId(orderApprovalEntity.getRestaurantId()))
                .orderId(new OrderId(orderApprovalEntity.getOrderId()))
                .approvalStatus(orderApprovalEntity.getStatus())
                .build();
    }
}
