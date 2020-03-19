import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CookieUtil {

    /**
     * 判断Cookie中是否有该键
     * @param key 传入的键
     * @param request 请求对象
     * @return 返回是否存在
     */
    public static boolean isExist(String key,HttpServletRequest request){
        return getCookie(key,request)==null?false:true;
    }


    /**
     * 获得Cookie键对应的值
     * @param key 键
     * @param request 请求对象
     * @return 值
     */
    public static String getCookie(String key, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies==null)
            return null;
        else{
            for (Cookie cookie : cookies) {
                if(key.equals(cookie.getName()))
                    return cookie.getValue();
            }
            return null;
        }
    }

    /**
     * 设置Cookie，使用默认的时间
     * @param key 键
     * @param value 值
     * @param response 响应对象
     * @return 返回是否添加成功
     */
    public static boolean setCookie(String key,String value,HttpServletResponse response){
        try {
            Cookie cookie=new Cookie(key,value);
            response.addCookie(cookie);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 设置Cookie和过期时间
     * @param key 键
     * @param value 值
     * @param time 过期时间
     * @param response 响应对象
     * @return 返回是否添加成功
     */
    public static boolean setCookie(String key,String value,int time,HttpServletResponse response){
        try {
            Cookie cookie=new Cookie(key,value);
            cookie.setMaxAge(time);
            response.addCookie(cookie);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
