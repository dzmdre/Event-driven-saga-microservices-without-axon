package org.dzmdre.food.ordering.system.restaurant.service.dataaccess.restaurant.adapter;

import lombok.AllArgsConstructor;
import org.dzmdre.food.ordering.system.restaurant.service.dataaccess.restaurant.repository.OrderApprovalJpaRepository;
import org.dzmdre.food.ordering.system.restaurant.service.dataaccess.restaurant.mapper.RestaurantDataAccessMapper;
import org.dzmdre.food.ordering.system.restaurant.service.domain.entity.OrderApproval;
import org.dzmdre.food.ordering.system.restaurant.service.domain.ports.output.repository.OrderApprovalRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderApprovalRepositoryImpl implements OrderApprovalRepository {

    private final OrderApprovalJpaRepository orderApprovalJpaRepository;
    private final RestaurantDataAccessMapper restaurantDataAccessMapper;

    @Override
    public OrderApproval save(OrderApproval orderApproval) {
        return restaurantDataAccessMapper
                .orderApprovalEntityToOrderApproval(orderApprovalJpaRepository
                        .save(restaurantDataAccessMapper.orderApprovalToOrderApprovalEntity(orderApproval)));
    }
}
