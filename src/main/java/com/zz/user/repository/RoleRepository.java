
package com.zz.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zz.user.entity.Role;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年11月4日 上午10:47:22 
 */

public interface RoleRepository extends JpaRepository<Role,String> {
	/**
	 * @Description更新操作中，如果某个字段为null则不更新，否则更新（注意符号和空格位置）
	 * @param role对象
	 * @return Integer 被修改数据条数
	 */
	@Modifying
	@Query("update Role tr set "+
	"tr.detail=CASE WHEN:#{#m.detail} IS NULL THEN tr.detail ELSE:#{#m.detail} END "+
	"where tr.id=:#{#m.id}")
	public Integer update(@Param("m") Role role);
	/**
	 * @Description 根据角色名查询角色
	 * @param name 角色名
	 * @return Role 角色对象
	 */
	public Role findByDetail(String detail);
	/**
	 * @Description 增加角色权限中间表
	 * @param r_id 角色id
	 * @param p_id 权限id
	 * @return Integer 增加条数
	 */
	@Modifying(clearAutomatically=true)
	@Query(value="INSERT INTO t_role_permission (r_id, p_id) VALUES(?1, ?2)",nativeQuery=true)
	public Integer add(String r_id,String p_id);
	/**
	 * @Description 根据角色id删除角色权限中间表
	 * @param r_id 角色id
	 * @return Integer 删除条数
	 */
	@Modifying(clearAutomatically=true)
	@Query(value="DELETE FROM t_role_permission WHERE r_id=?1",nativeQuery=true)
	public Integer delete(String r_id);
	
}
