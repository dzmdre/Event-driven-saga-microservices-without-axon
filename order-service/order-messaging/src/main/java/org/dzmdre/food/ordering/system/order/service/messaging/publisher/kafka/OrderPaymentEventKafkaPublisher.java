package org.dzmdre.food.ordering.system.order.service.messaging.publisher.kafka;

import org.dzmdre.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import org.dzmdre.food.ordering.system.kafka.producer.KafkaMessageHelper;
import org.dzmdre.food.ordering.system.kafka.producer.service.KafkaProducer;
import org.dzmdre.food.ordering.system.order.service.domain.config.OrderServiceConfigData;
import org.dzmdre.food.ordering.system.order.service.domain.outbox.model.payment.OrderPaymentEventPayload;
import org.dzmdre.food.ordering.system.order.service.domain.outbox.model.payment.OrderPaymentOutboxMessage;
import org.dzmdre.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.PaymentRequestMessagePublisher;
import org.dzmdre.food.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;
import org.dzmdre.food.ordering.system.outbox.OutboxStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Slf4j
@Component
public class OrderPaymentEventKafkaPublisher implements PaymentRequestMessagePublisher {

    private final OrderMessagingDataMapper orderMessagingDataMapper;
    private final KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;
    private final OrderServiceConfigData orderServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;

    public OrderPaymentEventKafkaPublisher(OrderMessagingDataMapper orderMessagingDataMapper,
                                           KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer,
                                           OrderServiceConfigData orderServiceConfigData,
                                           KafkaMessageHelper kafkaMessageHelper) {
        this.orderMessagingDataMapper = orderMessagingDataMapper;
        this.kafkaProducer = kafkaProducer;
        this.orderServiceConfigData = orderServiceConfigData;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publish(OrderPaymentOutboxMessage orderPaymentOutboxMessage,
                        BiConsumer<OrderPaymentOutboxMessage, OutboxStatus> outboxCallback) {
        OrderPaymentEventPayload orderPaymentEventPayload =
                kafkaMessageHelper.getOrderEventPayload(orderPaymentOutboxMessage.getPayload(),
                        OrderPaymentEventPayload.class);

        String sagaId = orderPaymentOutboxMessage.getSagaId().toString();

        log.info("Received OrderPaymentOutboxMessage for order id: {} and saga id: {}",
                orderPaymentEventPayload.getOrderId(),
                sagaId);

        try {
            PaymentRequestAvroModel paymentRequestAvroModel = orderMessagingDataMapper
                    .orderPaymentEventToPaymentRequestAvroModel(sagaId, orderPaymentEventPayload);

            kafkaProducer.send(orderServiceConfigData.getPaymentRequestTopicName(),
                    sagaId,
                    paymentRequestAvroModel,
                    kafkaMessageHelper.getKafkaCallback(orderServiceConfigData.getPaymentRequestTopicName(),
                            paymentRequestAvroModel,
                            orderPaymentOutboxMessage,
                            outboxCallback,
                            orderPaymentEventPayload.getOrderId(),
                            "PaymentRequestAvroModel"));

            log.info("OrderPaymentEventPayload sent to Kafka for order id: {} and saga id: {}",
                    orderPaymentEventPayload.getOrderId(), sagaId);
        } catch (Exception e) {
           log.error("Error while sending OrderPaymentEventPayload" +
                           " to kafka with order id: {} and saga id: {}, error: {}",
                   orderPaymentEventPayload.getOrderId(), sagaId, e.getMessage());
        }


    }
}
