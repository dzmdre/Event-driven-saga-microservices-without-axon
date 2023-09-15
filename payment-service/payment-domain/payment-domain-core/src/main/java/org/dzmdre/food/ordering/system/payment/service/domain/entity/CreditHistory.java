package org.dzmdre.food.ordering.system.payment.service.domain.entity;

import lombok.Builder;
import lombok.Getter;
import org.dzmdre.food.ordering.system.domain.entity.BaseEntity;
import org.dzmdre.food.ordering.system.domain.valueobject.CustomerId;
import org.dzmdre.food.ordering.system.domain.valueobject.Money;
import org.dzmdre.food.ordering.system.payment.service.domain.valueobject.CreditHistoryId;
import org.dzmdre.food.ordering.system.payment.service.domain.valueobject.TransactionType;

@Getter
public class CreditHistory extends BaseEntity<CreditHistoryId> {

    private final CustomerId customerId;
    private final Money amount;
    private final TransactionType transactionType;

    @Builder
    public CreditHistory(CreditHistoryId creditHistoryId,
                         CustomerId customerId,
                         Money amount,
                         TransactionType transactionType) {
        setId(creditHistoryId);
        this.customerId = customerId;
        this.amount = amount;
        this.transactionType = transactionType;
    }
}
