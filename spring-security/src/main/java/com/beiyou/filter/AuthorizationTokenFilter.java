package com.beiyou.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Slf4j
public class AuthorizationTokenFilter extends OncePerRequestFilter {

    @Value("${permitAll.url}")
    private String[] permitAllUrl;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (!ArrayUtils.contains(permitAllUrl, httpServletRequest.getRequestURI())) {
            String token = httpServletRequest.getHeader("Authorization");
            if (StringUtils.isNotEmpty(token)) {
                // 从redis中取出用户信息,没有集成redis,这块最好使用redis。后期可以集群共享
                Authentication authentication = TokenAndAuthentication.getAuthentication(token);
                if (authentication == null) {
                    commence(httpServletResponse, AjaxResult.error(HttpStatus.FORBIDDEN.value(), "令牌不可用"));
                } else {
                    // 添加至上下文中
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                }
            } else {
                commence(httpServletResponse, AjaxResult.error(HttpStatus.FORBIDDEN.value(), "未携带令牌"));
            }
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    public void commence(HttpServletResponse response, AjaxResult ajaxResult) throws IOException {
        log.error("异常：{}", ajaxResult.getMsg());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out;
        out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(ajaxResult));
        out.flush();
        out.close();
    }
}
