package org.dzmdre.food.ordering.system.domain.event;

public interface DomainEvent<T> {
    void fire();
}
