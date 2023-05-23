package com.ly.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ly.commons.Code;
import com.ly.vo.ResultVo;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName AppAuthenticationSuccessHandler
 * @Description TODO 自定义登录失败处理器(自定义认证失败处理器)
 * @Author 赖昱
 * @Date 2023/5/17 - 4:08
 */
@Component
public class AppAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Resource
    private ObjectMapper objectMapper;
    /**
     * @param request  请求对象
     * @param response 响应独享
     * @param e 认证异常对象
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException{
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        /*创建 响应类型对象*/
        ResultVo resultVo = new ResultVo();
        /*失败原因：根据 认证异常类型 匹配*/
        if (e instanceof UsernameNotFoundException){//如果能强转，说明类型匹配
            resultVo.setEnumCode(Code.USERNAME_NOT_FOUND);
        }else if(e instanceof DisabledException){
            resultVo.setEnumCode(Code.USER_DISABLED);
        }else if(e instanceof BadCredentialsException){
            resultVo.setEnumCode(Code.PSW_ERROR);
        }else if(e instanceof CredentialsExpiredException){
            resultVo.setEnumCode(Code.PSW_EXPIRED);
        }else if(e instanceof AccountExpiredException){
            resultVo.setEnumCode(Code.USER_EXPIRED);
        }else if(e instanceof LockedException){
            resultVo.setEnumCode(Code.USER_LOCKED);
        }
        /*将 对象 序列化为 json数据*/
        String json = objectMapper.writeValueAsString(resultVo);
        /*将 json数据 响应给前端*/
        PrintWriter writer = response.getWriter();
        writer.write(json);//响应json
        writer.flush();
    }
}
