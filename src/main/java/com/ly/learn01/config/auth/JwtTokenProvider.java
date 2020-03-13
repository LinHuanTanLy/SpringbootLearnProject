package com.ly.learn01.config.auth;

import com.ly.learn01.domain.dao.user.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Autowired
    private AuthParameters authParameters;

    /**
     * 创建jwt Token
     *
     * @param authentication
     * @return
     */
    public String createJwtToken(Authentication authentication) {
        String userName = ((User) authentication.getPrincipal()).getUsername();
        Date expireTime = new Date(System.currentTimeMillis() + authParameters.getTokenExpiredMs());
        return Jwts.builder()
                .setSubject(userName)
                .setExpiration(expireTime)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, authParameters.getJwtTokenSecret())
                .compact();
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        String VALIDATE_FAILED = "validate failed : ";
        try {
            Jwts.parser().setSigningKey(authParameters.getJwtTokenSecret()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(VALIDATE_FAILED + e.getMessage());
            return false;
        }
    }
}
