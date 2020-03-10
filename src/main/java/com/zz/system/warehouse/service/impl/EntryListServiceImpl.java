package com.zz.system.warehouse.service.impl;

import com.zz.system.warehouse.entity.EntryList;
import com.zz.system.warehouse.repository.EntryListRepository;
import com.zz.system.warehouse.service.EntryListService;
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
public class EntryListServiceImpl implements EntryListService {

    @Resource
    EntryListRepository entryListRepository;

    @Override
    public Page<EntryList> findByWeldingProductsLike(String weldingProducts, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"entryTime");
        Pageable pageable= PageRequest.of(page,size,sort);
        if(weldingProducts==null || weldingProducts==""){
            weldingProducts="%%";
        }else {
            weldingProducts="%"+weldingProducts+"%";
        }
        return entryListRepository.findByWeldingProductsLike(weldingProducts,pageable);
    }

    @Override
    public EntryList create(EntryList entryList) {
        entryList.setEntryTime(new Date());
        return entryListRepository.save(entryList);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(Long entryListId) {
        entryListRepository.deleteById(entryListId);
    }

    @Override
    public EntryList findById(Long entryListId) {
        return entryListRepository.findById(entryListId).get();
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public Integer updateEntryList(EntryList entryList) {
        return entryListRepository.updateEntryList(entryList);
    }
}
