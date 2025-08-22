package com.beiyou.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.UUID;

@Configuration
// 启用Security
@EnableWebSecurity
// 启用方法级注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthorizationTokenFilter authenticationTokenFilter;

    @Value("${permitAll.url}")
    private String[] permitAllUrl;

    /**
     * 密码编码器
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


    /**
     * 配置身份验证程序 和密码验证方式
     *
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 第一种 dao 认证，由UserDetailsService 实现来返回认证西信息
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        // 第二种 在内存中创建好用户来匹配登录用户
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .withUser("admin")
//                .password(passwordEncoder().encode("123456"))
//                .authorities("admin");
        // 第三种 自定义实现身份认证
        auth.authenticationProvider(new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String username = authentication.getName();
                String password = (String) authentication.getCredentials();
                log.info("前端传过来的明文密码:{}", password);
                UserDetails user = userDetailsService.loadUserByUsername(username);
                if (user == null) {
                    throw new UsernameNotFoundException("用户不存在");
                }
                if (!passwordEncoder().matches(password, user.getPassword())) {
                    throw new DisabledException("密码错误");
                }

                Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
                // 构建认证信息返回
                return new UsernamePasswordAuthenticationToken(user, user.getPassword(), authorities);
            }

            @Override
            public boolean supports(Class<?> aClass) {
                return true;
            }
        });
    }


    /**
     * 配置 HttpSecurity 策略
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 添加过滤器，在token访问时 将权限信息加入上下文中
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 添加异常处理器
        http.exceptionHandling()
                .accessDeniedHandler(this::handle)
                .authenticationEntryPoint(this::commence);
        // 请求访问策略
        http.authorizeRequests()
                .antMatchers(permitAllUrl).permitAll()
                .anyRequest().authenticated();
        // 启用Security 登录，登录处理策略
        http.formLogin()
                // 定义登录接口 默认 login
                .loginProcessingUrl("/login")
                .permitAll()
                //  登录失败处理
                .failureHandler(this::failureHandler)
                // 登录成功处理
                .successHandler(this::successHandler)
                .and()
                // 退出处理
                .logout().logoutUrl("/logout")
                // 退出成功处理
                .logoutSuccessHandler(this::logoutSuccessHandler);

        //开启跨域访问
        http.cors().disable();
        // 关闭csrf防护
        http.csrf().disable();
    }

    public void logoutSuccessHandler(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("退出");
        String token = request.getHeader("Authorization");
        TokenAndAuthentication.delete(token);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out;
        out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(AjaxResult.success("退出成功")));
        out.flush();
        out.close();
    }

    /**
     * 登录成功处理
     *
     * @param request
     * @param response
     * @param authentication
     */
    public void successHandler(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("登录成功处理");
        String token = UUID.randomUUID().toString();
        TokenAndAuthentication.setTokenAndAuthentication(token, authentication);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out;
        out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(AjaxResult.successData(token)));
        out.flush();
        out.close();

    }

    /**
     * 抛出AccessDeniedException异常
     * 没权限
     *
     * @param request
     * @param response
     * @param accessDeniedException
     */
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        log.error("异常：{}", accessDeniedException.getMessage());
        accessDeniedException.printStackTrace();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out;
        out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(AjaxResult.error(403, accessDeniedException.getMessage())));
        out.flush();
        out.close();
    }

    /**
     * 抛出AuthenticationException异常
     *
     * @param request
     * @param response
     * @param authException
     */
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        log.error("异常：{}", authException.getMessage());
        authException.printStackTrace();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out;
        out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(AjaxResult.error(HttpStatus.FORBIDDEN.value(), authException.getMessage())));
        out.flush();
        out.close();
    }

    private void failureHandler(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {
        log.error("异常：{}", authenticationException.getMessage());
        authenticationException.printStackTrace();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out;
        out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(AjaxResult.error(HttpStatus.BAD_REQUEST.value(), "登录失败，用户名或密码不正确")));
        out.flush();
        out.close();
    }

