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
		
		// ��AuthenticationTokenת�����Լ�ǰ�涨���token
		//UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
		
		// ��ȡ�û���������
		
		// ��װ�����ݿ��ѯ�����û���������
		
		Object principal = "111";
		Object credentials = "111";
		SimpleAuthenticationInfo info  = new SimpleAuthenticationInfo(principal, credentials, this.getName());
		
		return info;
	}



}
