
package com.zz.user.service;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zz.user.entity.Permission;
import com.zz.user.entity.Role;
import com.zz.user.repository.PermissionRepository;
import com.zz.util.KeyUtil;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年11月4日 下午8:35:39 
 */
@Service
public class PermissionService {
	@Resource
	PermissionRepository permissionRepository;
	
	/**
	 * @Description: 新增权限
	 * @param Permission 对象
	 * @return Permission 对象
	 */
	public Permission save(Permission permission){
		permission.setId(KeyUtil.genUniqueKey());
		return permissionRepository.save(permission);
	}
	/**
	 * @Description: 修改权限信息
	 * @param Permission 对象
	 * @return Integer 修改条数
	 */
	@Transactional(rollbackFor=Exception.class)
	public Integer update(Permission permission){
		return permissionRepository.update(permission);
	}
	/**
	 * Sort.Direction.DESC 表示从大到小
	 * Sort.Direction.ASC 表示 从小到大
	 * @Description: 查询所有权限并排序分页
	 * @param page 第几页
	 * @param size 该页显示数据条数
	 * @return Page<Permission> 权限所有信息
	 */
	public Page<Permission> showall(int page,int size){
		Sort sort=new Sort(Sort.Direction.DESC,"id");
		Pageable pageable= PageRequest.of(page,size,sort);
		return permissionRepository.findAll(pageable);
	}
	/**
	 * @Description 根据权限名查询
	 * @param name 权限名
	 * @return Permission 权限对象
	 */
	public Permission findByName(String name){
		return permissionRepository.findByName(name);
	}
	/**
	 * @Description 根据权限id查询
	 * @param id 权限id
	 * @return Permission 权限对象
	 */
	public Permission findById(String id){
		return permissionRepository.findById(id).get();
	}
	
	/**
	 * @Description: 根据id删除权限
	 * @param id 权限id
	 * @return void
	 */
	public void remove(String id){
		permissionRepository.deleteById(id);
	}
}
