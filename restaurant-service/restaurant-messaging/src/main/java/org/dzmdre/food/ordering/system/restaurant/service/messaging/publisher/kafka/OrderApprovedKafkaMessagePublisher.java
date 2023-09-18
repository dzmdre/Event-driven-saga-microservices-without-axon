package org.dzmdre.food.ordering.system.restaurant.service.messaging.publisher.kafka;

import lombok.AllArgsConstructor;
import org.dzmdre.food.ordering.system.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import org.dzmdre.food.ordering.system.kafka.producer.KafkaMessageHelper;
import org.dzmdre.food.ordering.system.kafka.producer.service.KafkaProducer;
import org.dzmdre.food.ordering.system.restaurant.service.domain.config.RestaurantServiceConfigData;
import org.dzmdre.food.ordering.system.restaurant.service.domain.event.OrderApprovedEvent;
import org.dzmdre.food.ordering.system.restaurant.service.domain.ports.output.message.publisher.OrderApprovedMessagePublisher;
import org.dzmdre.food.ordering.system.restaurant.service.messaging.mapper.RestaurantMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class OrderApprovedKafkaMessagePublisher implements OrderApprovedMessagePublisher {

    private final RestaurantMessagingDataMapper restaurantMessagingDataMapper;
    private final KafkaProducer<String, RestaurantApprovalResponseAvroModel> kafkaProducer;
    private final RestaurantServiceConfigData restaurantServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;

    @Override
    public void publish(OrderApprovedEvent orderApprovedEvent) {
        final String orderId = orderApprovedEvent.getOrderApproval().getOrderId().getValue().toString();
        log.info("Received OrderApprovedEvent for order id: {}", orderId);
        try {
            final RestaurantApprovalResponseAvroModel restaurantApprovalResponseAvroModel =
                    restaurantMessagingDataMapper
                            .orderApprovedEventToRestaurantApprovalResponseAvroModel(orderApprovedEvent);
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
