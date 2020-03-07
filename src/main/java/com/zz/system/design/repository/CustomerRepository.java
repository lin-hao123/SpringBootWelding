package com.zz.system.design.repository;

import com.zz.system.design.entity.Customer;
import com.zz.user.entity.Permission;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * create by linlx on 2020/01/14
 *
 */

public interface CustomerRepository extends JpaRepository<Customer,Long> {


    Page<Customer> findByCustomerNameLike(String customerName, Pageable pageable);
    
    @Modifying
	@Query("update Customer cs set "+
	"cs.customerName=CASE WHEN:#{#m.customerName} IS NULL THEN cs.customerName ELSE:#{#m.customerName} END,"+
	"cs.customerTel=CASE WHEN:#{#m.customerTel} IS NULL THEN cs.customerTel ELSE:#{#m.customerTel} END,"+
	"cs.address=CASE WHEN:#{#m.address} IS NULL THEN cs.address ELSE:#{#m.address} END,"+
	"cs.sex=CASE WHEN:#{#m.sex} IS NULL THEN cs.sex ELSE:#{#m.sex} END,"+
	"cs.createTime=CASE WHEN:#{#m.createTime} IS NULL THEN cs.createTime ELSE:#{#m.createTime} END,"+
	"cs.updateTime=CASE WHEN:#{#m.updateTime} IS NULL THEN cs.updateTime ELSE:#{#m.updateTime} END,"+
	"cs.productId=CASE WHEN:#{#m.productId} IS NULL THEN cs.productId ELSE:#{#m.productId} END "+
	"where cs.customerId=:#{#m.customerId}")
	public Integer updateCustomer(@Param("m") Customer customer);

}
