package com.zz.system.device.repository;

import com.zz.system.device.entity.Device;
import com.zz.system.device.entity.DeviceRepair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * created by linlx on 2020/1/16
 */
public interface DeviceRepairRepository extends JpaRepository<DeviceRepair,Long> {

    Page<DeviceRepair> findByDeviceNameLike(String deviceName, Pageable pageable);

    DeviceRepair findByDeviceId(String deviceId);

    void  deleteByDeviceId(String deviceId);

    @Modifying
    @Query("update DeviceRepair dr set "+
            "dr.deviceName=CASE WHEN:#{#m.deviceName} IS NULL THEN dr.deviceName ELSE:#{#m.deviceName} END,"+
            "dr.repairContent=CASE WHEN:#{#m.repairContent} IS NULL THEN dr.repairContent ELSE:#{#m.repairContent} END,"+
            "dr.repairResult=CASE WHEN:#{#m.repairResult} IS NULL THEN dr.repairResult ELSE:#{#m.repairResult} END,"+
            "dr.repairStaff=CASE WHEN:#{#m.repairStaff} IS NULL THEN dr.repairStaff ELSE:#{#m.repairStaff} END,"+
            "dr.repairTime=CASE WHEN:#{#m.repairTime} IS NULL THEN dr.repairTime ELSE:#{#m.repairTime} END "+
            "where dr.deviceId=:#{#m.deviceId}")
    Integer updateDeviceRepair(@Param("m") DeviceRepair deviceRepair);
}
