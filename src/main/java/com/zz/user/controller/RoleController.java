
package com.zz.user.controller;

import java.util.List;

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
import com.zz.user.dto.PermissionListDto;
import com.zz.user.dto.RoleListDto;
import com.zz.user.entity.Role;
import com.zz.user.entity.User;
import com.zz.user.form.RoleForm;
import com.zz.user.service.RoleService;
import com.zz.util.FormConvertDto;
import com.zz.vo.ResponseData;
import com.zz.vo.ResponseDataUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年11月4日 上午10:52:50 
 */
@RestController
@RequestMapping("role")
public class RoleController {
	@Resource 
	RoleService roleService;
	
	@ApiOperation(value = "创建角色信息") 
	@ApiImplicitParam(name = "roleForm", value = "角色实体", required = true, dataType = "RoleForm")
	@PostMapping("roleForm")
	public ResponseData save(@Valid @RequestBody RoleForm roleForm,BindingResult bindingResult ){
		if(bindingResult.hasErrors()){
			throw new DataValidationException(bindingResult.getFieldError().getDefaultMessage());
		}
		Role role=new Role();
		role.setDetail(roleForm.getDetail());
		Role rs1=roleService.save(role);
		boolean flag1=false;
		boolean flag2=false;
		if(rs1!=null){
			flag1=true;
		}
		PermissionListDto permissionListDto=FormConvertDto.permissionListDtoConvert(roleForm);
		int sum=0;    	
    	for(int i=0;i<permissionListDto.getPermissionList().size();i++){
    		Integer rs2=roleService.add(rs1.getId(), permissionListDto.getPermissionList().get(i).getId());
    		sum=sum+rs2;
    	}
    	if(permissionListDto.getPermissionList().size()==sum){
    		flag2=true;
    	}
    	if(flag1&&flag2){
    		return ResponseDataUtil.success("创建成功");
        }else{
   		    return ResponseDataUtil.failure(500, "创建失败");
   	    }   
	}
	
    @ApiOperation(value = "更新角色", notes = "根据用角色id更新角色")
    @ApiImplicitParam(name = "roleForm", value = "角色form", required = true, dataType = "RoleForm")
    @PutMapping("update")
    public ResponseData updateRole(@Valid @RequestBody RoleForm roleForm,BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){ 
    		throw new DataValidationException("验证错误");
//			throw new DataValidationException(bindingResult.getFieldError().getDefaultMessage());
		}
    	Role role=new Role();
    	role.setId(roleForm.getId());
    	role.setDetail(roleForm.getDetail());
    	boolean flag1=false;
    	boolean flag2=false;
    	Integer rs=roleService.update(role);
    	if(rs==1){
    		flag1=true;
    	}
    	roleService.delete(roleForm.getId());
    	PermissionListDto permissionListDto=FormConvertDto.permissionListDtoConvert(roleForm);
    	int sum=0;    	
    	for(int i=0;i<permissionListDto.getPermissionList().size();i++){
    		Integer rs2=roleService.add(roleForm.getId(), permissionListDto.getPermissionList().get(i).getId());
    		sum=sum+rs2;
    	}
    	if(permissionListDto.getPermissionList().size()==sum){
    		flag2=true;
    	}
    	if(flag1&&flag2){
    		 return ResponseDataUtil.success("修改成功");
    	}else{
    		return ResponseDataUtil.failure(500, "修改失败");
    	}
    }
    
	@ApiOperation(value = "查看角色", notes = "查看所有角色信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", value = "开始页0开始", required = true, dataType = "int", paramType = "path"),
		@ApiImplicitParam(name = "size", value = "每页显示数量", required = true, dataType = "int", paramType = "path")
		})
	@GetMapping("showall/{page}/{size}")
	public Page<Role> showAll(@PathVariable("page") int page,@PathVariable("size") int size){
		return roleService.showall(page, size);
	}
	
	@ApiOperation(value = "查看角色", notes = "根据角色名查看角色信息")
	@ApiImplicitParam(name = "detail", value = "角色名", required = true, dataType = "String", paramType = "path")
	@GetMapping("showByDetail/{detail}")
	public Role showAll(@PathVariable("detail") String detail){
		return roleService.findByDetail(detail);
	}
	
	@ApiOperation(value = "查看角色", notes = "根据角色id查看角色信息")
	@ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "String", paramType = "path")
	@GetMapping("findById/{id}")
	public Role findById(@PathVariable("id") String id){
		return roleService.findById(id);
	}
	
	@ApiOperation(value = "删除角色", notes = "根据id删除角色")
	@ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "String",paramType="path")
	@DeleteMapping("remove/{id}")
	public void remove(@PathVariable("id") String id){
		roleService.remove(id);
	}
	
	@ApiOperation(value = "增加角色权限中间表") 
	@ApiImplicitParams({
		@ApiImplicitParam(name = "rid", value = "角色id", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "pid", value = "权限id", required = true, dataType = "String", paramType = "path")
		})
	@PostMapping("addrolepermission/{rid}/{pid}")
	public ResponseData save(@PathVariable("rid") String r_id,@PathVariable("pid") String p_id){
	    return ResponseDataUtil.success("添加成功", roleService.add(r_id, p_id));
	}
	
	@ApiOperation(value = "删除角色权限中间表", notes = "根据角色id删除")
	@ApiImplicitParam(name = "rid", value = "角色id", required = true, dataType = "String", paramType = "path")
	@DeleteMapping("delete/{rid}")
	public Integer delete(@PathVariable("rid") String r_id){
		return roleService.delete(r_id);
	}
}
