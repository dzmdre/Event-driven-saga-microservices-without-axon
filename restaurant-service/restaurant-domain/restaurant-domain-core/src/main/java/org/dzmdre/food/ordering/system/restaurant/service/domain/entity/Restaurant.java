package org.dzmdre.food.ordering.system.restaurant.service.domain.entity;

import lombok.Builder;
import lombok.Getter;
import org.dzmdre.food.ordering.system.domain.entity.AggregateRoot;
import org.dzmdre.food.ordering.system.domain.valueobject.Money;
import org.dzmdre.food.ordering.system.domain.valueobject.OrderApprovalStatus;
import org.dzmdre.food.ordering.system.domain.valueobject.OrderStatus;
import org.dzmdre.food.ordering.system.domain.valueobject.RestaurantId;
import org.dzmdre.food.ordering.system.restaurant.service.domain.valueobject.OrderApprovalId;

import java.util.List;
import java.util.UUID;

@Getter
public class Restaurant extends AggregateRoot<RestaurantId> {
    private OrderApproval orderApproval;
    private boolean active;
    private final OrderDetail orderDetail;

    public void validateOrder(List<String> failureMessages) {
        if (orderDetail.getOrderStatus() != OrderStatus.PAID) {
            failureMessages.add("Payment is not completed for order: " + orderDetail.getId());
        }
        Money totalAmount = orderDetail.getProducts().stream().map(product -> {
            if (!product.isAvailable()) {
                failureMessages.add("Product with id: " + product.getId().getValue()
                        + " is not available");
            }
            return product.getPrice().multiply(product.getQuantity());
        }).reduce(Money.ZERO, Money::add);

        if (!totalAmount.equals(orderDetail.getTotalAmount())) {
            failureMessages.add("Price total is not correct for order: " + orderDetail.getId());
        }
    }

    public void constructOrderApproval(OrderApprovalStatus orderApprovalStatus) {
        this.orderApproval = OrderApproval.builder()
                .orderApprovalId(new OrderApprovalId(UUID.randomUUID()))
                .restaurantId(this.getId())
                .orderId(this.getOrderDetail().getId())
                .approvalStatus(orderApprovalStatus)
                .build();
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Builder
    public Restaurant(final RestaurantId restaurantId,
                      final OrderApproval orderApproval,
                      boolean active,
                      final OrderDetail orderDetail) {
        setId(restaurantId);
        this.orderApproval = orderApproval;
        this.active = active;
        this.orderDetail = orderDetail;
    }
}
