
package com.zz.user.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年11月8日 下午3:42:14 
 */
@Data
public class UpdateUserForm {
	/*
	 * 编号
	 */
	@NotEmpty(message="用户id不能为空")
	private String id;
	/*
	 * 姓名
	 */
	@NotEmpty(message="用户名不能为空")
	private String name;
	/*
	 * 部门
	 */
	private String partment;
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
	@NotEmpty(message="电话不能为空")
	private String tel;
	/*
	 * 状态（0：冻结，1：正常）
	 */
	@NotEmpty(message="状态不能为空")
	private String status;
	/*
	 * 角色
	 */
	private String roleList;
}
