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
    public DeviceMaintenance findByDeviceId(String deviceId) {
        return deviceMaintenanceRepository.findByDeviceId(deviceId);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(String deviceId) {
        deviceMaintenanceRepository.deleteByDeviceId(deviceId);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public DeviceMaintenance create(DeviceMaintenance deviceMaintenance) {
        deviceMaintenance.setMaintenanceTime(new Date());
        return deviceMaintenanceRepository.save(deviceMaintenance);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public Integer updateDeviceMaintenance(DeviceMaintenance deviceMaintenance) {
        return deviceMaintenanceRepository.updateDeviceMaintenance(deviceMaintenance);
    }
}
