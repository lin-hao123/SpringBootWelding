
package com.zz.user.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zz.user.dto.BasicUserDto;
import com.zz.user.entity.Role;
import com.zz.user.entity.User;
import com.zz.user.repository.UserRepository;
import com.zz.util.KeyUtil;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年10月28日 下午7:45:47 
 */
@Service
public class UserService {
	@Resource
	UserRepository userRepository;
	private UserRepository roleRepository;
	/**
	 * @Description: 注册用户基本信息
	 * @param User 对象
	 * @return BasicUserDto 基本信息
	 */
	public User save(User user){
		user.setId(KeyUtil.genUniqueKey());
		user.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		user.setStatus(1);
		User user1= userRepository.save(user);
		return user1;
	}
	/**
	 * 
	 * @Description:新建用户
	 * @Author lh
	 * @DateTime 2019年11月18日 下午9:11:44
	 * @param user
	 * @return User
	 */
	public User createUser(User user){
		user.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return userRepository.save(user);
	}
	/**
	 * @Description: 根据用户id查找用户
	 * @param id 用户id
	 * @return User 对象
	 */
	public User findById(String id){
		return userRepository.findById(id).get();
	}
	/**
	 * @Description: 根据名字查找用户是否存在
	 * @param name 名字
	 * @return User 对象
	 */
	public User findByName(String name){
		return userRepository.findByName(name);
	}
	/**
	 * @Description: 根据用户名和密码登陆
	 * @param name 名字
	 * @param pwd 密码
	 * @return User 对象
	 */
	public User findByNameAndPwd(String name,String pwd){
		return userRepository.findByNameAndPwd(name, pwd);
	}
	
	/**
	 * Sort.Direction.DESC 表示从大到小
	 * Sort.Direction.ASC 表示 从小到大
	 * @Description: 查询所有用户所有信息
	 * @param page 第几页
	 * @param size 该页显示数据条数
	 * @return User 用户所有信息
	 */
	public Page<User> showall(int page,int size){
		Sort sort=new Sort(Sort.Direction.DESC,"createTime");
		Pageable pageable= PageRequest.of(page,size,sort);
		return userRepository.findAll(pageable);
	}
	
	/**
	 * @Description: 根据id删除用户
	 * @return void
	 */
	public void deleteById(String id){
		userRepository.deleteById(id);
	}
	/**
	 * @Description: 修改用户基本信息
	 * @param User 对象
	 * @return Integer 修改条数
	 */
	@Transactional(rollbackFor=Exception.class)
	public Integer update(User user){
		return userRepository.update(user);
	}
	/**
	 * @Description 增加用户角色中间表
	 * @param u_id 用户id
	 * @param r_id 角色id
	 * @return Integer 增加条数
	 */
	@Transactional(rollbackFor=Exception.class)
	public Integer add(String u_id,String r_id){
		return userRepository.add(u_id, r_id);
	}
	/**
	 * @Description 根据用户id删除用户角色中间表
	 * @param u_id 用户id
	 * @return Integer 删除条数
	 */
	@Transactional(rollbackFor=Exception.class)
	public Integer delete(String u_id){
		return userRepository.delete(u_id);
	}
	/**
	 * @Description 根据用户id查询用户角色中间表
	 * @param u_id 用户id
	 * @return Object
	 */
	public List<User> checkId(String u_id){
		return userRepository.checkId(u_id);
	}
}
