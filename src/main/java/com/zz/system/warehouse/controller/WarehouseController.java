package com.zz.system.warehouse.controller;


import com.zz.system.quality.entity.MaterialInspection;
import com.zz.system.warehouse.entity.*;
import com.zz.system.warehouse.service.*;
import com.zz.vo.ResponseData;
import com.zz.vo.ResponseDataUtil;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * created by linlx on 2020/1/15
 */
@RestController
@RequestMapping("warehouse")
public class WarehouseController {


    @Resource
    ProductService productService;

    @Resource
    MaterialRequestionService materialRequestionService;

    @Resource
    DeliveryListService deliveryListService;

    @Resource
    EntryListService entryListService;

    @Resource
    MaterialService materialService;

    @Resource
    WarehouseService warehouseService;

    @Resource
    SupplierService supplierService;



    //===========================================出库单信息管理======================================


    /**
     * 分页获取出库单信息
     * @param page
     * @param size
     * @param productId
     * @return
     */
    @GetMapping("deliveryList/{page}/{size}")
    public ResponseData queryDeliveryListPage(@PathVariable("page") int page, @PathVariable("size") int size, String productId){

        ResponseData responseData=new ResponseData();
       Page<DeliveryList> deliveryListPage=deliveryListService.findByProductIdLike(productId,page,size);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(deliveryListPage);
        return responseData;
    }


    /**
     * 回显出库单信息
     * @param deliveryListId
     * @return
     */
    @GetMapping("deliveryList/{deliveryListId}")
    public ResponseData findDelivery(@PathVariable("deliveryListId") Long deliveryListId){

        ResponseData responseData=new ResponseData();
        DeliveryList deliveryList=deliveryListService.findById(deliveryListId);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(deliveryList);
        return responseData;
    }


    /**
     * 创建出库单
     * @param deliveryList
     * @return
     */
    @PostMapping("deliveryList")
    public ResponseData createDeliveryList(@RequestBody DeliveryList deliveryList){

        ResponseData responseData=new ResponseData();
        DeliveryList newDeliveryList=deliveryListService.create(deliveryList);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(newDeliveryList);

        return responseData;
    }


    /**
     * 删除出库单
     * @param deliveryListId
     * @return
     */
    @DeleteMapping("deliveryList/{deliveryListId}")
    public ResponseData deleteDeliveryList(@PathVariable("deliveryListId") Long deliveryListId){

        ResponseData responseData=new ResponseData();
       deliveryListService.delete(deliveryListId);
        responseData.setCode(200);
        responseData.setMsg("success");
        return responseData;
    }

    @PutMapping("deliveryList/update/{id}")
    public ResponseData updateDeliveryList(@PathVariable(value = "id") String id, @RequestBody DeliveryList deliveryList) {
    	deliveryList.setDeliveryListId(Long.parseLong(id));
    	Integer rs=deliveryListService.updateDeliveryList(deliveryList);
    	if(rs==1){
    		 return ResponseDataUtil.success("修改成功",rs);             
    	}else{           
    		return ResponseDataUtil.failure(500, "修改失败");
    	}
    }

    //===========================================入库单信息管理======================================

    /**
     * 分页获取入库单信息
     * @param page
     * @param size
     * @param materialId
     * @return
     */
    @GetMapping("entryList/{page}/{size}")
    public ResponseData queryEntryListPage(@PathVariable("page") int page, @PathVariable("size") int size, String materialId){

        ResponseData responseData=new ResponseData();
        Page<EntryList> entryListPage=entryListService.findByMaterialIdLike(materialId,page,size);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(entryListPage);
        return responseData;
    }

    @GetMapping("entryList/{entryListId}")
    public ResponseData findEntryList(@PathVariable("entryListId") Long entryListId){

        ResponseData responseData=new ResponseData();
        EntryList entryList=entryListService.findById(entryListId);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(entryList);
        return responseData;
    }

    /**
     * 创建入库单信息
     * @param entryList
     * @return
     */
    @PostMapping("entryList")
    public ResponseData createEntryList(@RequestBody EntryList entryList){

        ResponseData responseData=new ResponseData();
        EntryList createEntryList=entryListService.create(entryList);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(createEntryList);

        return responseData;
    }


