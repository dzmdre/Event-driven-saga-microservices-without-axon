package org.dzmdre.food.ordering.system.restaurant.service.messaging.publisher.kafka;

import lombok.AllArgsConstructor;
import org.dzmdre.food.ordering.system.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import org.dzmdre.food.ordering.system.kafka.producer.KafkaMessageHelper;
import org.dzmdre.food.ordering.system.kafka.producer.service.KafkaProducer;
import org.dzmdre.food.ordering.system.restaurant.service.domain.config.RestaurantServiceConfigData;
import org.dzmdre.food.ordering.system.restaurant.service.domain.event.OrderRejectedEvent;
import org.dzmdre.food.ordering.system.restaurant.service.domain.ports.output.message.publisher.OrderRejectedMessagePublisher;
import org.dzmdre.food.ordering.system.restaurant.service.messaging.mapper.RestaurantMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static org.dzmdre.food.ordering.system.restaurant.service.domain.ports.output.message.publisher.OrderApprovedMessagePublisher.RESTAURANT_APPROVAL_RESPONSE_AVRO_MODEL;

@Slf4j
@Component
@AllArgsConstructor
public class OrderRejectedKafkaMessagePublisher implements OrderRejectedMessagePublisher {
    private final RestaurantMessagingDataMapper restaurantMessagingDataMapper;
    private final KafkaProducer<String, RestaurantApprovalResponseAvroModel> kafkaProducer;
    private final RestaurantServiceConfigData restaurantServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;

    @Override
    public void publish(OrderRejectedEvent orderRejectedEvent) {
        final String orderId = orderRejectedEvent.getOrderApproval().getOrderId().getValue().toString();
        log.info("Received OrderRejectedEvent for order id: {}", orderId);
        try {
            final RestaurantApprovalResponseAvroModel restaurantApprovalResponseAvroModel =
                    restaurantMessagingDataMapper
                            .orderRejectedEventToRestaurantApprovalResponseAvroModel(orderRejectedEvent);
            kafkaProducer.send(restaurantServiceConfigData.getRestaurantApprovalResponseTopicName(),
                    orderId,
                    restaurantApprovalResponseAvroModel,
                    kafkaMessageHelper.getKafkaCallback(restaurantServiceConfigData
                                    .getRestaurantApprovalResponseTopicName(),
                            restaurantApprovalResponseAvroModel,
                            orderId,
                            RESTAURANT_APPROVAL_RESPONSE_AVRO_MODEL));
            log.info("RestaurantApprovalResponseAvroModel sent to kafka at: {}", System.nanoTime());
        } catch (Exception e) {
            log.error("Error while sending RestaurantApprovalResponseAvroModel message" +
                    " to kafka with order id: {}, error: {}", orderId, e.getMessage());
        }
    }
}
