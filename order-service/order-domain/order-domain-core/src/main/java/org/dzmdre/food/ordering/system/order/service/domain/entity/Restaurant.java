package org.dzmdre.food.ordering.system.order.service.domain.entity;

import lombok.Builder;
import lombok.Getter;
import org.dzmdre.food.ordering.system.domain.entity.AggregateRoot;
import org.dzmdre.food.ordering.system.domain.valueobject.RestaurantId;

import java.util.List;

@Builder()
@Getter
public class Restaurant extends AggregateRoot<RestaurantId> {
    private final List<Product> products;
    private boolean active;
    private RestaurantId restaurantId;

    public RestaurantId getRestaurantId(){
        return this.getId();
    }

    private void  setRestaurantId(RestaurantId restaurantId){
        this.setId(restaurantId);
    }
}
