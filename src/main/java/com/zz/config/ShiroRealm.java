package com.zz.config;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.zz.user.entity.User;



public class ShiroRealm extends AuthorizingRealm {

	@Resource
	private com.zz.user.repository.UserRepository userRepository;

	/**
	 * 获取用户角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		return null;
	}

	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String name = (String) token.getPrincipal();
		String pwd = new String((char[]) token.getCredentials());

		System.out.println("用户" + name + "认证-----ShiroRealm.doGetAuthenticationInfo");
		User user = userRepository.findByName(name);
		if (user == null) {
			throw new UnknownAccountException("用户名错误！");
		}
		//MD5加密不可破解
		//登录比较的是两个密文
		if (!pwd.equals(user.getPwd())) {
			throw new IncorrectCredentialsException("密码错误！");
		}
		if (user.getStatus()==0) {
			throw new LockedAccountException("账号已被锁定,请联系管理员！");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, pwd, getName());
		return info;
	}

}
