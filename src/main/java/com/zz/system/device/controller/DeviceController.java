package com.zz.system.device.controller;


import com.zz.system.device.entity.Device;
import com.zz.system.device.entity.DeviceMaintenance;
import com.zz.system.device.entity.DeviceRepair;
import com.zz.system.device.service.DeviceMaintenanceService;
import com.zz.system.device.service.DeviceRepairService;
import com.zz.system.device.service.DeviceService;
import com.zz.vo.ResponseData;
import com.zz.vo.ResponseDataUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * created by linlx on 2020/1/16
 */

@RestController
@RequestMapping("deviceController")
public class DeviceController {

    @Resource
    DeviceService deviceService;

    @Resource
    DeviceMaintenanceService deviceMaintenanceService;

    @Resource
    DeviceRepairService deviceRepairService;

   //=====================================================设备基本信息管理模块===============================================
    /**
     * 分页显示设备信息
     * @param page
     * @param size
     * @param deviceName
     * @return
     */
    @GetMapping("device/{page}/{size}")
    public ResponseData queryDevicePage(@PathVariable("page") int page, @PathVariable("size") int size,  String deviceName){

        ResponseData responseData=new ResponseData();
        Page<Device> devicePage =deviceService.findByDeviceNameLike(deviceName,page,size);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(devicePage);
       return responseData;
    }

    /**
     * 回显设备信息
     * @param deviceId
     * @return
     */
    @GetMapping("device/{deviceId}")
    public ResponseData findDeviceByDeviceId(@PathVariable("deviceId") String  deviceId){
        ResponseData responseData=new ResponseData();
        Device device=deviceService.findByDeviceId(deviceId);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(device);
        return responseData;
    }

    @PutMapping("device/update/{id}")
    public ResponseData updateDevice(@PathVariable(value = "id") String id, @RequestBody Device device) {
        device.setDeviceId(id);
        Integer rs= deviceService.updateDevice(device);
        if(rs==1){
            return ResponseDataUtil.success("修改成功",rs);
        }else{
            return ResponseDataUtil.failure(500, "修改失败");
        }
    }

    /**
     * 添加设备
     * @param device
     * @return
     */
    @PostMapping("device")
    public ResponseData createDevice(@RequestBody Device device){

        ResponseData responseData=new ResponseData();
        Device newDevice=deviceService.createDevice(device);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(newDevice);
        return responseData;
    }


    /**
     * 删除设备
     * @param deviceId
     * @return
     */
    @DeleteMapping("device/{deviceId}")
    public ResponseData deleteDevice(@PathVariable ("deviceId") String deviceId){

        ResponseData responseData=new ResponseData();
        deviceService.delete(deviceId);
        responseData.setCode(200);
        responseData.setMsg("success");
        return responseData;
    }





    //=====================================================设备保养日志信息管理模块===============================================

    /**
     * 分页获取设备保养日志
     * @param page
     * @param size
     * @param deviceName
     * @return
     */
    @GetMapping("deviceMaintenance/{page}/{size}")
    public ResponseData queryDeviceMaintenancePage(@PathVariable("page") int page, @PathVariable("size") int size, String deviceName){

        ResponseData responseData=new ResponseData();
        Page<DeviceMaintenance> deviceMaintenancePage=deviceMaintenanceService.findByDeviceNameLike(deviceName,page,size);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(deviceMaintenancePage);
        return responseData;
    }


    /**
     * 创建设备保养日志
     * @param deviceMaintenance
     * @return8
     */
    @PostMapping("deviceMaintenance")
    public ResponseData createDeviceMaintenance(@RequestBody DeviceMaintenance deviceMaintenance){

        ResponseData responseData=new ResponseData();
        DeviceMaintenance newDeviceMaintenance=deviceMaintenanceService.create(deviceMaintenance);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(newDeviceMaintenance);
        return responseData;
    }

    /**
     * 回显设备保养信息
     * @param deviceId
     * @return
     */
    @GetMapping("deviceMaintenance/{deviceId}")
    public ResponseData findDeviceMaintenanceByDeviceId(@PathVariable("deviceId") String deviceId){

        ResponseData responseData=new ResponseData();
        DeviceMaintenance deviceMaintenance=deviceMaintenanceService.findByDeviceId(deviceId);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(deviceMaintenance);
        return responseData;
    }


    /**
     * 删除设备保养日志
     * @param deviceId
     * @return
     */
    @DeleteMapping("deviceMaintenance/{deviceId}")
    public ResponseData deleteDeviceMaintenance(@PathVariable("deviceId") String deviceId){

        ResponseData responseData=new ResponseData();
        deviceMaintenanceService.delete(deviceId);
        responseData.setCode(200);
        responseData.setMsg("success");
        return responseData;
    }

    @PutMapping("deviceMaintenance/update/{id}")
    public ResponseData updateDeviceMaintenance(@PathVariable(value = "id") String id, @RequestBody DeviceMaintenance deviceMaintenance) {
      deviceMaintenance.setDeviceId(id);
      Integer rs= deviceMaintenanceService.updateDeviceMaintenance(deviceMaintenance);
      if(rs==1){
          return ResponseDataUtil.success("修改成功",rs);
        }else{
            return ResponseDataUtil.failure(500, "修改失败");
        }
    }





    //=====================================================设备保养日志信息管理模块===============================================

    /**
     * 分页获取设备维修记录
     * @param page
     * @param size
     * @param deviceName
     * @return
     */
    @GetMapping("deviceRepair/{page}/{size}")
    public ResponseData queryDeviceRepairPage(@PathVariable("page") int page, @PathVariable("size") int size, String deviceName){

        ResponseData responseData=new ResponseData();
        Page<DeviceRepair> deviceRepairPage=deviceRepairService.findByDeviceNameLike(deviceName,page,size);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(deviceRepairPage);
        return responseData;
    }


    /**
     * 回显设备维修记录
     * @param deviceId
     * @return
     */
    @GetMapping("deviceRepair/{deviceId}")
    public ResponseData findDeviceRepairByDeviceId(@PathVariable("deviceId") String deviceId){

        ResponseData responseData=new ResponseData();
        DeviceRepair deviceRepair=deviceRepairService.findByDeviceId(deviceId);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(deviceRepair);
        return responseData;
    }


    /**
     * 删除设备维修记录
     * @param deviceId
     * @return
     */
    @DeleteMapping("deviceRepair/{deviceId}")
    public ResponseData deleteDeviceRepair(@PathVariable("deviceId") String  deviceId){

        ResponseData responseData=new ResponseData();
        deviceRepairService.deleteDeviceRepair(deviceId);
        responseData.setCode(200);
        responseData.setMsg("success");
        return responseData;
    }

    /**
     * 创建设备维修记录
     * @param deviceRepair
     * @return
     */
    @PostMapping("deviceRepair")
    public ResponseData createDevice(@RequestBody DeviceRepair deviceRepair){

        ResponseData responseData=new ResponseData();
       DeviceRepair createDeviceRepair=deviceRepairService.create(deviceRepair);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(createDeviceRepair);
        return responseData;
    }


    @PutMapping("deviceRepair/update/{id}")
    public ResponseData updateDeviceRepair(@PathVariable(value = "id") String id, @RequestBody DeviceRepair deviceRepair) {
        deviceRepair.setDeviceId(id);
        Integer rs= deviceRepairService.updateDeviceRepair(deviceRepair);
        if(rs==1){
            return ResponseDataUtil.success("修改成功",rs);
        }else{
            return ResponseDataUtil.failure(500, "修改失败");
        }
    }





}
