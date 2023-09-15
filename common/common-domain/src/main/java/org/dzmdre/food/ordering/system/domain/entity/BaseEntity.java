package org.dzmdre.food.ordering.system.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public abstract class BaseEntity<ID> {
    private ID id;

    public void setId(ID id) {
        this.id = id;
    }
}
