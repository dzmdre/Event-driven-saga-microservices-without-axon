package org.dzmdre.food.ordering.system.restaurant.service.dataaccess.restaurant.adapter;

import lombok.AllArgsConstructor;
import org.dzmdre.food.ordering.system.dataaccess.restaurant.entity.RestaurantEntity;
import org.dzmdre.food.ordering.system.dataaccess.restaurant.repository.RestaurantJpaRepository;
import org.dzmdre.food.ordering.system.restaurant.service.dataaccess.restaurant.mapper.RestaurantDataAccessMapper;
import org.dzmdre.food.ordering.system.restaurant.service.domain.entity.Restaurant;
import org.dzmdre.food.ordering.system.restaurant.service.domain.ports.output.repository.RestaurantRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantDataAccessMapper restaurantDataAccessMapper;

    @Override
    public Optional<Restaurant> findRestaurantInformation(Restaurant restaurant) {
        final List<UUID> restaurantProducts =
                restaurantDataAccessMapper.restaurantToRestaurantProducts(restaurant);
        final Optional<List<RestaurantEntity>> restaurantEntities = restaurantJpaRepository
                .findByRestaurantIdAndProductIdIn(restaurant.getId().getValue(),
                        restaurantProducts);
        return restaurantEntities.map(restaurantDataAccessMapper::restaurantEntityToRestaurant);
    }
}
