package com.zz.system.device.repository;

import com.zz.system.device.entity.Device;
import com.zz.system.device.entity.DeviceMaintenance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * created by linlx on 2020/1/16
 */
public interface DeviceMaintenanceRepository extends JpaRepository<DeviceMaintenance,Long > {

    Page<DeviceMaintenance> findByDeviceNameLike(String deviceName, Pageable pageable);

    DeviceMaintenance findByDeviceId(String deviceId);

    void deleteByDeviceId(String deviceId);

    @Modifying
    @Query("update DeviceMaintenance dm set "+
            "dm.deviceName=CASE WHEN:#{#m.deviceName} IS NULL THEN dm.deviceName ELSE:#{#m.deviceName} END,"+
            "dm.maintenanceContent=CASE WHEN:#{#m.maintenanceContent} IS NULL THEN dm.maintenanceContent ELSE:#{#m.maintenanceContent} END,"+
            "dm.responsible=CASE WHEN:#{#m.responsible} IS NULL THEN dm.responsible ELSE:#{#m.responsible} END,"+
            "dm.maintenanceTime=CASE WHEN:#{#m.maintenanceTime} IS NULL THEN dm.maintenanceTime ELSE:#{#m.maintenanceTime} END "+
            "where dm.deviceId=:#{#m.deviceId}")
    Integer updateDeviceMaintenance(@Param("m") DeviceMaintenance deviceMaintenance);
}
