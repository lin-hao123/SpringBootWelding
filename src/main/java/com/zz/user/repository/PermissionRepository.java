
package com.zz.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zz.user.entity.Permission;
import com.zz.user.entity.Role;

/**
 * @Description: 权限Repository
 * @Author asuslh
 * @DateTime 2019年11月4日 下午8:33:04 
 */
public interface PermissionRepository extends JpaRepository<Permission,String>{

	/**
	 * @Description 修改权限信息
	 * @param Permission 对象
	 * @return Integer 被修改数据条数
	 */
	@Modifying
	@Query("update Permission ps set "+
	"ps.name=CASE WHEN:#{#m.name} IS NULL THEN ps.name ELSE:#{#m.name} END,"+
	"ps.url=CASE WHEN:#{#m.url} IS NULL THEN ps.url ELSE:#{#m.url} END "+
	"where ps.id=:#{#m.id}")
	public Integer update(@Param("m") Permission permission);
	
	/**
	 * @Description 根据权限名查询权限
	 * @param name 权限名
	 * @return Permission 权限对象
	 */
	public Permission findByName(String name);
}
