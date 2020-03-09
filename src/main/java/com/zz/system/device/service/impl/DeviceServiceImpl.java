package com.zz.system.device.service.impl;


import com.zz.system.device.entity.Device;
import com.zz.system.device.repository.DeviceRepository;
import com.zz.system.device.service.DeviceService;
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
public class DeviceServiceImpl implements DeviceService {

    @Resource
    DeviceRepository deviceRepository;


    @Override
    public Page<Device> findByDeviceNameLike(String deviceName, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"purchaseTime");
        Pageable pageable= PageRequest.of(page,size,sort);
        if (deviceName==null||deviceName==""){
            deviceName= "%%";
        }else {
            deviceName= "%"+deviceName+"%";
        }
        return deviceRepository.findByDeviceNameLike(deviceName,pageable);

    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public Device createDevice(Device device) {
        device.setPurchaseTime(new Date());
        return deviceRepository.save(device);
    }

    @Override
    public Device findByDeviceId(String deviceId) {
        return deviceRepository.findByDeviceId(deviceId);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(String deviceId) {
        deviceRepository.deleteByDeviceId(deviceId);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public Integer updateDevice(Device device) {
        return deviceRepository.updateDevice(device);
    }

}
