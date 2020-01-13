
package com.zz.user.dto;

import lombok.Data;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年10月28日 下午8:37:47 
 */
@Data
public class BasicUserDto {
	/*
	 * 编号
	 */
	private String id;
	/*
	 * 密码
	 */
	private String pwd;
	/*
	 * 姓名
	 */
	private String name;
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
	 * 创建时间
	 */
	private String createTime;
}
