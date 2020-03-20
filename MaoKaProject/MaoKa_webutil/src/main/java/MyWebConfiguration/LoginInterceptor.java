package MyWebConfiguration;

import Utils.CookieUtil;
import Utils.LoginAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //获得该注解
        LoginAccess loginAccessAnnotation = handlerMethod.getMethodAnnotation(LoginAccess.class);
        //判断能否通过
        boolean flag=false;
        if(loginAccessAnnotation==null)
            flag=true;
        else if(loginAccessAnnotation.canFreeAccess())
            flag=true;
        //获得最准确的token
        String token=null;
        String oldToken = CookieUtil.getCookie("token", request);
        String newToken = request.getParameter("token");
        if(!StringUtils.isEmpty(oldToken))
            token=oldToken;
        if(!StringUtils.isEmpty(newToken))
            token=newToken;

        //token不存在
        if(token==null) {
            if (flag)//允许通过
                return true;
            else { //不能通过,表明打回login页
                response.getWriter().print("loginPage");
                return false;
            }
        }

        //token存在
        else{
            //解析Token
            Map<String,Object> map = restTemplate.postForObject("wwww.maoka.com:9001/parseToken", token, Map.class);
            //获得解析结果
            boolean result = (boolean) map.get("result");
            if(result)//解析成功
                CookieUtil.setCookie("token",token,response);//将token放到cookie中
            else{//解析失败
                if (flag)//允许通过
                    return true;
                else { //需要校验，表明打回login页
                    response.getWriter().print("loginPage");
                    return false;
                }
            }
        }
        return super.preHandle(request, response, handler);
    }
}
