package com.ly.learn01.filter;

import com.sun.net.httpserver.spi.HttpServerProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * jwtLog 过滤器
 * <p>
 * 首先从请求头中提取出 authorization 字段，这个字段对应的value就是用户的token。
 * 将提取出来的token字符串转换为一个Claims对象，再从Claims对象中提取出当前用户名和用户角色，
 * 创建一个UsernamePasswordAuthenticationToken放到当前的Context中，然后执行过滤链使请求继续执行下去。
 */
public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String jwtToken = req.getHeader("authorization");
        if(jwtToken!=null) {
            System.out.println("the jwtToken is " + jwtToken);
            Claims claims = Jwts.parser().setSigningKey("sang@123").parseClaimsJws(jwtToken.replace("Bearer", "")).getBody();
            //获取当前用户名
            String userName = claims.getSubject();
            // 授予的权限
            List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, null, authorityList);
            SecurityContextHolder.getContext().setAuthentication(token);
            filterChain.doFilter(req, servletResponse);
        }
    }
}
