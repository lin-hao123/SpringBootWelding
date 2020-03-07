package com.zz.system.production.service.impl;

import com.zz.system.production.entity.EquipmentMaintenanceApplication;
import com.zz.system.production.repository.EquipmentMaintenanceApplicationRepository;
import com.zz.system.production.service.EquipmentMaintenanceApplicationService;
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
public class EquipmentMaintenanceApplicationServiceImpl implements EquipmentMaintenanceApplicationService {

    @Resource
    EquipmentMaintenanceApplicationRepository equipmentMaintenanceApplicationRepository;

    @Override
    public Page<EquipmentMaintenanceApplication> findByDeviceNameLike(String deviceName, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"applyTime");
        Pageable pageable= PageRequest.of(page,size,sort);
        if (deviceName==null||deviceName==""){
            deviceName= "%%";
        }else {
            deviceName= "%"+deviceName+"%";
        }
        return equipmentMaintenanceApplicationRepository.findByDeviceNameLike(deviceName,pageable);
    }

    @Override
    public EquipmentMaintenanceApplication findById(Long equipmentMaintenanceApplicationId) {
        return equipmentMaintenanceApplicationRepository.findById(equipmentMaintenanceApplicationId).get();
    }

    @Override
    public EquipmentMaintenanceApplication create(EquipmentMaintenanceApplication equipmentMaintenanceApplication) {
        equipmentMaintenanceApplication.setApplyTime(new Date());
        equipmentMaintenanceApplication.setStatus("待维修");
        return equipmentMaintenanceApplicationRepository.save(equipmentMaintenanceApplication);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(Long equipmentMaintenanceApplicationId) {
        equipmentMaintenanceApplicationRepository.deleteById(equipmentMaintenanceApplicationId);

    }

	@Override
	@Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
	public Integer updateEquipmentMaintenanceApplication(
			EquipmentMaintenanceApplication equipmentMaintenanceApplication) {
		return equipmentMaintenanceApplicationRepository.updateEquipmentMaintenanceApplication(equipmentMaintenanceApplication);
	}
}
