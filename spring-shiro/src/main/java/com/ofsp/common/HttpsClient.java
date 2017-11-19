package com.ofsp.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpsClient {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpsClient.class);
	
	  public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {  
	        StringBuffer buffer = new StringBuffer();  
	        try {  
	            // 鍒涘缓SSLContext瀵硅薄锛屽苟浣跨敤鎴戜滑鎸囧畾鐨勪俊浠荤鐞嗗櫒鍒濆鍖� 
	            TrustManager[] tm = { new MyX509TrustManager() };  
	            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
	            sslContext.init(null, tm, new java.security.SecureRandom());  
	            // 浠庝笂杩癝SLContext瀵硅薄涓緱鍒癝SLSocketFactory瀵硅薄  
	            SSLSocketFactory ssf = sslContext.getSocketFactory();  
	  
	            URL url = new URL(requestUrl);  
	            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
	            httpUrlConn.setSSLSocketFactory(ssf);  
	  
	            httpUrlConn.setDoOutput(true);  
	            httpUrlConn.setDoInput(true);  
	            httpUrlConn.setUseCaches(false);  
	            // 璁剧疆璇锋眰鏂瑰紡锛圙ET/POST锛� 
	            httpUrlConn.setRequestMethod(requestMethod);  
	  
	            
	            if ("GET".equalsIgnoreCase(requestMethod))  
	                httpUrlConn.connect();  
	  
	            // 褰撴湁鏁版嵁闇�鎻愪氦鏃� 
	            if (null != outputStr) {  
	                OutputStream outputStream = httpUrlConn.getOutputStream();  
	                // 娉ㄦ剰缂栫爜鏍煎紡锛岄槻姝腑鏂囦贡鐮� 
	                outputStream.write(outputStr.getBytes("UTF-8"));  
	                outputStream.close();  
	            }  
	  
	            // 灏嗚繑鍥炵殑杈撳叆娴佽浆鎹㈡垚瀛楃涓� 
	            InputStream inputStream = httpUrlConn.getInputStream();  
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
	  
	            String str = null;  
	            while ((str = bufferedReader.readLine()) != null) {  
	                buffer.append(str);  
	            }  
	            logger.debug("https buffer:"+ buffer.toString());
	            
	            bufferedReader.close();  
	            
	            inputStreamReader.close();  
	            // 閲婃斁璧勬簮  
	            inputStream.close();  
	            inputStream = null;  
	            httpUrlConn.disconnect();  
	            
	       //    jsonObject = JSONObject.fromObject(buffer.toString());  
	            
	        } catch (ConnectException ce) {  
	        	logger.error("Weixin server connection timed out.");  
	        } catch (Exception e) {  
	        	logger.error("https request error:{}", e);  
	        } 
	        return buffer.toString();  
	    }  


}
