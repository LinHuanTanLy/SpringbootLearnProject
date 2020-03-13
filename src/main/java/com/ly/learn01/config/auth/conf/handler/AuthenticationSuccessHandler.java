package com.ly.learn01.config.auth.conf.handler;

import com.ly.learn01.config.auth.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Service("authenticationsuccesshandler")
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        logger.info("User: " + authentication.getName() + " Login successfully.");
        this.returnJson(response,authentication);
    }

    private void returnJson(HttpServletResponse response,Authentication authentication) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter()
                .println("{\"tokenType\":\"Bearer\",\"token\": \"" + jwtTokenProvider.createJwtToken(authentication) + "\"}");
    }
}
