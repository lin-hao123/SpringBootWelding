
package com.zz.user.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年11月4日 上午10:56:55 
 */
@Data
public class RoleForm {
    /*
     * 角色id
     */
    private String id;
    /*
     * 角色描述
     */
	@NotEmpty(message="角色名不能为空")
    private String detail;
	/*
	 * 权限
	 */
	private String permissionList;

}
