package org.dzmdre.food.ordering.system.order.service.domain.entity;

import lombok.Builder;
import lombok.Getter;
import org.dzmdre.food.ordering.system.domain.entity.BaseEntity;
import org.dzmdre.food.ordering.system.domain.valueobject.Money;
import org.dzmdre.food.ordering.system.domain.valueobject.OrderId;
import org.dzmdre.food.ordering.system.domain.valueobject.OrderItemId;

@Getter
public class OrderItem extends BaseEntity<OrderItemId> {
    private OrderId orderId;
    private final Product product;
    private final int quantity;
    private final Money price;
    private final Money subTotal;

    @Builder
    public OrderItem(final OrderId orderId,
                     final Product product,
                     final int quantity,
                     final Money price,
                     final Money subTotal,
                     final OrderItemId orderItemId) {
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.subTotal = subTotal;
        setId(orderItemId);
    }

    void initializeOrderItem(OrderId orderId, OrderItemId orderItemId) {
        this.orderId = orderId;
        super.setId(orderItemId);
    }

    boolean isPriceValid() {
        return price.isGreaterThanZero() &&
                price.equals(product.getPrice()) &&
                price.multiply(quantity).equals(subTotal);
    }
}
