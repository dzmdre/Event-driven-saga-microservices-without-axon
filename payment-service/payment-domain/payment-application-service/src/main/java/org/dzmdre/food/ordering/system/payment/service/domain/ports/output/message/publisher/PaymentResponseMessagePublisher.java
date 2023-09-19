package org.dzmdre.food.ordering.system.payment.service.domain.ports.output.message.publisher;

import org.dzmdre.food.ordering.system.outbox.OutboxStatus;
import org.dzmdre.food.ordering.system.payment.service.domain.outbox.model.OrderOutboxMessage;

import java.util.function.BiConsumer;

public interface PaymentResponseMessagePublisher {
    void publish(OrderOutboxMessage orderOutboxMessage,
                 BiConsumer<OrderOutboxMessage, OutboxStatus> outboxCallback);
}
