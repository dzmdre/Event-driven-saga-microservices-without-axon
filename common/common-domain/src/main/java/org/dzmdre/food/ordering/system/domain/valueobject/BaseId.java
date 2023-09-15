package org.dzmdre.food.ordering.system.domain.valueobject;

import lombok.EqualsAndHashCode;

import java.util.Objects;

@EqualsAndHashCode
public abstract class BaseId<T> {
    private final T value;

    protected  BaseId(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
