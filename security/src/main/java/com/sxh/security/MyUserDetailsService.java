package com.sxh.security;

import com.sxh.entity.UserInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
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
        myUserDetails.setUsername(username);
        myUserDetails.setPassword("123456");

        return myUserDetails;
    }
}
