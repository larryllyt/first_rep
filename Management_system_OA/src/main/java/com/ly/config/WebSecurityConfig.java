package com.ly.config;

import com.ly.filter.LoginFilter;
import com.ly.handler.AppAccessDeniedHandler;
import com.ly.handler.AppAuthenticationFailureHandler;
import com.ly.handler.AppAuthenticationSuccessHandler;
import com.ly.handler.AppLogoutSuccessHandler;
import com.ly.configService.SecurityUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @ClassName WebSecurityConfig
 * @Description TODO 实现自定义安全配置类
 * @Author 赖昱
 * @Date 2023/5/17 - 2:24
 */
@Slf4j
// 启用全局方法安全注解实现基于方法的鉴权
@EnableGlobalMethodSecurity(prePostEnabled = true)// 包含Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*注入 登录成功处理器*/
    @Resource
    private AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;

    /*注入  登录失败处理器*/
    @Resource
    private AppAuthenticationFailureHandler appAuthenticationFailureHandler;

    /*注入 登出成功处理器*/
    @Resource
    private AppLogoutSuccessHandler appLogoutSuccessHandler;

    /*注入  没有权限处理器*/
    @Resource
    private AppAccessDeniedHandler accessDeniedHandler;

    /*注入  自定义UserDetailsService实现类bean*/
    @Resource
    private SecurityUserDetailsService securityUserDetailsService;

    // 自定义登录过滤器
    @Resource
    private LoginFilter loginFilter;

    /**
     * 调用用户详情服务进行认证授权
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    /**
     * http的相关配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 添加登录拦截过滤器
        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);
        // 登录方式 不需要认证就可以访问
        http.formLogin()
                .successHandler(appAuthenticationSuccessHandler)
                .failureHandler(appAuthenticationFailureHandler)
                .loginProcessingUrl("/login/doLogin")
                .permitAll();
        // 登出
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(appLogoutSuccessHandler)//登出成功处理器
                .permitAll();
        // 没有权限配置
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)//没有权限处理器
        ;
        http.authorizeRequests()
                /*配置资源不需要认证 直接访问*/
                .mvcMatchers("/login","/login/doLogin").permitAll()
                .anyRequest().authenticated()//配置所有资源都需要认证
        ;
        /*使用自定义的登录页面需要 关闭 csrf（放跨域攻击）*/
        http.csrf().disable();
        //禁用session  实现基于token认证---系统不再自动往安全向下文中填充 认证对象 需要 手动回填
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * web配置 静态资源匹配放行
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        /*忽略 某目录下的 静态资源*/
        web.ignoring().mvcMatchers("/resources/**");
    }

    /**
     * 密码加密器/密码编码器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 禁止覆盖UsernameNotFoundException异常并抛出BadCredentialsException异常
     * @return
     */
    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(securityUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        return daoAuthenticationProvider;
    }
}
