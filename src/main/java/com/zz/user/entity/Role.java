
package com.zz.user.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.zz.user.entity.Permission;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年10月28日 下午5:02:42 
 */
@Entity
@Getter
@Setter
@Table(name="T_ROLE")
@Proxy(lazy = false)
public class Role implements Serializable{

	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 6787276131921584863L;
	    @Id
	    @Column(length=100)
	    /*
	     * 编号
	     */
	    private String id;
	    /*
	     * 角色名称
	     */
	    private String detail;
	    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	    @JoinTable(name = "T_ROLE_PERMISSION", joinColumns = { @JoinColumn(name = "r_id") }, inverseJoinColumns = {
	            @JoinColumn(name = "p_id") })
	    private Set<Permission> permissions = new HashSet<Permission>();

}
