package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().mvcMatchers(HttpMethod.POST, "/user/client").permitAll()
                                .mvcMatchers(HttpMethod.POST, "/user/merchant").permitAll()
                                .mvcMatchers(HttpMethod.POST, "/user/admin").permitAll()
                                .mvcMatchers(HttpMethod.POST, "/merchant").hasAuthority("ADMIN")
                                .mvcMatchers(HttpMethod.GET, "/user/userid/{userId}").authenticated()
                                .mvcMatchers(HttpMethod.GET, "/user/email/{email}").authenticated()
                                .mvcMatchers(HttpMethod.DELETE, "/user/{userId}").authenticated()
                                .mvcMatchers(HttpMethod.GET, "/order/orderid/{orderId}").authenticated()
                                .mvcMatchers(HttpMethod.GET, "/order/userorders/{userIdId}").authenticated()
                                .mvcMatchers(HttpMethod.GET, "/order").hasAuthority("ADMIN")
                                .mvcMatchers(HttpMethod.GET, "/order/{merchantName}").hasAuthority("ADMIN")
                                .mvcMatchers(HttpMethod.POST, "/order").hasAuthority("CLIENT")
                                .mvcMatchers(HttpMethod.PATCH, "/order/{orderId}").hasAuthority("CLIENT")
                                .mvcMatchers(HttpMethod.DELETE, "/order/{orderId}").hasAuthority("CLIENT")
                                .mvcMatchers(HttpMethod.PATCH, "/order/status/next/{orderId}").hasAuthority("MERCHANT")
                                .mvcMatchers(HttpMethod.PATCH, "/order/status/previous/{orderId}").hasAuthority("MERCHANT");
    }
}
