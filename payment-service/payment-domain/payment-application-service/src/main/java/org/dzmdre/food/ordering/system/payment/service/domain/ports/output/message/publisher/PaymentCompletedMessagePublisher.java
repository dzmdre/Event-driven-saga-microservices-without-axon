package org.dzmdre.food.ordering.system.payment.service.domain.ports.output.message.publisher;

import org.dzmdre.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import org.dzmdre.food.ordering.system.payment.service.domain.event.PaymentCompletedEvent;

public interface PaymentCompletedMessagePublisher extends DomainEventPublisher<PaymentCompletedEvent> {
}
