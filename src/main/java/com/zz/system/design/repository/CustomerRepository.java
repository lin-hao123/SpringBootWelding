package com.zz.system.design.repository;

import com.zz.system.design.entity.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * create by linlx on 2020/01/14
 *
 */

public interface CustomerRepository extends JpaRepository<Customer,Long> {


    Page<Customer> findByCustomerNameLike(String customerName, Pageable pageable);

}
