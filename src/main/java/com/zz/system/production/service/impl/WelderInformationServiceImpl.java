package com.zz.system.production.service.impl;

import com.zz.system.production.entity.WelderInformation;
import com.zz.system.production.repository.WelderInformationRepository;
import com.zz.system.production.service.WelderInformationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * created by linlx on 2020/1/21
 */
@Service
public class WelderInformationServiceImpl implements WelderInformationService {

    @Resource
    WelderInformationRepository welderInformationRepository;

    @Override
    public Page<WelderInformation> findByWelderNameLike(String welderName, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable= PageRequest.of(page,size,sort);
        if (welderName==null||welderName==""){
            welderName= "%%";
        }else {
            welderName= "%"+welderName+"%";
        }
        return welderInformationRepository.findByWelderNameLike(welderName,pageable);
    }

    @Override
    public WelderInformation findById(Long welderId) {
        return welderInformationRepository.findById(welderId).get();
    }

    @Override
    public WelderInformation create(WelderInformation welderInformation) {
        welderInformation.setCreateTime(new Date());
        welderInformation.setUpdateTime(new Date());
        return welderInformationRepository.save(welderInformation);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(Long welderId) {
        welderInformationRepository.deleteById(welderId);

    }
}
