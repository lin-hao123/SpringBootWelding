
package com.zz.user.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年11月4日 下午8:41:42 
 */
@Data
public class PermissionForm {
 	/*
 	 * url地址
 	 */
	@NotEmpty(message="权限地址不能为空")
    private String url;
    /*
     * 描述
     */
	@NotEmpty(message="权限名不能为空")
    private String name;

}
