package com.zz.system.quality.controller;


import com.zz.system.design.entity.Customer;
import com.zz.system.device.entity.DeviceRepair;
import com.zz.system.production.entity.DispatchInformation;
import com.zz.system.quality.entity.Announcement;
import com.zz.system.quality.entity.MaterialInspection;
import com.zz.system.quality.entity.UnqualifiedProduct;
import com.zz.system.quality.service.AnnouncementService;
import com.zz.system.quality.service.MaterialInspectionService;
import com.zz.system.quality.service.UnqualifiedProductService;
import com.zz.vo.ResponseData;
import com.zz.vo.ResponseDataUtil;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 质检部门
 * created by linlx on 2020/2/24
 */
@RestController
@RequestMapping("quality")
public class QualityController {

    @Resource
    MaterialInspectionService materialInspectionService;

    @Resource
    UnqualifiedProductService unqualifiedProductService;

    @Resource
    AnnouncementService announcementService;


    //=================================================物料检测管理=================================================


    /**
     * 分页获取物料检测
     * @param page
     * @param size
     * @param inspectionStaff
     * @return
     */
    @GetMapping("materialInspection/{page}/{size}")
    public ResponseData queryMaterialInspection(@PathVariable("page") int page, @PathVariable("size") int size, String inspectionStaff){
        ResponseData responseData=new ResponseData();
        Page<MaterialInspection> materialInspectionPage=materialInspectionService.findByInspectionStaffLike(inspectionStaff,page,size);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(materialInspectionPage);
        return responseData;
    }


    /**
     * 添加物料检测信息
     * @param materialInspection
     * @return
     */
    @PostMapping("materialInspection")
    public ResponseData createMaterialInspection( @RequestBody MaterialInspection materialInspection){

        ResponseData responseData=new ResponseData();
        MaterialInspection createMaterialInspection=materialInspectionService.create(materialInspection);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(createMaterialInspection);
        return responseData;
    }

    /**
     * 删除物料检测信息
     * @param materialInspectionId
     * @return
     */
    @DeleteMapping ("materialInspection/{materialInspectionId}")
    public ResponseData deleteMaterialInspection( @PathVariable("materialInspectionId") Long materialInspectionId){

        ResponseData responseData=new ResponseData();

        materialInspectionService.delete(materialInspectionId);
        responseData.setCode(200);
        responseData.setMsg("success");
        return responseData;
    }

    /**
     * 回显物料检测信息
     * @param materialInspectionId
     * @return
     */
    @GetMapping ("materialInspection/{materialInspectionId}")
    public ResponseData getMaterialInspection( @PathVariable("materialInspectionId") Long materialInspectionId){

        ResponseData responseData=new ResponseData();
        MaterialInspection materialInspection=materialInspectionService.findById(materialInspectionId);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(materialInspection);
        return responseData;
    }

    
    @PutMapping("materialInspection/update/{id}")
    public ResponseData updateMaterialInspection(@PathVariable(value = "id") String id, @RequestBody MaterialInspection materialInspection) {
    	materialInspection.setMaterialInspectionId(Long.parseLong(id));
    	Integer rs=materialInspectionService.updateMaterialInspection(materialInspection);
    	if(rs==1){
    		 return ResponseDataUtil.success("修改成功",rs);             
    	}else{           
    		return ResponseDataUtil.failure(500, "修改失败");
    	}
    }

    //=================================================不合格产品处理=================================================


    /**
     * 分页获取不合格产品信息
     * @param page
     * @param size
     * @param weldingProducts
     * @return
     */
    @GetMapping("unqualifiedProduct/{page}/{size}")
    public ResponseData queryUnqualifiedProduct(@PathVariable("page") int page, @PathVariable("size") int size, String weldingProducts){
        ResponseData responseData=new ResponseData();
        Page<UnqualifiedProduct> unqualifiedProductPage=unqualifiedProductService.findByWeldingProductsLike(weldingProducts, page, size);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(unqualifiedProductPage);
        return responseData;
    }


