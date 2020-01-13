
package com.zz.util;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zz.exception.DataValidationException;
import com.zz.user.dto.BasicUserDto;
import com.zz.user.dto.PermissionListDto;
import com.zz.user.dto.RoleListDto;
import com.zz.user.dto.UserDto;
import com.zz.user.entity.Permission;
import com.zz.user.entity.Role;
import com.zz.user.form.BasicUserForm;
import com.zz.user.form.CreateUserForm;
import com.zz.user.form.RoleForm;
import com.zz.user.form.UpdateUserForm;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年11月11日 下午7:21:24 
 */
@Slf4j
public class FormConvertDto {
	public static UserDto userDtoConvert(UpdateUserForm updateUserForm){
		 UserDto userDto=new UserDto();
		 userDto.setId(updateUserForm.getId());
		 userDto.setName(updateUserForm.getName());
		 userDto.setPartment(updateUserForm.getPartment());
		 userDto.setAge(Integer.parseInt(updateUserForm.getAge()));
		 userDto.setSex(updateUserForm.getSex());
		 userDto.setStatus(Integer.parseInt(updateUserForm.getStatus()));
		 userDto.setTel(updateUserForm.getTel());
		 return userDto;
	}
	
	public static RoleListDto roleListDtoConvert(UpdateUserForm updateUserForm){
		Gson gson=new Gson();
		RoleListDto roleListDto=new RoleListDto();		
		List<Role> roleList=new ArrayList<>();
		 try {
			 roleList = gson.fromJson(updateUserForm.getRoleList(),
	                    new TypeToken<List<Role>>() {
	                    }.getType());
	        } catch (Exception e) {
	            log.error("【对象转换】错误, string={}", updateUserForm.getRoleList());
	            throw new DataValidationException("对象转换");
	        }
		 roleListDto.setRoleList(roleList);
		return roleListDto;
	}
	
	public static PermissionListDto permissionListDtoConvert(RoleForm roleForm){
		Gson gson=new Gson();
		PermissionListDto permissionListDto=new PermissionListDto();
		List<Permission> permissionList=new ArrayList<>();
		 try {
			 permissionList = gson.fromJson(roleForm.getPermissionList(),
	                    new TypeToken<List<Permission>>() {
	                    }.getType());
	        } catch (Exception e) {
	            log.error("【对象转换】错误, string={}", roleForm.getPermissionList());
	            throw new DataValidationException("对象转换");
	        }
		 permissionListDto.setPermissionList(permissionList);
		return permissionListDto;
	}
	
	public static UserDto createUserDtoConvert(CreateUserForm createUserForm){
		UserDto userDto=new UserDto();
		userDto.setId(KeyUtil.genUniqueKey());
		userDto.setName(createUserForm.getName());
		userDto.setPartment(createUserForm.getPartment());
		userDto.setAge(Integer.parseInt(createUserForm.getAge()));
		userDto.setSex(createUserForm.getSex());
		userDto.setTel(createUserForm.getTel());
		userDto.setStatus(Integer.parseInt(createUserForm.getStatus()));
		String enpwd=MD5Utils.encrypt(createUserForm.getName(), createUserForm.getPwd());
		userDto.setPwd(enpwd);
		return userDto;
	}
	
	public static RoleListDto createRoleListDtoConvert(CreateUserForm createUserForm){
		Gson gson=new Gson();
		RoleListDto roleListDto=new RoleListDto();		
		List<Role> roleList=new ArrayList<>();
		 try {
			 roleList = gson.fromJson(createUserForm.getRoleList(),
	                    new TypeToken<List<Role>>() {
	                    }.getType());
	        } catch (Exception e) {
	            log.error("【对象转换】错误, string={}", createUserForm.getRoleList());
	            throw new DataValidationException("对象转换");
	        }
		 roleListDto.setRoleList(roleList);
		return roleListDto;
	}
	
	public static BasicUserDto BasicUserConvert(BasicUserForm basicUserForm){
		BasicUserDto basicUserDto=new BasicUserDto();
		basicUserDto.setName(basicUserForm.getName());
		basicUserDto.setTel(basicUserForm.getTel());
		basicUserDto.setPwd(basicUserForm.getPwd());
		basicUserDto.setAge(Integer.parseInt(basicUserForm.getAge()));
		basicUserDto.setSex(basicUserForm.getSex());
		return basicUserDto;
		
	}
	 
}
