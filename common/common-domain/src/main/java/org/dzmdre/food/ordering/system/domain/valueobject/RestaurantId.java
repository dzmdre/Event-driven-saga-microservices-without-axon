package org.dzmdre.food.ordering.system.domain.valueobject;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RestaurantId extends BaseId<UUID> {
    public RestaurantId(UUID value) {
        super(value);
    }
}