    /**
     * 删除入库单信息
     * @param entryListId
     * @return
     */
    @DeleteMapping("entryList/{entryListId}")
    public ResponseData deleteEntryList(@PathVariable("entryListId") Long entryListId){

        ResponseData responseData=new ResponseData();
        entryListService.delete(entryListId);
        responseData.setCode(200);
        responseData.setMsg("success");
        return responseData;
    }



    //===========================================物料管理======================================

    /**
     * 分页获取物料信息
     * @param page
     * @param size
     * @param materialName
     * @return
     */
    @GetMapping("material/{page}/{size}")
    public ResponseData queryMaterialPage(@PathVariable("page") int page, @PathVariable("size") int size, String materialName){

        ResponseData responseData=new ResponseData();
        Page<Material> materialPage=materialService.findByMaterialNameLike(materialName,page,size);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(materialPage);
        return responseData;
    }


    /**
     * 回显物料信息
     * @param materialId
     * @return
     */
    @GetMapping("material/{materialId}")
    public ResponseData findMaterial(@PathVariable("materialId") Long materialId){

        ResponseData responseData=new ResponseData();
        Material material=materialService.findById(materialId);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(material);
        return responseData;
    }


    /**
     * 删除物料信息
     * @param materialId
     * @return
     */
    @DeleteMapping("material/{materialId}")
    public ResponseData deleteMaterial(@PathVariable("materialId") Long materialId){

        ResponseData responseData=new ResponseData();
        materialService.delete(materialId);
        responseData.setCode(200);
        responseData.setMsg("success");
        return responseData;
    }


    /**
     * 创建物料信息
     * @param material
     * @return
     */
    @PostMapping("material")
    public ResponseData createMaterial(@RequestBody Material material){

        ResponseData responseData=new ResponseData();
        Material createMaterial=materialService.create(material);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(createMaterial);

        return responseData;
    }

    //===========================================成品管理======================================


    /**
     *分页获取产品
     * @param page
     * @param size
     * @param productName
     * @return
     */
    @GetMapping("product/{page}/{size}")
    public ResponseData queryProductPage(@PathVariable("page") int page, @PathVariable("size") int size, String productName){

        ResponseData responseData=new ResponseData();
        Page<Product> productPage=productService.findByProductNameLike(productName,page,size);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(productPage);
        return responseData;
    }

    /**
     * 回显产品信息
     * @param productId
     * @return
     */
    @GetMapping("product/{productId}")
    public ResponseData findProduct(@PathVariable("productId") Long productId){

        ResponseData responseData=new ResponseData();
        Product product=productService.findById(productId);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(product);
        return responseData;
    }


    /**
     * 删除产品信息
     * @param productId
     * @return
     */
    @DeleteMapping("product/{productId}")
    public ResponseData deleteProduct(@PathVariable("productId") Long productId){

        ResponseData responseData=new ResponseData();
        productService.delete(productId);
        responseData.setCode(200);
        responseData.setMsg("success");
        return responseData;
    }

    /**
     * 创建产品
     * @param product
     * @return
     */
    @PostMapping("product")
    public ResponseData createProduct(@RequestBody Product product){

        ResponseData responseData=new ResponseData();
        Product createProduct=productService.create(product);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(createProduct);

        return responseData;
    }


    //===========================================领料单管理======================================

    /**
     * 领料单查询
     * @param page
     * @param size
     * @param materialId
     * @return
     */
    @GetMapping("materialRequestion/{page}/{size}")
    public ResponseData getMaterialRequestion(@PathVariable("page") int page, @PathVariable("size") int size, String materialId){

        ResponseData responseData=new ResponseData();
        Page<MaterialRequestion> materialRequestionPage=materialRequestionService.findByMaterialIdLike(materialId,page,size);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(materialRequestionPage);
        return responseData;
    }

    @GetMapping("materialRequestion/{materialReqeustionId}")
    public ResponseData findMaterialRequestion(@PathVariable("materialReqeustionId") Long materialReqeustionId){

        ResponseData responseData=new ResponseData();
        MaterialRequestion materialRequestion=materialRequestionService.findByMaterialRequestionId(materialReqeustionId);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(materialRequestion);
        return responseData;
    }


