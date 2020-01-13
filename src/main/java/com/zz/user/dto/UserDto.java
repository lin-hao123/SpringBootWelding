
package com.zz.user.dto;

import lombok.Data;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年11月3日 下午7:20:43 
 */
@Data
public class UserDto {
	/*
	 * 用户编号
	 */
	private String id;
	/*
	 * 用户姓名
	 */
	private String name;
	/*
	 * 用户所属部门
	 */
	private String partment;
	/*
	 * 用户密码
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
}
