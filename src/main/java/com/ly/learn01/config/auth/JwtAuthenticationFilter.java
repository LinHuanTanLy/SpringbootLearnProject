package com.ly.learn01.config.auth;

import com.ly.learn01.domain.dao.user.User;
import com.ly.learn01.service.user.UserService;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.Authenticator;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);


    @Autowired
    private AuthParameters authParameters;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserService userService;


    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String token = getJwtTokenFroRequest(httpServletRequest);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            String userName = getUsernameFromJwt(token, authParameters.getJwtTokenSecret());
            User user = userService.findOneByUserName(userName);
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            logger.error(httpServletRequest.getParameter("username") + " :Token is null");
        }
        super.doFilter(httpServletRequest, httpServletResponse, filterChain);
    }

    /**
     * 从request中获取token
     *
     * @param request
     * @return
     */
    private String getJwtTokenFroRequest(HttpServletRequest request) {
        String defToken = request.getHeader("Authorization");
        if (defToken != null && defToken.startsWith("Bearer")) {
            return defToken.replace("Bearer ", "");
        }
        return null;
    }

    /**
     * 从Jwt获取用户名，生成token    该用户名已设置为jwt。
     *
     * @param token
     * @param signKey
     * @return
     */
    private String getUsernameFromJwt(String token, String signKey) {
        return Jwts.parser().setSigningKey(signKey)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}
