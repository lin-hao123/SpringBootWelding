package com.zz.system.design.service;

import com.zz.system.design.entity.Customer;
import org.springframework.data.domain.Page;



public interface CustomerService {

    Page<Customer> findByCustomerNameLike(String customerName, int page, int size);

    Customer create(Customer customer);

    void delete(Long customerId);

    Customer findById(Long customerId);

}
