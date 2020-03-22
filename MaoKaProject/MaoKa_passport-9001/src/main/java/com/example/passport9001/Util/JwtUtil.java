package com.example.passport9001.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    // 给定一个算法，如HmacSHA-256
    static Algorithm alg = Algorithm.HMAC256("secret");

    /**
     * 加密算法
     *
     * @param param 用户的属性
     * @return 返回加密的token
     */
    public static String encode(Map<String, Object> param) {
        Date currentTime = new Date();//选中当前时间
        //创建一个token
        String token = JWT.create()
                .withIssuer("skye")//指定签发作者名
                .withAudience("org.example")//指定企业名
                .withExpiresAt(new Date(currentTime.getTime() + 24 * 3600 * 1000L))//指定签发时间
                .withClaim("id", (int) param.get("id"))//增加用户的信息
                .withClaim("account", (String) param.get("account"))
                .sign(alg);//指定算法
        return token;
    }

    /**
     * 解密算法
     *
     * @param token 需要校验的token
     * @return 返回解析结果
     */
    public static Map<String, Object> decode(String token) {
        Map<String, Object> result = new HashMap<>();//创建返回的Map
        //创建一个验证器，验证token的准确性
        JWTVerifier verifier = JWT.require(alg)//表明使用的算法
                .withIssuer("skye")//指定签发作者名
                .withAudience("org.example")//指定企业名
                .build();
        try {
            verifier.verify(token);//验证token是否正确
            result.put("result", true);//成功结果放入Map
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);//失败结果放入Map
        }
        DecodedJWT decode = JWT.decode(token);//使用JWT对token解析解析
        result.put("issuer", decode.getIssuer());//签发用户名放入Map

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        result.put("date", dateFormat.format(decode.getExpiresAt()));//签发时间放入Map

        result.put("id", decode.getClaim("id"));//用户信息放入Map
        result.put("account", decode.getClaim("account"));
        return result;
    }
}
