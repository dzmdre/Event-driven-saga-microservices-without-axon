package org.dzmdre.food.ordering.system.payment.service.domain.valueobject;

import org.dzmdre.food.ordering.system.domain.valueobject.BaseId;

import java.util.UUID;

public class PaymentId extends BaseId<UUID> {
    public PaymentId(UUID value) {
        super(value);
    }
}
