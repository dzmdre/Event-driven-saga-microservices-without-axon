package org.dzmdre.food.ordering.system.restaurant.service.domain.entity;

import lombok.Builder;
import lombok.Getter;
import org.dzmdre.food.ordering.system.domain.entity.BaseEntity;
import org.dzmdre.food.ordering.system.domain.valueobject.OrderApprovalStatus;
import org.dzmdre.food.ordering.system.domain.valueobject.OrderId;
import org.dzmdre.food.ordering.system.domain.valueobject.RestaurantId;
import org.dzmdre.food.ordering.system.restaurant.service.domain.valueobject.OrderApprovalId;

@Getter
public class OrderApproval extends BaseEntity<OrderApprovalId> {
    private final RestaurantId restaurantId;
    private final OrderId orderId;
    private final OrderApprovalStatus approvalStatus;

    @Builder
    public OrderApproval(final OrderApprovalId orderApprovalId,
                         final RestaurantId restaurantId,
                         final OrderId orderId,
                         final OrderApprovalStatus approvalStatus) {
        setId(orderApprovalId);
        this.restaurantId = restaurantId;
        this.orderId = orderId;
        this.approvalStatus = approvalStatus;
    }
}
