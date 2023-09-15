package org.dzmdre.food.ordering.system.order.service.domain.entity;

import lombok.Builder;
import lombok.Getter;
import org.dzmdre.food.ordering.system.domain.entity.AggregateRoot;
import org.dzmdre.food.ordering.system.domain.valueobject.RestaurantId;

import java.util.List;

@Getter
public class Restaurant extends AggregateRoot<RestaurantId> {
    private final List<Product> products;
    private final boolean active;

    @Builder
    public Restaurant(List<Product> products,
                      boolean active,
                      RestaurantId restaurantId) {
        this.products = products;
        this.active = active;
        setId(restaurantId);
    }
}
