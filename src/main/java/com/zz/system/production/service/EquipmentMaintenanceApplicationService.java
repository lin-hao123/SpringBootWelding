package com.zz.system.production.service;

import com.zz.system.production.entity.EquipmentMaintenanceApplication;
import org.springframework.data.domain.Page;

/**
 * created by linlx on 2020/1/21
 */
public interface EquipmentMaintenanceApplicationService {

    Page<EquipmentMaintenanceApplication> findByDeviceNameLike(String deviceName,int page,int size);

    EquipmentMaintenanceApplication findById(Long equipmentMaintenanceApplicationId);

    EquipmentMaintenanceApplication create(EquipmentMaintenanceApplication equipmentMaintenanceApplication);

    void delete(Long equipmentMaintenanceApplicationId);
}
