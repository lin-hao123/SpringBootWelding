package com.zz.system.device.repository;

import com.zz.system.device.entity.DeviceRepair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by linlx on 2020/1/16
 */
public interface DeviceRepairRepository extends JpaRepository<DeviceRepair,Long> {

    Page<DeviceRepair> findByDeviceNameLike(String deviceName, Pageable pageable);
}
