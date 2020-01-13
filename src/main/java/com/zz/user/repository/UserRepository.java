
package com.zz.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zz.user.entity.Role;
import com.zz.user.entity.User;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年10月28日 下午7:43:46 
 */

public interface UserRepository extends JpaRepository<User,String>{
	
	/**
	 * @Description: 根据用户名查找用户是否存在
	 * @param name 用户名
	 * @return User 对象
	 */
	public User findByName(String name);
	/**
	 * @Description: 根据用户名和密码登陆
	 * @param name 名字
	 * @param pwd 密码
	 * @return User 对象
	 */
	public User findByNameAndPwd(String name,String pwd);
	/**
	 * @Description更新操作中，如果某个字段为null则不更新，否则更新（注意符号和空格位置）
	 * @param user对象
	 * @return Integer 被修改数据条数
	 */
	@Modifying
	@Query("update User u set "+
	"u.name=CASE WHEN:#{#m.name} IS NULL THEN u.name ELSE:#{#m.name} END,"+
	"u.sex=CASE WHEN:#{#m.sex} IS NULL THEN u.sex ELSE:#{#m.sex} END,"+
	"u.age=CASE WHEN:#{#m.age} IS NULL THEN u.age ELSE:#{#m.age} END,"+
	"u.partment=CASE WHEN:#{#m.partment} IS NULL THEN u.age ELSE:#{#m.partment} END,"+
	"u.tel=CASE WHEN:#{#m.tel} IS NULL THEN u.tel ELSE:#{#m.tel} END,"+
	"u.status=CASE WHEN:#{#m.status} IS NULL THEN u.status ELSE:#{#m.status} END "+
	"where u.id=:#{#m.id}")
	public Integer update(@Param("m") User user);
	
	/**
	 * @Description 增加用户角色中间表
	 * @param u_id 角色id
	 * @param r_id 角色id
	 * @return Integer 增加条数
	 */	
	@Modifying(clearAutomatically=true)
	@Query(value="INSERT INTO t_user_role (u_id, r_id) VALUES(?1, ?2)",nativeQuery=true)
	public Integer add(String u_id,String r_id);
	/**
	 * @Description 根据用户id删除用户角色中间表
	 * @param u_id 用户id
	 * @return Integer 删除条数
	 */
	@Modifying(clearAutomatically=true)
	@Query(value="DELETE FROM t_user_role WHERE u_id=?1",nativeQuery=true)
	public Integer delete(String u_id);
	/**
	 * @Description 根据用户id查询用户角色中间表
	 * @param u_id 用户id
	 * @return Object
	 */
	@Query(value="select * from t_user_role ur left join t_user u on (ur.u_id=u.id) where ur.u_id=?1",nativeQuery=true)
	public List<User> checkId(String u_id);
}
