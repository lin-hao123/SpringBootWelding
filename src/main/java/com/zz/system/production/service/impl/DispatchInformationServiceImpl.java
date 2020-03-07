package com.zz.system.production.service.impl;

import com.zz.system.production.entity.DispatchInformation;
import com.zz.system.production.repository.DispatchInformationRepository;
import com.zz.system.production.service.DispatchInformationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * created by linlx on 2020/1/22
 */

@Service
public class DispatchInformationServiceImpl implements DispatchInformationService {


    @Resource
    DispatchInformationRepository dispatchInformationRepository;


    @Override
    public Page<DispatchInformation> findByProductNameLike(String productName, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"finishTime");
        Pageable pageable= PageRequest.of(page,size,sort);
        if (productName==null||productName==""){
            productName= "%%";
        }else {
            productName= "%"+productName+"%";
        }
        return dispatchInformationRepository.findByProductNameLike(productName,pageable);
    }

    @Override
    public DispatchInformation findById(Long dispatchId) {
        return dispatchInformationRepository.findById(dispatchId).get();
    }

    @Override
    public DispatchInformation create(DispatchInformation dispatchInformation) {
        return dispatchInformationRepository.save(dispatchInformation);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(Long dispatchId) {
        dispatchInformationRepository.deleteById(dispatchId);

    }

	@Override
	 @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
	public Integer updateDispatchInformation(DispatchInformation dispatchInformation) {
		return dispatchInformationRepository.updateDispatchInformation(dispatchInformation);
	}
}
