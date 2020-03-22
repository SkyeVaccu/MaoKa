package com.example.login_controller7001.Controller;

import Entity.User;
import Utils.CookieUtil;
import Utils.LoginAccess;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.login_controller7001.Feign.LoginFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private LoginFeign loginFeign;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private RestTemplate restTemplate;

    @CrossOrigin
    @PostMapping("/login")
    @LoginAccess(canFreeAccess = true)
    @ResponseBody
    public String login(String account, String password, HttpServletResponse response){
        //将数据分装到对象中
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        //判断是否登录成功
        boolean result = loginFeign.login(user);
        //成功
        if(result){
            //根据信息获得token
            String token = restTemplate.postForObject("http://www.maoka.com:9001/issueToken", user, String.class);
            //将token添加到Cookie中
            CookieUtil.setCookie("token",token,response);
            return token;
        }
        //失败返回空字符串
        else
            return "";
    }

    @CrossOrigin
    @GetMapping("/threeLogin")
    public String threeLogin(String code){
        //发送请求换取token
        String cliend_id="2415007340";
        String client_secret="6d911293a721f9cab23ec84ed18628b9";
        String redirect_uri="http://www.maoka.com:7001/threeLogin";
        String tokenData = restTemplate.postForObject("https://api.weibo.com/oauth2/access_token?client_id={client_id}" +
                        "&client_secret={client_secret}" +
                        "&grant_type=authorization_code" +
                        "&redirect_uri={redirect_uri}" +
                        "&code={code}",
                null,
                String.class,
                cliend_id, client_secret, redirect_uri, code
        );
        //转换结果，获得token
        Map<String,Object> map = JSON.parseObject(tokenData, Map.class);
        String access_token=(String)map.get("access_token");
        long uid=Long.parseLong((String)map.get("uid"));

        //发送请求获得用户数据
        String jsonData = restTemplate.getForObject("https://api.weibo.com/2/users/show.json?access_token={access_token}&uid={uid}", String.class,access_token,uid);
        Map<String,Object> data = JSON.parseObject(jsonData,Map.class);
        //获取微博的账号，使用常规密码
        String account=(String) data.get("name");
        String password="123456";

        //发送请求到login处理方法，进行登录校验
        String token = restTemplate.postForObject("http://www.maoka.com:7001/login?account={account}&password={password}", null, String.class, account, password);
        //获得token，表明登录成功
        if(!"".equals(token)) {
            //携带token重定向到首页，为了下一次拦截器拦截后，token添加进cookie
            return "redirect:http://www.maoka.com:80/html/index.html?token=" + token;
        }
        else
            return null;
    }
}
