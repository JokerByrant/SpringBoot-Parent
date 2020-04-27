package com.sxh.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 返回用户信息
 * @author sxh
 * @date 2020/3/18
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new RuntimeException("用户名不能为空！");
        }
        MyUserDetails myUserDetails = new MyUserDetails();
        // 在这里设置用户信息
        myUserDetails.setUsername(username);
        myUserDetails.setPassword("123456");

        return myUserDetails;
    }
}
