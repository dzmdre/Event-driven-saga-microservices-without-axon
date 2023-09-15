package org.dzmdre.food.ordering.system.payment.service.domain.ports.input.message.listener;

import org.dzmdre.food.ordering.system.payment.service.domain.dto.PaymentRequest;

public interface PaymentRequestMessageListener {

    void completePayment(PaymentRequest paymentRequest);

    void cancelPayment(PaymentRequest paymentRequest);
}
