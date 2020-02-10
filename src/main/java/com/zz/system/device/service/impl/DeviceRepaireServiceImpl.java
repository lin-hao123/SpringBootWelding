package com.zz.system.device.service.impl;

import com.zz.system.device.entity.DeviceRepair;
import com.zz.system.device.repository.DeviceRepairRepository;
import com.zz.system.device.service.DeviceRepairService;
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
public class DeviceRepaireServiceImpl implements DeviceRepairService {

    @Resource
    DeviceRepairRepository deviceRepairRepository;

    @Override
    public Page<DeviceRepair> findByDeviceNameLike(String deviceName, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"repairTime");
        Pageable pageable= PageRequest.of(page,size,sort);
        if (deviceName==null||deviceName==""){
            deviceName= "%%";
        }else {
            deviceName= "%"+deviceName+"%";
        }
        return deviceRepairRepository.findByDeviceNameLike(deviceName,pageable);
    }

    @Override
    public DeviceRepair findByDeviceId(Long deviceId) {
        return deviceRepairRepository.findById(deviceId).get();
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void deleteDeviceRepair(Long deviceId) {
        deviceRepairRepository.deleteById(deviceId);

    }

    @Override
    public DeviceRepair create(DeviceRepair deviceRepair) {
        deviceRepair.setRepairTime(new Date());
        return deviceRepairRepository.save(deviceRepair);
    }
}
