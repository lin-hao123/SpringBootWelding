package com.zz.system.device.service.impl;

import com.zz.system.device.entity.DeviceMaintenance;
import com.zz.system.device.repository.DeviceMaintenanceRepository;
import com.zz.system.device.service.DeviceMaintenanceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * created by linlx on 2020/1/16
 */
@Service
public class DeviceMaintenanceServiceImpl implements DeviceMaintenanceService {

    @Resource
    DeviceMaintenanceRepository deviceMaintenanceRepository;
    @Override
    public Page<DeviceMaintenance> findByDeviceNameLike(String deviceName, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"maintenanceTime");
        Pageable pageable= PageRequest.of(page,size,sort);
        if (deviceName==null||deviceName==""){
            deviceName= "%%";
        }else {
            deviceName= "%"+deviceName+"%";
        }
        return deviceMaintenanceRepository.findByDeviceNameLike(deviceName,pageable);
    }

    @Override
    public DeviceMaintenance findByDeviceId(Long deviceId) {
        return deviceMaintenanceRepository.findById(deviceId).get();
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(Long deviceId) {
        deviceMaintenanceRepository.deleteById(deviceId);
    }

    @Override
    public DeviceMaintenance create(DeviceMaintenance deviceMaintenance) {
        deviceMaintenance.setMaintenanceTime(new Date());
        return deviceMaintenanceRepository.save(deviceMaintenance);
    }
}
