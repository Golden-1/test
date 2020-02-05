package golden.controller;


import java.util.HashMap;
import java.util.Map;


public class TokenError extends RuntimeException {
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public TokenError(String code, String message){
       this.code=code;
       this.message=message;
    }
}
