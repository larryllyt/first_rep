package com.ly.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ly.commons.Code;
import com.ly.entity.User;
import com.ly.utils.JWTUtil;
import com.ly.vo.ResultVo;
import com.ly.vo.SecurityUserVo;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 登录拦截过滤器： 拦截所有（除了登录之外）请求
 * 从请求头中 获取 token  验证 是否存在token 以及token
 * 是否合法
 */
@Component
public class LoginFilter extends OncePerRequestFilter {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private JWTUtil jwtUtil;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /*获取访问地址 判断是否是 非登录请求*/
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/login/doLogin")||request.getRequestURI().equals("/login")){//提交登录  登录页面 不拦截
            /*登录请求 无需拦截  直接放行*/
            filterChain.doFilter(request,response);
            /*結束方法 放置 重复响应*/
            return;
        }
        /*从请求头中 获取  包含token的字符串     */
        String authorization = request.getHeader("Authorization");
        /*验证token是否存在*/
        if (authorization==null){
            /*请求中没有携带token*/
            ResultVo resultVo = new ResultVo();
            resultVo.setEnumCode(Code.TOKEN_NOT_FOUND);
            /*调用响应方法*/
            write(response, resultVo);
            return;
        }
        /*验证token是否合法--  获取完整的token*/
//        authorization.substring(7);// 去掉  Bearer空格
        String token = authorization.replace("Bearer ", "");
        boolean verifyResult = jwtUtil.verifyToken(token);
        if (!verifyResult){
            /*请求中没有携带token*/
            ResultVo resultVo = new ResultVo();
            resultVo.setEnumCode(Code.TOKEN_ILLEGAL);
            /*调用响应方法*/
            write(response, resultVo);
            return;
        }


        /*判断 redis中是否存在 对应的token key  存在 登录状态  不存在 已经登出了*/
        Boolean hasKey = stringRedisTemplate.hasKey("token:" + token);
        if (!hasKey){
            /*请求中没有携带token*/
            ResultVo resultVo = new ResultVo();
            resultVo.setEnumCode(Code.TOKEN_HAS_CLEAN);
            /*调用响应方法*/
            write(response, resultVo);
            return;
        }

        /*从token中 解析获取 数据*/
        Long userId = jwtUtil.getUserId(token);
        String userName = jwtUtil.getUserName(token);
        List<String> auth = jwtUtil.getAuth(token);
        User user = new User();
        user.setId(userId);
        user.setUsername(userName);
        SecurityUserVo securityUserVo = new SecurityUserVo(user);
        securityUserVo.setAuthorityList(auth);
        /*手动回填 认证对象*/
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(securityUserVo,null,securityUserVo.getAuthorities());//需要的数据 从 token中获取
        /*手动 往 安全上下文中  填充  认证对象*/
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        /*放行*/
        filterChain.doFilter(request,response);
    }



    /*响应json数据*/
    private void write(HttpServletResponse response, ResultVo resultVo) throws IOException {
        response.setContentType("text/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        String json = objectMapper.writeValueAsString(resultVo);
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
    }
}
