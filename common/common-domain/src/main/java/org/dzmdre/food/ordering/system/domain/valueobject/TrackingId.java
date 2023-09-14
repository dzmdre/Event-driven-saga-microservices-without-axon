package org.dzmdre.food.ordering.system.domain.valueobject;

import org.dzmdre.food.ordering.system.domain.valueobject.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID value) {
        super(value);
    }
}
