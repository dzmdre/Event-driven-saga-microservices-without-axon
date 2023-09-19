package org.dzmdre.food.ordering.system.customer.service.dataaccess.customer.mapper;

import org.dzmdre.food.ordering.system.customer.service.dataaccess.customer.entity.CustomerEntity;
import org.dzmdre.food.ordering.system.customer.service.domain.entity.Customer;
import org.dzmdre.food.ordering.system.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()),
                customerEntity.getUsername(),
                customerEntity.getFirstName(),
                customerEntity.getLastName());
    }

    public CustomerEntity customerToCustomerEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId().getValue())
                .username(customer.getUsername())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }

}
