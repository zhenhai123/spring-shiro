package com.ofsp.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;

public class BusinessRealm  extends AuthenticatingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo( AuthenticationToken authenticationToken) throws AuthenticationException {
		
		// 将AuthenticationToken转换成自己前面定义的token
		//UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
		
		// 获取用户名和密码
		
		// 封装从数据库查询到的用户名和密码
		
		Object principal = "111";
		Object credentials = "111";
		SimpleAuthenticationInfo info  = new SimpleAuthenticationInfo(principal, credentials, this.getName());
		
		return info;
	}



}
