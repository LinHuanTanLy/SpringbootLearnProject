package com.ly.learn01.config.auth.conf;

import com.ly.learn01.config.auth.JwtAuthenticationFilter;
import com.ly.learn01.config.auth.conf.handler.AuthenticationFailHandler;
import com.ly.learn01.config.auth.conf.handler.AuthenticationSuccessHandler;
import com.ly.learn01.config.auth.UserDataDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {


    @Configurable
    public static class MySecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        UserDataDetailService userService;

        @Autowired
        @Qualifier("authenticationsuccesshandler")  //@Qualifier 注释和 @Autowired 注释通过指定哪一个真正的 bean 将会被装配来消除混乱
        private AuthenticationSuccessHandler successHandler;

        @Autowired
        @Qualifier("authenticationfailhandler")
        private AuthenticationFailHandler failHandler;

        @Autowired
        private AuthenticationEntryPoint entryPoint;

        @Bean
        public JwtAuthenticationFilter getJwtAuthenticationFilter() {
            return new JwtAuthenticationFilter();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.addFilterBefore(
                    getJwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/api/index/**").permitAll()
                    .anyRequest().authenticated()
                    .and().formLogin().loginProcessingUrl("/api/login")
                    .successHandler(successHandler)
                    .failureHandler(failHandler)
                    .and()
                    .exceptionHandling().authenticationEntryPoint(entryPoint);
        }


        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userService);
        }
    }
}
