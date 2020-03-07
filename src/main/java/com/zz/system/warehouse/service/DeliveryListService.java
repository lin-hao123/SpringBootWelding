package com.zz.system.warehouse.service;

import com.zz.system.warehouse.entity.DeliveryList;
import org.springframework.data.domain.Page;

/**
 * created by linlx on 2020/1/19
 */
public interface DeliveryListService {

    Page<DeliveryList> findByProductIdLike(String productId,int page,int size);

    DeliveryList create(DeliveryList deliveryList);

    void delete(Long deliveryListId);

    DeliveryList findById(Long deliveryListId);
    
    Integer updateDeliveryList(DeliveryList deliveryList);
}
