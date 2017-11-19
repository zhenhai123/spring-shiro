package com.ofsp.common;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 璇佷功淇′换绠＄悊鍣紙鐢ㄤ簬https璇锋眰锛�
 * 
 * @author yangwl
 * 
 * @date 2016骞�鏈�1鏃�7:16:05
 */
public class MyX509TrustManager implements X509TrustManager {

	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}