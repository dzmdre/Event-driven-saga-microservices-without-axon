package org.dzmdre.food.ordering.system.order.service.domain.entity;

import org.dzmdre.food.ordering.system.domain.entity.AggregateRoot;
import org.dzmdre.food.ordering.system.domain.valueobject.RestaurantId;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Restaurant extends AggregateRoot<RestaurantId> {
    private final List<Product> products;
    private boolean active;

    public List<Product> getProducts() {
        return products;
    }

    public boolean isActive() {
        return active;
    }

    public RestaurantId getRestaurantId() {
        return this.getId();
    }
}
