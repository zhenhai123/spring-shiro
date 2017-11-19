package com.ofsp.ctl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.SysexMessage;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.JsonArray;
import com.ofsp.common.HttpsClient;
import com.ofsp.utils.JsonUtil;


@Controller
public class LogonController {
	
	
	private static Logger logger = LoggerFactory.getLogger(LogonController.class);
	
	private static String get_code_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	private static String access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code"; 
	
	
	@RequestMapping(value="/logon", method=RequestMethod.GET)
	public String logon(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		
		
		String url = null; 
		String code = request.getParameter("code");
		
		// 获取subject
		Subject subject = SecurityUtils.getSubject();
		// 判断用户是否登录
		if(!subject.isAuthenticated()){
			if(code==null){
				String redirectUrl = URLEncoder.encode("", "UTF-8");
				String appId = "";
				String realApplyUrl = request.getServletPath();
				// 创建获取用户code的重定向url
				String getCodeUrl = get_code_url.replace("APPID", appId).replace("REDIRECT_URI", redirectUrl).replace("STATE", realApplyUrl);
				url = "redirect:" + getCodeUrl;
			}else{
				String appId = "";
				String secrect = "";
				// 创建获取用户openid的重定向url
				String accessTokenUrl = access_token_url.replace("APPID", appId).replace("SECRET", secrect).replace("CODE", code);
				// http请求获取用户用户的基本信息
			    String accessTokenStr = HttpsClient.httpRequest(accessTokenUrl, "GET", null);
			    logger.info("--------------------------获取用户access_token：【"+accessTokenStr+"】成功----------------------------------------");
			    Map<String, String> map = JsonUtil.jsonToMap(accessTokenStr);
			    String openid = map.get("openid");
				// 使用UsernamePasswordToken对象封装用户名和密码
				UsernamePasswordToken token  = new UsernamePasswordToken("111", "111");
				// 使用subject实例中的login（token）,下面进入realm验证
				subject.login(token);
				Session session = subject.getSession();
				session.setAttribute("openId", openid);
				
				url = "redirect:" + request.getParameter("state");
			}
		}
		
		return url;
	}
	
	
	
	
	@RequestMapping(value="logonSuccess")
	public String logonSuccess (HttpServletRequest request, HttpServletResponse response){
		
		
		System.out.println("验证成功");
		
		return "abc";
		
	}
	
	
//	public static void main(String[] args) {
//		List<String> list=new ArrayList<>();
//		list.add("1");
//		list.add("5");
//		list.add("3");
//		list.add("2");
//	//	list.forEach((a)->System.err.println(a));
//		
//		Collections.sort(list, (a, b) -> a.compareTo(b));
//		Collections.sort(list, (a, b) -> a.compareTo(b));
//		
//		list.stream().filter((a) -> a > 60).forEach(i-> send(""+i));
//		
//		list.stream().filter((a) -> a > 60);
//		
//	}
	
	public static void main(String[] args) {
		
		
	}
	

}
