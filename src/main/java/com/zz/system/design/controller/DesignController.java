package com.zz.system.design.controller;

import com.zz.system.design.entity.Customer;
import com.zz.system.design.entity.ProductionInstruction;
import com.zz.system.design.entity.ProductionPlan;
import com.zz.system.design.entity.TaskInstruction;
import com.zz.system.design.service.CustomerService;
import com.zz.system.design.service.ProductionInstructionService;
import com.zz.system.design.service.ProductionPlanService;
import com.zz.system.design.service.TaskInstructionService;
import com.zz.vo.ResponseData;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 *
 */
@RestController
@RequestMapping("design")
public class DesignController {

    @Resource
    CustomerService customerService;

    @Resource
    ProductionPlanService productionPlanService;

    @Resource
    ProductionInstructionService productionInstructionService;

    @Resource
    TaskInstructionService taskInstructionService;



    //=================================================客户管理=================================================

    /**
     *分页获取客户信息
     * @param page
     * @param size
     * @param customerName
     * @return
     */
    @GetMapping("customer/{page}/{size}")
    public ResponseData queryCustomer(@PathVariable("page") int page,@PathVariable("size") int size,String customerName){

        ResponseData responseData=new ResponseData();
        Page<Customer> customerPage=customerService.findByCustomerNameLike(customerName,page,size);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(customerPage);
        return responseData;
    }


    /**
     * 添加客户信息
     * @param customer
     * @return
     */
    @PostMapping("customer")
    public ResponseData createCustomer( @RequestBody Customer customer){

        ResponseData responseData=new ResponseData();
        Customer createCustomer=customerService.create(customer);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(createCustomer);
        return responseData;
    }


    /**
     * 删除客户
     * @param customerId
     * @return
     */
    @DeleteMapping ("customer/{customerId}")
    public ResponseData deleteCustomer( @PathVariable("customerId") Long customerId){

        ResponseData responseData=new ResponseData();

        customerService.delete(customerId);
        responseData.setCode(200);
        responseData.setMsg("success");
        return responseData;
    }


    /**
     * 回显客户信息
     * @param customerId
     * @return
     */
    @GetMapping ("customer/{customerId}")
    public ResponseData getCustomer( @PathVariable("customerId") Long customerId){

        ResponseData responseData=new ResponseData();
        Customer customer=customerService.findById(customerId);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(customer);
        return responseData;
    }


    //=================================================生产计划管理=================================================

    /**
     *分页获取计划生产信息
     * @param page
     * @param size
     * @param productName
     * @return
     */
    @GetMapping("productionPlan/{page}/{size}")
    public ResponseData queryProductionPlanPage(@PathVariable("page") int page,@PathVariable("size") int size,String productName){

        ResponseData responseData=new ResponseData();

        Page<ProductionPlan> productionPlanPage=productionPlanService.findByProductNameLike(productName,page,size);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(productionPlanPage);
        return responseData;
    }


    /**
     * 添加计划生产信息
     * @param productionPlan
     * @return
     */
    @PostMapping("productionPlan")
    public ResponseData createProductionPlan( @RequestBody ProductionPlan productionPlan){

        ResponseData responseData=new ResponseData();
        ProductionPlan createProductionPlan=productionPlanService.create(productionPlan);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(createProductionPlan);
        return responseData;
    }


    /**
     * 删除计划生产
     * @param productionPlanId
     * @return
     */
    @DeleteMapping ("productionPlan/{productionPlanId}")
    public ResponseData deleteProductionPlan( @PathVariable("productionPlanId") Long productionPlanId){

        ResponseData responseData=new ResponseData();
        productionPlanService.delete(productionPlanId);
        responseData.setCode(200);
        responseData.setMsg("success");
        return responseData;
    }


    /**
     * 回显计划生产信息
     * @param productionPlanId
     * @return
     */
    @GetMapping ("productionPlan/{productionPlanId}")
    public ResponseData getProductionPlan( @PathVariable("productionPlanId") Long productionPlanId){

        ResponseData responseData=new ResponseData();
        ProductionPlan productionPlan=productionPlanService.findById(productionPlanId);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(productionPlan);
        return responseData;
    }




