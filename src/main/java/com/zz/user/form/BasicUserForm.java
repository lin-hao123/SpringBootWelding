
package com.zz.user.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年10月28日 下午7:51:15 
 */
@Data
public class BasicUserForm {
	/*
	 * 姓名
	 */
	@NotEmpty(message="姓名不能为空")
	private String name;
	/*
	 * 密码
	 */
	@NotEmpty(message="密码不能为空")
	private String pwd;
	/*
	 * 性别
	 */
	@NotEmpty(message="性别不能为空")
	private String sex;
	/*
	 * 年龄
	 */
	private String age;
	/*
	 * 电话
	 */
	@NotEmpty(message="电话不能为空")
	private String tel;

}
