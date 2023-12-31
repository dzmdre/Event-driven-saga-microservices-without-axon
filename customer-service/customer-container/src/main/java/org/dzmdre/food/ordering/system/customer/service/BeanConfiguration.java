package org.dzmdre.food.ordering.system.customer.service;

import org.dzmdre.food.ordering.system.customer.service.domain.CustomerDomainService;
import org.dzmdre.food.ordering.system.customer.service.domain.CustomerDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CustomerDomainService customerDomainService() {
        return new CustomerDomainServiceImpl();
    }
}
