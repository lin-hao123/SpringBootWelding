package com.zz.system.production.service;

import com.zz.system.production.entity.Equipment;
import org.springframework.data.domain.Page;

/**
 * created by linlx on 2020/2/25
 */
public interface EquipmentService {

    Page<Equipment> findByEquipmentNameLike(String equipmentName,int page,int size);

    Equipment create(Equipment equipment);

    void delete(Long equipmentId);

    Equipment findById(Long equipmentId);
    
    Integer updateEquipment(Equipment equipment);

}
