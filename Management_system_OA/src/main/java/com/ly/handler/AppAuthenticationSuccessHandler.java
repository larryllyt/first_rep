package com.ly.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ly.commons.Code;
import com.ly.utils.JWTUtil;
import com.ly.vo.ResultVo;
import com.ly.vo.SecurityUserVo;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AppAuthenticationSuccessHandler
 * @Description TODO 自定义登录成功处理器(自定义认证成功处理器)
 * @Author 赖昱
 * @Date 2023/5/17 - 4:08
 */
@Component
public class AppAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JWTUtil jwtUtil;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 登录成功后生成token 响应token
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        ResultVo resultVo = new ResultVo();
        resultVo.setEnumCode(Code.LOGIN_SUCCESS);
        /*从认证对象中获取 用户数据*/
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        SecurityUserVo securityUserVo = (SecurityUserVo) authenticationToken.getPrincipal();
        /*生成token*/
        String token = jwtUtil.createToken(securityUserVo.getUser().getId(), securityUserVo.getUsername(),securityUserVo.getAuthorityList());

        /*将服务器 从 无状态 变为 有状态    将token存一份 在 redis中*/
        stringRedisTemplate.opsForValue().set("token:"+token,securityUserVo.getUser().getId() + "",1, TimeUnit.DAYS);//token作为key 任意值作为value

        //携带token
        resultVo.setAccessToken(token);
        String json = objectMapper.writeValueAsString(resultVo);
        /*发送token*/
        PrintWriter writer = response.getWriter();
        writer.write(json);//响应json
        writer.flush();
    }
}
