package com.sxh.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sxh.security.MyUserDetails;
import com.sxh.util.UuidUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * 登录验证类，生成token放到header中
 * @author sxh
 * @date 2020/3/14
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    private Logger logger = LoggerFactory.getLogger(JWTLoginFilter.class);

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 接收并解析用户凭证，出現错误时，返回json数据前端
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Authentication authentication = null;
        try {
            // 只支持json形式的请求
            MyUserDetails myUserDetails = new ObjectMapper().readValue(request.getInputStream(), MyUserDetails.class);
            // 进入MyUserDetailsService，验证用户名密码
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(myUserDetails.getUsername(),myUserDetails.getPassword(),new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException("登录失败！");
        }
        return authentication;
    }

    /**
     * 用户登录成功后，生成token,并且返回json数据给前端
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        MyUserDetails myUserDetails = (MyUserDetails) authResult.getPrincipal();
        String token = Jwts.builder()
                .setId(UuidUtil.getUid())
                .setSubject(myUserDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000)) // 设置过期时间为24小时
                .setNotBefore(new Date()) // 不能被接收处理时间，在此之前不能被接收处理
                .signWith(SignatureAlgorithm.HS512, "MyJwtSecret")
                .compact();
        response.addHeader("Authorization", "Bearer " + token);
        logger.info("登录成功！token生成成功！");
    }
}
