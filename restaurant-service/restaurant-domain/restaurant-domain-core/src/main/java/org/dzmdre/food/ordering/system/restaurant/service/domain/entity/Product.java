package org.dzmdre.food.ordering.system.restaurant.service.domain.entity;

import lombok.Builder;
import lombok.Getter;
import org.dzmdre.food.ordering.system.domain.entity.BaseEntity;
import org.dzmdre.food.ordering.system.domain.valueobject.Money;
import org.dzmdre.food.ordering.system.domain.valueobject.ProductId;

@Getter
public class Product extends BaseEntity<ProductId> {
    private String name;
    private Money price;
    private final int quantity;
    private boolean available;

    public void updateWithConfirmedNamePriceAndAvailability(String name, Money price, boolean available) {
        this.name = name;
        this.price = price;
        this.available = available;
    }

    @Builder
    public Product(final ProductId productId,
                   final String name,
                   final Money price,
                   final int quantity,
                   final boolean available) {
        setId(productId);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.available = available;
    }
}
