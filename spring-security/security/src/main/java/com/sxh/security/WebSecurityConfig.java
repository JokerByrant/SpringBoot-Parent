package com.sxh.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author 一池春水倾半城
 * @date 2019/9/6
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                // .loginProcessingUrl("/login") // 这里的会跳转至Spring Security默认的页面
                .loginPage("/login") // 这里会跳转至自己配置的login接口
//                 .successForwardUrl("/hello") // successForwardUrl并不是只支持post请求，它的请求类型跟随login的请求类型，相当于请求地址从login转到/hello，类型不变
                .defaultSuccessUrl("/hello") // defaultSuccessUrl相当于构建了一个successHandler，将请求重定向至/hello接口
                .failureUrl("/login?error=true").permitAll()
                .and()
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().permitAll();
//                .and()
//                .addFilter(new JWTLoginFilter(authenticationManager())) // 增加登录拦截
//                .addFilter(new JWTAuthenticationFilter(authenticationManager())) // 增加登录信息验证过滤
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 禁用session，登录信息仅保存在token中(若不禁用session，生成的token将变得无意义，因为这时请求头不携带token也能从session中获取到对应的登录信息)
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // 配置自己的userDetailsService和passwordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

        // 通过下面这种方式配置与上面效果一样
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder);
//        auth.authenticationProvider(authenticationProvider);
    }
}