    //=================================================生产指导书管理=================================================

    /**
     *分页获取生产指导书
     * @param page
     * @param size
     * @param productionOrder
     * @return
     */
    @GetMapping("productionInstruction/{page}/{size}")
    public ResponseData queryProductionInstructionPage(@PathVariable("page") int page,@PathVariable("size") int size,String productionOrder){

        ResponseData responseData=new ResponseData();
        Page<ProductionInstruction> productionInstructionPage=productionInstructionService.findByProductionOrderLike(productionOrder,page,size);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(productionInstructionPage);
        return responseData;
    }


    /**
     * 添加生产指导书
     * @param productionInstruction
     * @return
     */
    @PostMapping("productionInstruction")
    public ResponseData createProductionInstruction( @RequestBody ProductionInstruction productionInstruction){

        ResponseData responseData=new ResponseData();
        ProductionInstruction createProductionInstruction=productionInstructionService.create(productionInstruction);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(createProductionInstruction);
        return responseData;
    }


    /**
     * 删除生产指导书
     * @param productionInstructionId
     * @return
     */
    @DeleteMapping ("productionInstruction/{productionInstructionId}")
    public ResponseData deleteProductionInstruction( @PathVariable("productionInstructionId") Long productionInstructionId){

        ResponseData responseData=new ResponseData();
        productionInstructionService.delete(productionInstructionId);
        responseData.setCode(200);
        responseData.setMsg("success");
        return responseData;
    }


    /**
     * 回显生产指导书
     * @param productionInstructionId
     * @return
     */
    @GetMapping ("productionInstruction/{productionInstructionId}")
    public ResponseData getProductionInstruction( @PathVariable("productionInstructionId") Long productionInstructionId){

        ResponseData responseData=new ResponseData();
        ProductionInstruction productionInstruction=productionInstructionService.findById(productionInstructionId);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(productionInstruction);
        return responseData;
    }



    //=================================================任务指导管理=================================================

    /**
     *分页获取任务指导
     * @param page
     * @param size
     * @param productionOrder
     * @return
     */
    @GetMapping("taskInstruction/{page}/{size}")
    public ResponseData queryTaskInstructionPage(@PathVariable("page") int page,@PathVariable("size") int size,String productionOrder){

        ResponseData responseData=new ResponseData();
        Page<TaskInstruction> taskInstructionPage=taskInstructionService.findByProductionOrderLike(productionOrder,page,size);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(taskInstructionPage);
        return responseData;
    }


    /**
     * 添加任务指导
     * @param taskInstruction
     * @return
     */
    @PostMapping("taskInstruction")
    public ResponseData createTaskInstruction( @RequestBody TaskInstruction taskInstruction){

        ResponseData responseData=new ResponseData();
        TaskInstruction createTaskInstruction=taskInstructionService.create(taskInstruction);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(createTaskInstruction);
        return responseData;
    }


    /**
     * 删除任务指导
     * @param taskInstructionId
     * @return
     */
    @DeleteMapping ("taskInstruction/{taskInstructionId}")
    public ResponseData deleteTaskInstruction( @PathVariable("taskInstructionId") Long taskInstructionId){

        ResponseData responseData=new ResponseData();
       taskInstructionService.delete(taskInstructionId);
        responseData.setCode(200);
        responseData.setMsg("success");
        return responseData;
    }


    /**
     * 回显任务指导
     * @param taskInstructionId
     * @return
     */
    @GetMapping ("taskInstruction/{taskInstructionId}")
    public ResponseData getTaskInstruction( @PathVariable("taskInstructionId") Long taskInstructionId){

        ResponseData responseData=new ResponseData();
        TaskInstruction taskInstruction=taskInstructionService.findById(taskInstructionId);
        responseData.setCode(200);
        responseData.setMsg("success");
        responseData.setData(taskInstruction);
        return responseData;
    }




}
