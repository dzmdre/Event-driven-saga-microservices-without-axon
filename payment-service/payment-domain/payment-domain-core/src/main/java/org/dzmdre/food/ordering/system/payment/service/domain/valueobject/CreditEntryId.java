package org.dzmdre.food.ordering.system.payment.service.domain.valueobject;

import org.dzmdre.food.ordering.system.domain.valueobject.BaseId;

import java.util.UUID;

public class CreditEntryId extends BaseId<UUID> {
    public CreditEntryId(UUID value) {
        super(value);
    }
}
