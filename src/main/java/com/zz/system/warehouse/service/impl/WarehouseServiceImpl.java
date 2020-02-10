package com.zz.system.warehouse.service.impl;

import com.zz.system.warehouse.service.WarehouseService;
import com.zz.system.warehouse.entity.Warehouse;
import com.zz.system.warehouse.repository.WarehouseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * created by linlx on 2020/1/20
 */

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Resource
    WarehouseRepository warehouseRepository;


    @Override
    public Page<Warehouse> findByWarehouseNameLike(String warehouseName, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"warehouseId");
        Pageable pageable= PageRequest.of(page,size,sort);
        if(warehouseName==null || warehouseName==""){
            warehouseName="%%";
        }else {
            warehouseName="%"+warehouseName+"%";
        }
        return warehouseRepository.findByWarehouseNameLike(warehouseName,pageable);
    }

    @Override
    public Warehouse create(Warehouse warehouse) {
        warehouse.setCreateTime(new Date());
        warehouse.setUpdateTime(new Date());
        return warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse findById(Long warehouseId) {
        return warehouseRepository.findById(warehouseId).get();
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(Long warehouseId) {
        warehouseRepository.deleteById(warehouseId);
    }
}
