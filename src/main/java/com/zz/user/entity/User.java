
package com.zz.user.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.zz.user.entity.Role;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年10月28日 下午4:46:23 
 */
@Entity
@Getter
@Setter
@Proxy(lazy = false)
@Table(name="T_USER")
public class User implements Serializable{

	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 890537543921549507L;
	@Id
	@Column(length=100)
	/*
	 * 编号
	 */
	private String id;
	/*
	 * 姓名
	 */
	private String name;
	/*
	 * 密码
	 */
	private String pwd;
	/*
	 * 性别
	 */
	private String sex;
	/*
	 * 年龄
	 */
	private int age;
	/*
	 * 电话
	 */
	private String tel;
	/*
	 * 状态（0：冻结，1：正常）
	 */
	private int status;
	/*
	 * 所属部门
	 */
	private String partment;
	/*
	 * 职位
	 */
	private String position;
	/*
	 * 证书
	 */
	private String credential;
	/*
	 * 创建时间
	 */
	private String createTime;
	/*
	 * 角色
	 */
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "T_USER_ROLE", joinColumns = { @JoinColumn(name = "u_id") },
			inverseJoinColumns = {@JoinColumn(name = "r_id") })
	private Set<Role> roles = new HashSet<Role>();

	

}
