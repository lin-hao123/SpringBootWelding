package com.zz.system.quality.service.impl;

import com.zz.system.quality.entity.MaterialInspection;
import com.zz.system.quality.repository.MaterialInspectionRepository;
import com.zz.system.quality.service.MaterialInspectionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * created by linlx on 2020/2/24
 */
@Service
public class MaterialInspectionServiceImpl implements MaterialInspectionService {

    @Resource
    MaterialInspectionRepository materialInspectionRepository;

    @Override
    public Page<MaterialInspection> findByInspectionStaffLike(String inspectionStaff, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"detectionStatus");
        Pageable pageable= PageRequest.of(page,size,sort);
        if(inspectionStaff==null||inspectionStaff==""){
            inspectionStaff="%%";
        }else {
            inspectionStaff="%"+inspectionStaff+"%";
        }
        return materialInspectionRepository.findByInspectionStaffLike(inspectionStaff,pageable);
    }

    @Override
    public MaterialInspection create(MaterialInspection materialInspection) {
        return materialInspectionRepository.save(materialInspection);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(Long materialInspectionId) {
        materialInspectionRepository.deleteById(materialInspectionId);


    }

    @Override
    public MaterialInspection findById(Long materialInspectionId) {
        return materialInspectionRepository.findById(materialInspectionId).get();
    }
}
