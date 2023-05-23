package com.ly.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ly.commons.Code;
import com.ly.vo.ResultVo;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName AppAuthenticationSuccessHandler
 * @Description TODO 自定义登出成功处理器(自定义登出成功处理器）
 * @Date 2023/5/17 - 4:08
 */
@Component
public class AppLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ObjectMapper objectMapper;

    /*当用户登出成功后 执行的方法*/
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        /*登录 ，将用户的状态 删除  将redis中的token删除*/
        String authorization = request.getHeader("Authorization");
        /*验证token是否合法--  获取完整的token*/
        String token = authorization.replace("Bearer ", "");
        stringRedisTemplate.delete("token:"+token);
        /*创建 响应类型对象*/
        ResultVo resultVo =new ResultVo();
        resultVo.setEnumCode(Code.LOGOUT_SUCCESS);
//        resultVo.setData(authentication);//根据业务逻辑 携带 数据
        /*将 对象 序列化为 json数据*/
        String json = objectMapper.writeValueAsString(resultVo);
        /*将 json数据 响应给前端*/
        PrintWriter writer = response.getWriter();
        writer.write(json);//响应json
        writer.flush();
    }
}
