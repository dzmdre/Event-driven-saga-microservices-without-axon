package org.dzmdre.food.ordering.system.customer.service.dataaccess.customer.adapter;

import org.dzmdre.food.ordering.system.customer.service.dataaccess.customer.mapper.CustomerDataAccessMapper;
import org.dzmdre.food.ordering.system.customer.service.dataaccess.customer.repository.CustomerJpaRepository;
import org.dzmdre.food.ordering.system.customer.service.domain.entity.Customer;
import org.dzmdre.food.ordering.system.customer.service.domain.ports.output.repository.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;

    private final CustomerDataAccessMapper customerDataAccessMapper;

    public CustomerRepositoryImpl(CustomerJpaRepository customerJpaRepository,
                                  CustomerDataAccessMapper customerDataAccessMapper) {
        this.customerJpaRepository = customerJpaRepository;
        this.customerDataAccessMapper = customerDataAccessMapper;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerDataAccessMapper.customerEntityToCustomer(
                customerJpaRepository.save(customerDataAccessMapper.customerToCustomerEntity(customer)));
    }
}