    /**
     * 删除领料单信息
     * @param materialRequestionId
     * @return
     */
    @DeleteMapping("materialRequestion/{materialRequestionId}")
    public ResponseData deleteMaterialRequestion(@PathVariable("materialRequestionId") Long materialRequestionId){

        ResponseData responseData=new ResponseData();
        materialRequestionService.delete(materialRequestionId);
        responseData.setCode(200);
        responseData.setMsg("success");
        return responseData;
    }

    @PostMapping("materialRequestion")
    public ResponseData createMaterialRequestion(@RequestBody MaterialRequestion materialRequestion){

        ResponseData responseData=new ResponseData();
        MaterialRequestion newMaterialRequestion=materialRequestionService.create(materialRequestion);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(newMaterialRequestion);

        return responseData;
    }



    //===========================================仓库管理======================================


    /**
     * 分页获取仓库信息
     * @param page
     * @param size
     * @param warehouseName
     * @return
     */
    @GetMapping("warehouse/{page}/{size}")
    public ResponseData getWarehousePage(@PathVariable("page") int page, @PathVariable("size") int size, String warehouseName){

        ResponseData responseData=new ResponseData();
        Page<Warehouse> warehousePage=warehouseService.findByWarehouseNameLike(warehouseName,page,size);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(warehousePage);
        return responseData;
    }


    /**
     * 回显仓库信息
     * @param warehouseId
     * @return
     */
    @GetMapping("warehouse/{warehouseId}")
    public ResponseData findWarehouse(@PathVariable("warehouseId") Long warehouseId){

        ResponseData responseData=new ResponseData();
        Warehouse warehouse=warehouseService.findById(warehouseId);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(warehouse);
        return responseData;
    }


    /**
     * 创建仓库信息
     * @param warehouse
     * @return
     */
    @PostMapping("warehouse")
    public ResponseData createWarehouse(@RequestBody Warehouse warehouse){

        ResponseData responseData=new ResponseData();
        Warehouse createWarehouse=warehouseService.create(warehouse);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(createWarehouse);

        return responseData;
    }

    /**
     * 删除仓库信息
     * @param warehouseId
     * @return
     */
    @DeleteMapping("warehouse/{warehouseId}")
    public ResponseData deleteWarehouse(@PathVariable("warehouseId") Long warehouseId){

        ResponseData responseData=new ResponseData();
       warehouseService.delete(warehouseId);
        responseData.setCode(200);
        responseData.setMsg("success");
        return responseData;
    }



    //===========================================供应商管理======================================


    /**
     * 分页获取供应商信息
     * @param page
     * @param size
     * @param supplierName
     * @return
     */
    @GetMapping("supplier/{page}/{size}")
    public ResponseData getSupplierPage(@PathVariable("page") int page, @PathVariable("size") int size, String supplierName){

        ResponseData responseData=new ResponseData();
        Page<Supplier> supplierPage=supplierService.findBySupplierNameLike(supplierName,page,size);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(supplierPage);
        return responseData;
    }


    /**
     * 回显供应商信息
     * @param supplierId
     * @return
     */
    @GetMapping("supplier/{supplierId}")
    public ResponseData findSupplier(@PathVariable("supplierId") Long supplierId){

        ResponseData responseData=new ResponseData();
        Supplier supplier=supplierService.findById(supplierId);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(supplier);
        return responseData;
    }


    /**
     * 创建供应商信息
     * @param supplier
     * @return
     */
    @PostMapping("supplier")
    public ResponseData createSupplier(@RequestBody Supplier supplier){

        ResponseData responseData=new ResponseData();
        Supplier createSupplier=supplierService.create(supplier);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(createSupplier);

        return responseData;
    }

    /**
     * 删除供应商信息
     * @param supplierId
     * @return
     */
    @DeleteMapping("supplier/{supplierId}")
    public ResponseData deleteSupplier(@PathVariable("supplierId") Long supplierId){

        ResponseData responseData=new ResponseData();
        supplierService.delete(supplierId);
        responseData.setCode(200);
        responseData.setMsg("success");
        return responseData;
    }


}
