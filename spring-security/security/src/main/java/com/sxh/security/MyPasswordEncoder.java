package com.sxh.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 重写密码解析类
 *
 * @author sxh
 * @date 2020/3/18
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    /**
     * 验证输入的密码是否匹配
     *
     * @param charSequence    正确的密码
     * @param encodedPassword 用户输入的密码
     * @return
     */
    @Override
    public boolean matches(CharSequence charSequence, String encodedPassword) {
        return encode(charSequence).equals(encodedPassword);
    }
}
