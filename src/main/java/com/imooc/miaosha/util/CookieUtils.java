package com.imooc.miaosha.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {



    public static void setCookie(HttpServletResponse response, String name, String value, int expiry){
        Cookie cookie=new Cookie(name,value);
        cookie.setMaxAge(expiry);
        response.addCookie(cookie);
    }

}
