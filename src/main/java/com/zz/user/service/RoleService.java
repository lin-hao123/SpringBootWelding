
package com.zz.user.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zz.user.entity.Role;
import com.zz.user.repository.RoleRepository;
import com.zz.util.KeyUtil;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年11月4日 上午10:48:31 
 */
@Service
public class RoleService {
	@Resource
	RoleRepository roleRepository;
	/**
	 * @Description: 新增角色
	 * @param Role 对象
	 * @return Role 对象
	 */
	public Role save(Role role){
		role.setId(KeyUtil.genUniqueKey());
		return roleRepository.save(role);
	}
	/**
	 * @Description: 修改角色信息
	 * @param Role 对象
	 * @return int 修改条数
	 */
	@Transactional(rollbackFor=Exception.class)
	public Integer update(Role role){
		return roleRepository.update(role);
	}
	/**
	 * Sort.Direction.DESC 表示从大到小
	 * Sort.Direction.ASC 表示 从小到大
	 * @Description: 查询所有角色并排序分页
	 * @param page 第几页
	 * @param size 该页显示数据条数
	 * @return User 用户所有信息
	 */
	public Page<Role> showall(int page,int size){
		Sort sort=new Sort(Sort.Direction.DESC,"id");
		Pageable pageable= PageRequest.of(page,size,sort);
		return roleRepository.findAll(pageable);
	}
	/**
	 * @Description 根据角色名查询角色
	 * @param name 角色名
	 * @return Role 角色对象
	 */
	public Role findByDetail(String detail){
		return roleRepository.findByDetail(detail);
	}
	/**
	 * @Description 根据角色id查询角色
	 * @param id 角色id
	 * @return Role 角色对象
	 */
	public Role findById(String id){
		return roleRepository.findById(id).get();
	}
	/**
	 * @Description: 删除角色
	 * @param id 角色id
	 * @return void
	 */
	public void remove(String id){
		roleRepository.deleteById(id);
	}
	/**
	 * @Description 增加角色权限中间表
	 * @param r_id 角色id
	 * @param p_id 权限id
	 * @return Integer 增加条数
	 */
	@Transactional(rollbackFor=Exception.class)
	public Integer add(String r_id,String p_id){
		return roleRepository.add(r_id, p_id);
	}
	/**
	 * @Description 根据角色id删除角色权限中间表
	 * @param r_id 角色id
	 * @return Integer 删除条数
	 */
	@Transactional(rollbackFor=Exception.class)
	public Integer delete(String r_id){
		return roleRepository.delete(r_id);
	}
}
