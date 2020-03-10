package com.zz.system.warehouse.service.impl;

import com.zz.system.warehouse.entity.Supplier;
import com.zz.system.warehouse.repository.SupplierRepository;
import com.zz.system.warehouse.service.SupplierService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * created by linlx on 2020/1/20
 */

@Service
public class SupplierServiceImpl implements SupplierService {

    @Resource
    SupplierRepository supplierRepository;


    @Override
    public Page<Supplier> findBySupplierNameLike(String supplierName, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"supplierId");
        Pageable pageable= PageRequest.of(page,size,sort);
        if(supplierName==null || supplierName==""){
            supplierName="%%";
        }else {
            supplierName="%"+supplierName+"%";
        }
        return supplierRepository.findBySupplierNameLike(supplierName,pageable);
    }

    @Override
    public Supplier findById(Long supplierId) {
        return supplierRepository.findById(supplierId).get();
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public Supplier create(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(Long supplierId) {
        supplierRepository.deleteById(supplierId);

    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public Integer updateSupplier(Supplier supplier) {
        return supplierRepository.updateSupplier(supplier);
    }
}
