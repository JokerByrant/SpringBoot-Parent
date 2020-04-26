package com.sxh.util;

import lombok.Data;

import java.util.Date;

/**
 * @author sxh
 * @date 2020/4/26
 */
@Data
public class Message {
    private String username;
    
    private Date sentTime;
    
    private String msg;

    public Message(String username, Date sentTime, String msg) {
        this.username = username;
        this.sentTime = sentTime;
        this.msg = msg;
    }
}
