package com.zz.system.device.service;

import com.zz.system.device.entity.DeviceRepair;
import org.springframework.data.domain.Page;

/**
 * created by linlx on 2020/1/16
 */
public interface DeviceRepairService {

    Page<DeviceRepair> findByDeviceNameLike(String deviceName, int page,int size);

    DeviceRepair findByDeviceId(Long deviceId);

    void deleteDeviceRepair(Long deviceId);

    DeviceRepair create(DeviceRepair deviceRepair);
}
