package com.zz.system.device.repository;

import com.zz.system.device.entity.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * created by linlx on 2020/1/16
 */
public interface DeviceRepository extends JpaRepository<Device,Long > {

    Page<Device> findByDeviceNameLike(String deviceName,Pageable pageable);

    Device findByDeviceId(String deviceId);

    void deleteByDeviceId(String deviceId);

    @Modifying
    @Query("update Device d set "+
            "d.deviceName=CASE WHEN:#{#m.deviceName} IS NULL THEN d.deviceName ELSE:#{#m.deviceName} END,"+
            "d.vendor=CASE WHEN:#{#m.vendor} IS NULL THEN d.vendor ELSE:#{#m.vendor} END,"+
            "d.vendorTel=CASE WHEN:#{#m.vendorTel} IS NULL THEN d.vendorTel ELSE:#{#m.vendorTel} END,"+
            "d.weldingType=CASE WHEN:#{#m.weldingType} IS NULL THEN d.weldingType ELSE:#{#m.weldingType} END,"+
            "d.location=CASE WHEN:#{#m.location} IS NULL THEN d.location ELSE:#{#m.location} END,"+
            "d.purchaseTime=CASE WHEN:#{#m.purchaseTime} IS NULL THEN d.purchaseTime ELSE:#{#m.purchaseTime} END,"+
            "d.responsible=CASE WHEN:#{#m.responsible} IS NULL THEN d.responsible ELSE:#{#m.responsible} END "+
            "where d.deviceId=:#{#m.deviceId}")
    Integer updateDevice(@Param("m") Device device);

}
