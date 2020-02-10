package com.zz.system.device.service;

import com.zz.system.device.entity.DeviceMaintenance;
import org.springframework.data.domain.Page;


/**
 * created by linlx on 2020/1/16
 */
public interface DeviceMaintenanceService {

    Page<DeviceMaintenance> findByDeviceNameLike(String deviceName, int page, int size);

    DeviceMaintenance findByDeviceId(Long deviceId);

    void delete(Long deviceId);

    DeviceMaintenance create(DeviceMaintenance deviceMaintenance);
}
