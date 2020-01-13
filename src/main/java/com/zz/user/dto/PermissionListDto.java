
package com.zz.user.dto;

import java.util.List;

import com.zz.user.entity.Permission;

import lombok.Data;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年11月13日 上午10:47:14 
 */
@Data
public class PermissionListDto {
	List<Permission> permissionList;

}
