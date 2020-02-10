package com.zz.system.production.controller;

import com.zz.system.device.entity.Device;
import com.zz.system.production.entity.DispatchInformation;
import com.zz.system.production.entity.EquipmentMaintenanceApplication;
import com.zz.system.production.entity.WelderInformation;
import com.zz.system.production.service.DispatchInformationService;
import com.zz.system.production.service.EquipmentMaintenanceApplicationService;
import com.zz.system.production.service.WelderInformationService;
import com.zz.vo.ResponseData;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * created by linlx on 2020/1/21
 */
@RestController
@RequestMapping("production")
public class ProductionController {

    @Resource
    WelderInformationService welderInformationService;

    @Resource
    EquipmentMaintenanceApplicationService equipmentMaintenanceApplicationService;

    @Resource
    DispatchInformationService dispatchInformationService;



    //=============================================================焊工信息管理==================================================

    /**
     * 分页获取焊工信息
     * @param page
     * @param size
     * @param welderName
     * @return
     */
    @GetMapping("welderInformation/{page}/{size}")
    public ResponseData queryWelderInformationPage(@PathVariable("page") int page, @PathVariable("size") int size, String welderName){

        ResponseData responseData=new ResponseData();
        Page<WelderInformation> welderInformationPage=welderInformationService.findByWelderNameLike(welderName,page,size);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(welderInformationPage);
        return responseData;
    }


    /**
     * 创建焊工信息
     * @param welderInformation
     * @return
     */
    @PostMapping("welderInformation")
    public ResponseData createWelderInformation(@RequestBody WelderInformation welderInformation){

        ResponseData responseData=new ResponseData();
        WelderInformation createWelderInformation=welderInformationService.create(welderInformation);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(createWelderInformation);
        return responseData;
    }


    /**
     * 删除焊工信息
     * @param welderId
     * @return
     */
    @DeleteMapping("welderInformation/{welderId}")
    public ResponseData deleteWelderInformation(@PathVariable ("welderId") Long welderId){

        ResponseData responseData=new ResponseData();
        welderInformationService.delete(welderId);
        responseData.setCode(200);
        responseData.setMsg("success");
        return responseData;
    }

    /**
     * 回显焊工信息
     * @param welderId
     * @return
     */
    @GetMapping("welderInformation/{welderId}")
    public ResponseData findWelderInformation(@PathVariable ("welderId") Long welderId){

        ResponseData responseData=new ResponseData();
        WelderInformation welderInformation=welderInformationService.findById(welderId);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(welderInformation);
        return responseData;
    }


    //=============================================================设备维修申请管理==================================================

    /**
     * 分页获取设备维修信息
     * @param page
     * @param size
     * @param deviceName
     * @return
     */
    @GetMapping("equipmentMaintenanceApplication/{page}/{size}")
    public ResponseData queryEquipmentMaintenanceApplicationPage(@PathVariable("page") int page, @PathVariable("size") int size, String deviceName){

        ResponseData responseData=new ResponseData();
        Page<EquipmentMaintenanceApplication> equipmentMaintenanceApplicationPage=equipmentMaintenanceApplicationService.findByDeviceNameLike(deviceName,page,size);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(equipmentMaintenanceApplicationPage);
        return responseData;
    }


    /**
     * 创建设备维修信息
     * @param equipmentMaintenanceApplication
     * @return
     */
    @PostMapping("equipmentMaintenanceApplication")
    public ResponseData createEquipmentMaintenanceApplication(@RequestBody EquipmentMaintenanceApplication equipmentMaintenanceApplication){

        ResponseData responseData=new ResponseData();
        EquipmentMaintenanceApplication createEquipmentMaintenanceApplication=equipmentMaintenanceApplicationService.create(equipmentMaintenanceApplication);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(createEquipmentMaintenanceApplication);
        return responseData;
    }


    /**
     * 删除设备维修申请信息
     * @param equipmentMaintenanceApplicationId
     * @return
     */
    @DeleteMapping("equipmentMaintenanceApplication/{equipmentMaintenanceApplicationId}")
    public ResponseData deleteEquipmentMaintenanceApplication(@PathVariable ("equipmentMaintenanceApplicationId") Long equipmentMaintenanceApplicationId){

        ResponseData responseData=new ResponseData();
        equipmentMaintenanceApplicationService.delete(equipmentMaintenanceApplicationId);
        responseData.setCode(200);
        responseData.setMsg("success");
        return responseData;
    }

    /**
     * 回显设备维修申请信息
     * @param equipmentMaintenanceApplicationId
     * @return
     */
    @GetMapping("equipmentMaintenanceApplication/{equipmentMaintenanceApplicationId}")
    public ResponseData findEquipmentMaintenanceApplication(@PathVariable ("equipmentMaintenanceApplicationId") Long equipmentMaintenanceApplicationId){

        ResponseData responseData=new ResponseData();
        EquipmentMaintenanceApplication equipmentMaintenanceApplication=equipmentMaintenanceApplicationService.findById(equipmentMaintenanceApplicationId);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(equipmentMaintenanceApplication);
        return responseData;
    }




    //=============================================================派工信息管理==================================================

    /**
     * 分页获取派工信息
     * @param page
     * @param size
     * @param productName
     * @return
     */
    @GetMapping("dispatchInformation/{page}/{size}")
    public ResponseData queryDispatchInformationPage(@PathVariable("page") int page, @PathVariable("size") int size, String productName){

        ResponseData responseData=new ResponseData();
        Page<DispatchInformation> dispatchInformationPage=dispatchInformationService.findByProductNameLike(productName,page,size);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(dispatchInformationPage);
        return responseData;
    }


    /**
     * 创建派工信息
     * @param dispatchInformation
     * @return
     */
    @PostMapping("dispatchInformation")
    public ResponseData createDispatchInformation(@RequestBody DispatchInformation dispatchInformation){

        ResponseData responseData=new ResponseData();
        DispatchInformation createDispatchInformation=dispatchInformationService.create(dispatchInformation);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(createDispatchInformation);
        return responseData;
    }


    /**
     * 删除派工信息
     * @param dispatchInformationId
     * @return
     */
    @DeleteMapping("dispatchInformation/{dispatchInformationId}")
    public ResponseData deleteDispatchInformation(@PathVariable ("dispatchInformationId") Long dispatchInformationId){

        ResponseData responseData=new ResponseData();
        dispatchInformationService.delete(dispatchInformationId);
        responseData.setCode(200);
        responseData.setMsg("success");
        return responseData;
    }

    /**
     * 回显派工信息
     * @param dispatchInformationId
     * @return
     */
    @GetMapping("dispatchInformation/{dispatchInformationId}")
    public ResponseData findDispatchInformation(@PathVariable ("dispatchInformationId") Long dispatchInformationId){

        ResponseData responseData=new ResponseData();
        DispatchInformation dispatchInformation=dispatchInformationService.findById(dispatchInformationId);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(dispatchInformation);
        return responseData;
    }




}
