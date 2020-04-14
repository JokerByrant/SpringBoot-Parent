package com.sxh.pojo;

import lombok.Data;

/**
 * @author sxh
 * @date 2020/4/14
 */
@Data
public class UserPojo {
    private Integer id;
    
    private String username;
    
    private String userSex;

    public UserPojo(Integer id, String username, String userSex) {
        this.id = id;
        this.username = username;
        this.userSex = userSex;
    }

    public UserPojo() {
    }
}
