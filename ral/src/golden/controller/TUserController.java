package golden.controller;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.util.Date;  
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import golden.model.User;
import golden.service.userservice;

@Controller
@RequestMapping("/user")
public class TUserController {

	@Autowired
	userservice nuserservice;
	
//	@RequestMapping("/toregister.do")
//	
//	public String register() {
//		return "register";
//	}
//	@RequestMapping("/tologin.do")
//	public String login() {
//		return "login";
//	}
	

	@RequestMapping("/tologin.do")
	@ResponseBody
	public JSONObject login(User nuser,String token,HttpServletRequest head,HttpServletResponse response) throws NoSuchAlgorithmException {//获取提交的表单然后插入
        String message;	
		int code;
		JSONObject result = new JSONObject();
		if(nuser.getPassword()!="") {
	    MessageDigest md = MessageDigest.getInstance("MD5");  
	    md.update(nuser.getPassword().getBytes());
	    byte b[]=md.digest();
	    int i;
	    StringBuffer buf = new StringBuffer("");
	    for (int offset = 0; offset < b.length; offset++) {
	    i = b[offset];
	    if(i<0) i+= 256;
	    if(i<16)
	    buf.append("0");
	    buf.append(Integer.toHexString(i));
	    }
	    nuser.setPassword(buf.toString());
}
//	    byte b[] = md.digest();
	
//		result.put("md5",b);
	    User user_1=nuserservice.selectuser(nuser);
		if(nuserservice.selectuser(nuser)!=null) {
		message="success";
		code=1;
		token_1 token_2=new token_1();
		token=token_2.getToken(true,user_1.getUsername(),user_1.getName());
		response.addCookie(new Cookie("token",token));
		result.put("username",nuser.getUsername());
		result.put("message",message);
		result.put("code", code);
		result.put("token",token);
		}
		else {
			if(nuser.getUsername()=="") {
				message="username loss";
				code=-2;
			}
			else if(nuser.getPassword()=="") {
				message="password loss";
				code=-3;
			}
			else if(nuserservice.selectbyusername(nuser)==null) {
			message="username doesn't exit";
			code=-1;
		}
		    else {
		    	message="password error";
			    code=0;
		    }
	}
		result.put("message",message);
	    result.put("code", code);
		return result;
	}
	
	
	
	@RequestMapping("/toregister.do")
	@ResponseBody
	public JSONObject toregister(User nuser) throws NoSuchAlgorithmException{//获取提交的表单然后插入
		JSONObject result = new JSONObject();
		int code;
		String message;
    if(nuser.getUsername()=="") {
			message="username loss";
			code=-2;
			result.put("message", message);
			result.put("code", code);
			return result;
		}
	else if(nuser.getPassword()=="") {
			message="password loss";
			code=-3;
			result.put("message", message);
			result.put("code", code);
			return result;
		}
	else if(nuser.getName()=="") {
		message="name loss";
		code=-4;
		result.put("message", message);
		result.put("code", code);
		return result;
	}
	else if(nuserservice.selectbyusername(nuser)!=null) {	
		message="username exits";
		code=0;
		result.put("message", message);
		result.put("code", code);
		return result;
	}
	else {
	MessageDigest md = MessageDigest.getInstance("MD5");  
    md.update(nuser.getPassword().getBytes());
    byte b[]=md.digest();
    int i;
    StringBuffer buf = new StringBuffer("");
    for (int offset = 0; offset < b.length; offset++) {
    i = b[offset];
    if(i<0) i+= 256;
    if(i<16)
    buf.append("0");
    buf.append(Integer.toHexString(i));
    }
    nuser.setPassword(buf.toString());//用md5加密后存进数据库，具体原理到时候再 学一学！
		nuserservice.insertuser(nuser);
	if(nuserservice.selectuser(nuser)!=null) {
		message="success";
	    code=1;
	    result.put("message", message);
		result.put("code", code);
		return result;
	}
	else {
		message="failure";
		code=-1;
		result.put("message", message);
		result.put("code", code);
		return result;
		}
	}
	}
	
	
	@RequestMapping("/multilanguage.do")
	@ResponseBody
	public JSONObject tomultilanguage(String language,String text) {
		JSONObject result = new JSONObject();
		int code=200;
		String message="Success",sentiment1="happiness",sentiment2="surprise";//下面插入对text的分析
        result.put("code", code);
        result.put("message", message);
        result.put("sentiment1", sentiment1);
        result.put("sentiment2", sentiment2);
        return result;
	}
}