package com.ly.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ly.commons.Code;
import com.ly.vo.ResultVo;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName AppAccessDeniedHandler
 * @Description TODO 自定义没有权限处理器
 * @Author 赖昱
 * @Date 2023/5/17 - 20:43
 */
@Component
public class AppAccessDeniedHandler implements AccessDeniedHandler {

    @Resource
    private ObjectMapper objectMapper;

    /**
     * @param request
     * @param response
     * @param e   没有权限异常
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        /*创建 响应类型对象*/
        ResultVo resultVo = new ResultVo();
        resultVo.setEnumCode(Code.NOT_HAS_AUTH);
        /*将 对象 序列化为 json数据*/
        String json = objectMapper.writeValueAsString(resultVo);
        /*将 json数据 响应给前端*/
        PrintWriter writer = response.getWriter();
        writer.write(json);//响应json
        writer.flush();
    }
}
