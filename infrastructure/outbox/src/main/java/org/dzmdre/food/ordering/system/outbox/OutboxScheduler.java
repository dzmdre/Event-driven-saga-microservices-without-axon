package org.dzmdre.food.ordering.system.outbox;

public interface OutboxScheduler {
    void processOutboxMessage();
}
