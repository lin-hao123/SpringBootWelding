package com.zz.system.warehouse.repository;

import com.zz.system.warehouse.entity.MaterialRequestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by linlx on 2020/1/15
 */
public interface MaterialRequestionRepository extends JpaRepository<MaterialRequestion,Long> {

    Page<MaterialRequestion> findByMaterialIdLike(String materialId, Pageable pageable);

}
