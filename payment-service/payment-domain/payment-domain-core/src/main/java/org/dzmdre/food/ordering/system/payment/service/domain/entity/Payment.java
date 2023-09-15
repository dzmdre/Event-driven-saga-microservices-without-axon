package org.dzmdre.food.ordering.system.payment.service.domain.entity;

import lombok.Builder;
import lombok.Getter;
import org.dzmdre.food.ordering.system.domain.entity.AggregateRoot;
import org.dzmdre.food.ordering.system.domain.valueobject.CustomerId;
import org.dzmdre.food.ordering.system.domain.valueobject.Money;
import org.dzmdre.food.ordering.system.domain.valueobject.OrderId;
import org.dzmdre.food.ordering.system.domain.valueobject.PaymentStatus;
import org.dzmdre.food.ordering.system.payment.service.domain.valueobject.PaymentId;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
public class Payment extends AggregateRoot<PaymentId> {

    private final OrderId orderId;
    private final CustomerId customerId;
    private final Money price;

    private PaymentStatus paymentStatus;
    private ZonedDateTime createdAt;

    @Builder
    private Payment(PaymentId paymentId, OrderId orderId,
                    CustomerId customerId, Money price,
                    PaymentStatus paymentStatus, ZonedDateTime createdAt) {
        setId(paymentId);
        this.orderId = orderId;
        this.customerId = customerId;
        this.price = price;
        this.paymentStatus = paymentStatus;
        this.createdAt = createdAt;
    }

    public void initializePayment() {
        setId(new PaymentId(UUID.randomUUID()));
        createdAt = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    public void validatePayment(List<String> failureMessages) {
        if (price == null || !price.isGreaterThanZero()) {
            failureMessages.add("Total price must be greater than zero!");
        }
    }

    public void updateStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
