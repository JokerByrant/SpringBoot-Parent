package com.sxh.feign.client;

import com.sxh.message.ResponseMessage;
import org.springframework.stereotype.Component;

/**
 * @author sxh
 * @date 2020/4/3
 */
@Component
public class UserClientFallBackService implements UserClient {

    @Override
    public ResponseMessage getUser(Integer id) {
        return new ResponseMessage("调用失败，服务被降级！");
    }
}
