package com.zz.system.warehouse.service.impl;

import com.zz.system.warehouse.entity.DeliveryList;
import com.zz.system.warehouse.repository.DeliveryListRepository;
import com.zz.system.warehouse.service.DeliveryListService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * created by linlx on 2020/1/19
 */

@Service
public class DeliveryListServiceImpl implements DeliveryListService {

    @Resource
    DeliveryListRepository deliveryListRepository;


    @Override
    public Page<DeliveryList> findByProductIdLike(String productId, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"outTime");
        Pageable pageable= PageRequest.of(page,size,sort);
        if(productId==null || productId==""){
            productId="%%";
        }else {
            productId="%"+productId+"%";
        }
        return deliveryListRepository.findByProductIdLike(productId,pageable);
    }

    @Override
    public DeliveryList create(DeliveryList deliveryList) {
        deliveryList.setOutTime(new Date());
        return deliveryListRepository.save(deliveryList);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(Long deliveryListId) {
        deliveryListRepository.deleteById(deliveryListId);
    }

    @Override
    public DeliveryList findById(Long deliveryListId) {
        return deliveryListRepository.findById(deliveryListId).get();
    }
}
