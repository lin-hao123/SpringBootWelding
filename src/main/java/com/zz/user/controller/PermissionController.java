
package com.zz.user.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zz.exception.DataValidationException;
import com.zz.user.entity.Permission;
import com.zz.user.form.PermissionForm;
import com.zz.user.service.PermissionService;
import com.zz.vo.ResponseData;
import com.zz.vo.ResponseDataUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年11月4日 下午8:46:19 
 */
@RestController
@RequestMapping("permission")
public class PermissionController {
	@Resource 
	PermissionService permissionService; 
	
	@ApiOperation(value = "创建权限信息") 
	@ApiImplicitParam(name = "permissionForm", value = "权限实体", required = true, dataType = "PermissionForm")
	@PostMapping("add")
	public ResponseData save(@Valid @RequestBody PermissionForm permissionForm,BindingResult bindingResult ){
		if(bindingResult.hasErrors()){
			throw new DataValidationException(bindingResult.getFieldError().getDefaultMessage());
		}
		Permission permission=new Permission();
		BeanUtils.copyProperties(permissionForm, permission);
	    return ResponseDataUtil.success("添加成功", permissionService.save(permission));
	}
	
    @ApiOperation(value = "更新权限", notes = "根据用权限id更新权限")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "权限id", required = true, dataType = "String", paramType = "path"),
        @ApiImplicitParam(name = "permissionForm", value = "权限form", required = true, dataType = "PermissionForm") })
    @PutMapping("update/{id}")
    public ResponseData updatePermission(@PathVariable(value = "id") String id, @Valid @RequestBody PermissionForm permissionForm,BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){ 
			throw new DataValidationException(bindingResult.getFieldError().getDefaultMessage());
		}
    	Permission permission=new Permission();
    	BeanUtils.copyProperties(permissionForm, permission);
    	permission.setId(id);
    	Integer rs=permissionService.update(permission);
    	if(rs==1){
    		 return ResponseDataUtil.success("修改成功",rs);
    	}else{
    		return ResponseDataUtil.failure(500, "修改失败");
    	}
    }
    
	@ApiOperation(value = "查看权限", notes = "查看所有权限信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", value = "开始页0开始", required = true, dataType = "int", paramType = "path"),
		@ApiImplicitParam(name = "size", value = "每页显示数量", required = true, dataType = "int", paramType = "path")
		})
	@GetMapping("showall/{page}/{size}")
	public Page<Permission> showAll(@PathVariable("page") int page,@PathVariable("size") int size){
		return permissionService.showall(page, size);
	}
	
	@ApiOperation(value = "查看权限", notes = "根据权限名查看权限信息")
	@ApiImplicitParam(name = "name", value = "权限名", required = true, dataType = "String", paramType = "path")
	@GetMapping("showByName/{name}")
	public Permission showAll(@PathVariable("name") String name){
		return permissionService.findByName(name);
	}
	
	@ApiOperation(value = "根据id查看权限", notes = "根据id查看权限")
	@ApiImplicitParam(name = "id", value = "权限id", required = true, dataType = "String", paramType = "path")
	@GetMapping("findById/{id}")
	public Permission findById(@PathVariable("id") String id){
		return permissionService.findById(id);
	}
	@ApiOperation(value = "删除权限", notes = "根据id删除权限")
	@ApiImplicitParam(name = "id", value = "权限id", required = true, dataType = "String",paramType="path")
	@DeleteMapping("remove/{id}")
	public void add(@PathVariable("id") String id){
		permissionService.remove(id);
	}
}
