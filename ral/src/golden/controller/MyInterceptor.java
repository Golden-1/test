package golden.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import golden.controller.token.PassToken;
import golden.controller.token.UserLoginToken;
import golden.controller.TokenError;
import golden.model.User;
import golden.service.userservice;

import org.apache.ibatis.javassist.compiler.ast.Stmnt;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class MyInterceptor implements HandlerInterceptor {
	@Resource
	userservice userService;
    public void setUserService(userservice userService){
        this.userService=userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
      Cookie[]cookies=request.getCookies();
      String token=null;
      if(cookies!=null){
      for(Cookie cookie:cookies){
          if(cookie.getName().equals("token")){
              token = cookie.getValue();
          }
      }
      }
      if(token!=null) {
    	   deToken_ detoken=new deToken_();
           DecodedJWT jwt=null;
           jwt=detoken.deToken(token);//判断是否被伪造或者过期
           if(jwt==null) {
           return false;//如果令牌被伪造或者过期，则jwt为null，不予通行
       }
           else {
           	    String username;
           		jwt=JWT.decode(token);
           		username=jwt.getClaim("username").asString();
           		User user_1=new User();
           		user_1.setUsername(username);
           		User user_2=userService.selectbyusername(user_1);
           		if(user_2==null) {
           			System.out.print("username don't exit");
           			return false;
           		}
           		else {
           			String name_1=user_2.getName();
           			String name_2=jwt.getClaim("name").asString();
           			if(name_1.equals(name_2))
           				return true;
           			else {
           				System.out.println("name don't match");
           				return false;
           			}
           		}
           }
      }
      else {
        String temp = request.getHeader("Authorization");
        if(temp!=null) {
        String[] str=temp.split(" ");
        token=str[1];
        deToken_ detoken=new deToken_();
        DecodedJWT jwt=null;
        jwt=detoken.deToken(token);//判断是否被伪造或者过期
        if(jwt==null) {
        return false;//如果令牌被伪造或者过期，则jwt为null，不予通行
    }
        else {
        	    String username;
        		jwt=JWT.decode(token);
        		username=jwt.getClaim("username").asString();
        		User user_1=new User();
        		user_1.setUsername(username);
        		User user_2=userService.selectbyusername(user_1);
        		if(user_2==null) {
        			System.out.print("username don't exit");
        			return false;
        		}
        		else {
        			String name_1=user_2.getName();
        			String name_2=jwt.getClaim("name").asString();
        			if(name_1.equals(name_2))
        				return true;
        			else {
        				System.out.println("name don't match");
        				return false;
        			}
        		}
        }
    }
        else
        	return false;//token为空直接返回false
      }
    }

    
//        if(cookies!=null){
//            for(Cookie cookie:cookies){
//                if(cookie.getName().equals("token")){
//                    token = cookie.getValue();
//                }
//            }
//        }
//        if(!(object instanceof HandlerMethod)){
//            return true;
//            
//        }else{
//            HandlerMethod handlerMethod = (HandlerMethod) object;
//            Method method = handlerMethod.getMethod();
//            if(method.isAnnotationPresent(PassToken.class)){
//                PassToken passToken=method.getAnnotation(PassToken.class);
//                if(passToken.required()){
//                    return true;
//                }
//            }
//            if(method.isAnnotationPresent(UserLoginToken.class)){
//                UserLoginToken userLoginToken=method.getAnnotation(UserLoginToken.class);
//                if(userLoginToken.required()){
//                    if(token==null){
//                        throw new TokenError("-1","token null error");
//                    }
//                    String userId;
//                    try {
//                        userId= JWT.decode(token).getAudience().get(0);
//                    }catch (JWTDecodeException e){
//                        throw new TokenError("-2","token decode error");
//                    }
//                    User user = this.userService.selectUserById(Integer.valueOf(userId));
//                    if(user==null){
//                        throw new TokenError("-3","user nullpoint error");
//                    }
//                    JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(user.getPassword())).build();
//                    try{
//                        jwtVerifier.verify(token);
//                        return true;
//                    }catch (JWTVerificationException e){
//                        throw new TokenError("-4","token verified error");
//                    }
//                }
//            }
//            return true;
//        }

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}












//package golden.controller;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//
//import golden.model.User;
//
//public class MyInterceptor implements HandlerInterceptor {
//
//	@Override
//	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
//			throws Exception {
//		
//
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
//			throws Exception {
//	}
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
//		
//		HttpSession session = request.getSession();
//		User user = (User)session.getAttribute("user");
//		if(user!=null) {//如果用户已登录，给请求放行
//			return true;
//		}else {//如果用户未登录，不给请求放行
////			String path = request.getContextPath();
////			response.sendRedirect(path+"/user/tologin.do");
//			return false;
//		}
//		
//	}
//
//}