    /**
     * 添加不合格产品信息
     * @param unqualifiedProduct
     * @return
     */
    @PostMapping("unqualifiedProduct")
    public ResponseData createUnqualifiedProduct( @RequestBody UnqualifiedProduct unqualifiedProduct){

        ResponseData responseData=new ResponseData();
        UnqualifiedProduct createUnqualifiedProduct=unqualifiedProductService.create(unqualifiedProduct);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(createUnqualifiedProduct);
        return responseData;
    }

    /**
     * 删除不合格吃产品信息
     * @param unqualifiedProductId
     * @return
     */
    @DeleteMapping ("unqualifiedProduct/{unqualifiedProductId}")
    public ResponseData deleteUnqualifiedProduct( @PathVariable("unqualifiedProductId") Long unqualifiedProductId){

        ResponseData responseData=new ResponseData();
        unqualifiedProductService.delete(unqualifiedProductId);
        responseData.setCode(200);
        responseData.setMsg("success");
        return responseData;
    }

    /**
     * 回显不合格产品信息
     * @param unqualifiedProductId
     * @return
     */
    @GetMapping ("unqualifiedProduct/{unqualifiedProductId}")
    public ResponseData getUnqualifiedProduct( @PathVariable("unqualifiedProductId") Long unqualifiedProductId){

        ResponseData responseData=new ResponseData();
        UnqualifiedProduct unqualifiedProduct=unqualifiedProductService.findById(unqualifiedProductId);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(unqualifiedProduct);
        return responseData;
    }
    
    @PutMapping("unqualifiedProduct/update/{id}")
    public ResponseData updateUnqualifiedProduct(@PathVariable(value = "id") String id, @RequestBody UnqualifiedProduct unqualifiedProduct) {
    	unqualifiedProduct.setUnqualifiedProductId(Long.parseLong(id));
    	Integer rs=unqualifiedProductService.updateUnqualifiedProduct(unqualifiedProduct);
    	if(rs==1){
    		 return ResponseDataUtil.success("修改成功",rs);             
    	}else{           
    		return ResponseDataUtil.failure(500, "修改失败");
    	}
    }

    //=================================================公告信息管理=================================================


    /**
     * 分页显示公告信息
     * @param page
     * @param size
     * @param announcementTitle
     * @return
     */
    @GetMapping("announcement/{page}/{size}")
    public ResponseData queryAnnouncement(@PathVariable("page") int page, @PathVariable("size") int size, String announcementTitle){
        ResponseData responseData=new ResponseData();
        Page<Announcement> announcementPage=announcementService.findByAnnouncementTitleLike(announcementTitle,page,size);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(announcementPage);
        return responseData;
    }


    /**
     * 添加公告信息
     * @param announcement
     * @return
     */
    @PostMapping("announcement")
    public ResponseData createAnnouncement( @RequestBody Announcement announcement){

        ResponseData responseData=new ResponseData();
        Announcement createAnnouncement=announcementService.create(announcement);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(createAnnouncement);
        return responseData;
    }

    /**
     * 删除公告信息
     * @param announcementId
     * @return
     */
    @DeleteMapping ("announcement/{announcementId}")
    public ResponseData deleteAnnouncement( @PathVariable("announcementId") Long announcementId){

        ResponseData responseData=new ResponseData();
        announcementService.delete(announcementId);
        responseData.setCode(200);
        responseData.setMsg("success");
        return responseData;
    }

    /**
     * 回显公告信息
     * @param announcementId
     * @return
     */
    @GetMapping ("announcement/{announcementId}")
    public ResponseData getAnnouncement( @PathVariable("announcementId") Long announcementId){

        ResponseData responseData=new ResponseData();
        Announcement announcement=announcementService.findById(announcementId);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(announcement);
        return responseData;
    }


    @PutMapping("announcement/update/{id}")
    public ResponseData updateAnnouncement(@PathVariable(value = "id") String id, @RequestBody Announcement announcement) {
        announcement.setAnnouncementId(Long.parseLong(id));
        Integer rs= announcementService.updateAnnouncement(announcement);
        if(rs==1){
            return ResponseDataUtil.success("修改成功",rs);
        }else{
            return ResponseDataUtil.failure(500, "修改失败");
        }
    }



}
