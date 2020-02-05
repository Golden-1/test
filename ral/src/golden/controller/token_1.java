package golden.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.crypto.SecretKey;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;

import golden.model.User;

public class token_1 {
	public String getToken(final boolean isVip, final String username,
            final String name) {
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + 8 * 3600 * 1000);
            token = JWT.create()
                .withIssuer("auth0")
                .withClaim("isVip", isVip)
                .withClaim("username", username)
                .withClaim("name", name)
                .withExpiresAt(expiresAt)
                // 使用了HMAC256加密算法。
                // mysecret是用来加密数字签名的密钥。
                .sign(Algorithm.HMAC256("mysecret"));
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return token;
    }
}
//	public String getToken(User user){
//        Date start=new Date();
//        long currentTime = System.currentTimeMillis() + 3600 * 1000;
//        Date end=new Date(currentTime);
//        System.out.println(1);
//        String token= JWT.create().withAudience(new String[]{String.valueOf(user.getId())}).withIssuedAt(start).withExpiresAt(end).sign(Algorithm.HMAC256(user.getPassword()));
//        return token;
//	}


