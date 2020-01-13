
package com.zz.user.form;

import lombok.Data;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年11月18日 下午8:28:24 
 */
@Data
public class CreateUserForm {
	/*
	 * 姓名
	 */
	private String name;
	/*
	 * 部门
	 */
	private String partment;
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
	private String age;
	/*
	 * 电话
	 */
	private String tel;
	/*
	 * 状态（0：冻结，1：正常）
	 */
	private String status;
	/*
	 * 角色
	 */
	private String roleList;
}
