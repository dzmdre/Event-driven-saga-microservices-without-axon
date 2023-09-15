package org.dzmdre.food.ordering.system.payment.service.domain.ports.output.message.publisher;

import org.dzmdre.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import org.dzmdre.food.ordering.system.payment.service.domain.event.PaymentCancelledEvent;

public interface PaymentCancelledMessagePublisher extends DomainEventPublisher<PaymentCancelledEvent> {
}
