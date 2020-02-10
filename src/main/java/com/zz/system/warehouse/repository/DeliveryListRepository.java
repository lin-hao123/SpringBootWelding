package com.zz.system.warehouse.repository;

import com.zz.system.warehouse.entity.DeliveryList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * created by linlx on 2020/1/19
 */
public interface DeliveryListRepository extends JpaRepository<DeliveryList,Long> {

    Page<DeliveryList> findByProductIdLike(String productId, Pageable pageable);
}
