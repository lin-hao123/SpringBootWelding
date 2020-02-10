package com.zz.system.design.service.impl;

import com.zz.system.design.entity.Customer;
import com.zz.system.design.repository.CustomerRepository;
import com.zz.system.design.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    CustomerRepository customerRepository;


    @Override
    public Page<Customer> findByCustomerNameLike(String customerName, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable= PageRequest.of(page,size,sort);
        if(customerName==null||customerName==""){
            customerName="%%";
        }else {
            customerName="%"+customerName+"%";
        }
        return customerRepository.findByCustomerNameLike(customerName,pageable);
    }

    @Override
    public Customer create(Customer customer) {
        customer.setCreateTime(new Date());
        customer.setUpdateTime(new Date());
        return customerRepository.save(customer);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer findById(Long customerId) {
        return customerRepository.findById(customerId).get();
    }



}
