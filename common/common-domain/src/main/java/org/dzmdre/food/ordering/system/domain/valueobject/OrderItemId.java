package org.dzmdre.food.ordering.system.domain.valueobject;

import org.dzmdre.food.ordering.system.domain.valueobject.BaseId;

import java.util.UUID;

public class OrderItemId extends BaseId<Long> {
    public OrderItemId(java.util.UUID value) {
        super(value.getMostSignificantBits());
    }
}
