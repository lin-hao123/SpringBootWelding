package com.zz.system.device.service;

import com.zz.system.device.entity.Device;
import org.springframework.data.domain.Page;



/**
 * created by linlx on 2020/1/16
 */
public interface DeviceService {


    Page<Device> findByDeviceNameLike(String deviceName, int page, int size);

    Device createDevice(Device device);

    Device findByDeviceId(Long deviceId);

    void delete(Long deviceId);

    void update(Device device);

}
