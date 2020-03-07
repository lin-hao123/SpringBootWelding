package com.zz.system.quality.service;

import com.zz.system.quality.entity.MaterialInspection;
import org.springframework.data.domain.Page;

/**
 * created by linlx on 2020/2/24
 */
public interface MaterialInspectionService {

    Page<MaterialInspection> findByInspectionStaffLike(String inspectionStaff, int page, int size);

    MaterialInspection create(MaterialInspection materialInspection);

    void delete(Long materialInspectionId);

    MaterialInspection findById(Long materialInspectionId);
    
    Integer updateMaterialInspection(MaterialInspection materialInspection);
}
