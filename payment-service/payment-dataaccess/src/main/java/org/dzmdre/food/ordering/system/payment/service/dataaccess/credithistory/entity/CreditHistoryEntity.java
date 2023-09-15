package org.dzmdre.food.ordering.system.payment.service.dataaccess.credithistory.entity;

import lombok.*;
import org.dzmdre.food.ordering.system.payment.service.domain.valueobject.TransactionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credit_history")
@Entity
public class CreditHistoryEntity {
    @Id
    private UUID id;
    private UUID customerId;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
}
