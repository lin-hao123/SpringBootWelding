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

    @Modifying
    @Query("update Device d set "+
            "d.deviceName=CASE WHEN:#{#m.deviceName} IS NULL THEN d.deviceName ELSE:#{#m.deviceName} END,"+
            "d.vendor=CASE WHEN:#{#m.vendor} IS NULL THEN d.vendor ELSE:#{#m.vendor} END,"+
            "d.vendorTel=CASE WHEN:#{#m.vendorTel} IS NULL THEN d.vendorTel ELSE:#{#m.vendorTel} END,"+
            "d.weldingType=CASE WHEN:#{#m.weldingType} IS NULL THEN d.weldingType ELSE:#{#m.weldingType} END,"+
            "d.department=CASE WHEN:#{#m.department} IS NULL THEN d.department ELSE:#{#m.department} END,"+
            "d.deviceFunction=CASE WHEN:#{#m.deviceFunction} IS NULL THEN d.deviceFunction ELSE:#{#m.deviceFunction} END,"+
            "d.responsible=CASE WHEN:#{#m.responsible} IS NULL THEN d.responsible ELSE:#{#m.responsible} END "+
            "where d.deviceId=:#{#m.deviceId}")
    void update(@Param("m") Device device);

}
