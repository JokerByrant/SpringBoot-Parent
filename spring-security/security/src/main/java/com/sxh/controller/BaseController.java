package com.sxh.controller;

import com.sxh.security.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 控制器基本类
 *
 * @author sxh
 * @date 2020/3/20
 */
public class BaseController {

    public String getCurrentUsername() {
        String username = "";
        if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
            Object o = SecurityContextHolder.getContext().getAuthentication();
            MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            username = userDetails.getUsername();
        }
        return username;
    }
}
