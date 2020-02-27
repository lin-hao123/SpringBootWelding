package com.zz.system.production.repository;

import com.zz.system.production.entity.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by linlx on 2020/2/25
 */
public interface EquipmentRepository extends JpaRepository<Equipment,Long> {

    Page<Equipment> findByEquipmentNameLike(String equipmentName, Pageable pageable);
}
