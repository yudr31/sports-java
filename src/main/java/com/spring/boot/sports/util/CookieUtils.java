package com.spring.boot.sports.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * author yuderen
 * version 2018/11/20 16:03
 */
public class CookieUtils {

    private static final Logger log = LoggerFactory.getLogger(CookieUtils.class);

    public CookieUtils() {
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0) {
            Cookie[] arr$ = cookies;
            int len$ = cookies.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Cookie c = arr$[i$];
                if(c.getName().equals(name)) {
                    return c;
                }
            }
        }

        return null;
    }

    public static String getCookieString(Cookie cookie) {
        if(null == cookie) {
            return null;
        } else {
            try {
                String value = URLDecoder.decode(cookie.getValue(), "UTF-8");
                return value;
            } catch (Exception var2) {
                log.warn(var2.toString());
                return "";
            }
        }
    }

    public static String getCookieString(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        return getCookieString(cookie);
    }

    public static Cookie addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer expiry, String domain) {
        try {
            value = URLEncoder.encode(value, "UTF-8");
        } catch (Exception var8) {
            log.error(var8.toString());
        }

        Cookie cookie = new Cookie(name, value);
        if(expiry != null) {
            cookie.setMaxAge(expiry.intValue());
        }

        if(StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        cookie.setHttpOnly(true);

        String ctx = request.getContextPath();
        cookie.setPath(StringUtils.isBlank(ctx)?"/":ctx);
        response.addCookie(cookie);
        System.out.println(response.getHeaderNames());
        System.out.println("Set-Cookie:" + response.getHeader("Set-Cookie"));
        return cookie;
    }

    public static void cancleCookie(HttpServletRequest request, HttpServletResponse response, String name, String domain) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        String ctx = request.getContextPath();
        cookie.setPath(StringUtils.isBlank(ctx)?"/":ctx);
        if(StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }

        response.addCookie(cookie);
    }


}
