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

/**
 * created by linlx on 2020/1/16
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Resource
    DeviceRepository deviceRepository;


    @Override
    public Page<Device> findByDeviceNameLike(String deviceName, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"deviceId");
        Pageable pageable= PageRequest.of(page,size,sort);
        if (deviceName==null||deviceName==""){
            deviceName= "%%";
        }else {
            deviceName= "%"+deviceName+"%";
        }
        return deviceRepository.findByDeviceNameLike(deviceName,pageable);

    }

    @Override
    public Device createDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public Device findByDeviceId(Long deviceId) {
        return deviceRepository.findById(deviceId).get();
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(Long deviceId) {
        deviceRepository.deleteById(deviceId);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void update(Device device) {
        deviceRepository.update(device);

    }


}
