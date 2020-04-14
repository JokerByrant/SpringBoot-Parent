package com.sxh.eurekaclient.pojo;

import lombok.Data;

/**
 * @author sxh
 * @date 2020/3/13
 */
@Data
public class Result<T> {
    private T data;

    private int code;

    private String message;

    public Result(T data) {
        this.data = data;
    }
}
