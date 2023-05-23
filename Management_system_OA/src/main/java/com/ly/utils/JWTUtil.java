package com.ly.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @ClassName JWTUtil
 * @Description TODO JWT工具类
 * @Author 赖昱
 * @Date 2023/5/17 - 2:35
 */
@Component
public class JWTUtil {

    @Value("${my.secretKey}")
    private String secret;

    /**
     * 生成token
     * @param userId
     * @param username
     * @param auth
     * @return
     */
    public String createToken(Long userId, String username, List<String> auth){
        Map<String,Object> header = new HashMap<>();
        header.put("alg","HS256");//加密算法
        header.put("typ","JWT");//token类型

        Date now = new Date();
        Date expireTime = new Date(now.getTime()+(1000*60*60*24*1));
        String token  = JWT.create()
                .withHeader(header)
                .withIssuedAt(now)//签发时间
                .withExpiresAt(expireTime)//过期时间
                .withClaim("userId", userId)//自定义 负载数据
                .withClaim("username", username)
                .withClaim("auth", auth)
                .sign(Algorithm.HMAC256(secret));

        return token;
    }

    /**
     * 解析验证token
     * @param token
     * @return
     */
    public boolean verifyToken(String token){

        // 创建解析对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();

        /*验证jwt*/
        try {
            /*解析验证token，获取 解析的结果*/
            DecodedJWT verify = jwtVerifier.verify(token);//解析失败会排除异常
            return true;
        }catch (Exception e){
            /*输出异常信息*/
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从token中获取ID
     * @param token
     * @return
     */
    public Long getUserId(String token){
        Long userId = null;
        /*创建解析对象*/
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        /*验证jwt*/
        try {
            /*解析验证token，获取 解析的结果*/
            DecodedJWT verify = jwtVerifier.verify(token);//解析失败会排除异常
            /*从解析的结果中 获取 执行数据*/
            /*获取对应key的负载数据 封装*/
            Claim userIdClaim = verify.getClaim("userId");
            /*从封装中获取 原数据*/
            userId = userIdClaim.asLong();
        }catch (Exception e){
            /*输出异常信息*/
            e.printStackTrace();
        }
        return userId;
    }

    /**
     * 从token中获取用户名
     * @param token
     * @return
     */
    public String getUserName(String token){
        String username = null;
        /*创建解析对象*/
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        /*验证jwt*/
        try {
            /*解析验证token，获取 解析的结果*/
            DecodedJWT verify = jwtVerifier.verify(token);//解析失败会排除异常
            /*从解析的结果中 获取 执行数据*/
            /*获取对应key的负载数据 封装*/
            Claim usernameClaim = verify.getClaim("username");
            /*从封装中获取 原数据*/
            username=usernameClaim.asString();
        }catch (Exception e){
            /*输出异常信息*/
            e.printStackTrace();
        }
        return username;
    }

    /**
     * 从token中获取权限
     * @param token
     * @return
     */
    public List<String> getAuth(String token){
        List<String> auth = new ArrayList<>();
        /*创建解析对象*/
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        /*验证jwt*/
        try {
            /*解析验证token，获取 解析的结果*/
            DecodedJWT verify = jwtVerifier.verify(token);//解析失败会排除异常
            /*从解析的结果中 获取 执行数据*/
            /*获取对应key的负载数据 封装*/
            Claim authClaim = verify.getClaim("auth");
            /*从封装中获取 原数据*/
            auth=authClaim.asList(String.class);
        }catch (Exception e){
            /*输出异常信息*/
            e.printStackTrace();
        }
        return auth;
    }


}
