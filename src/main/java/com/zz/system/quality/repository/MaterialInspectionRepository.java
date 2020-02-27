package com.zz.system.quality.repository;

import com.zz.system.quality.entity.MaterialInspection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by linlx on 2020/2/24
 */
public interface MaterialInspectionRepository extends JpaRepository<MaterialInspection,Long> {

    Page<MaterialInspection> findByInspectionStaffLike(String inspectionStaff, Pageable pageable);
}
