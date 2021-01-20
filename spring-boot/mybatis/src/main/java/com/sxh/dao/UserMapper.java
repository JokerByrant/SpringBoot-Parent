package com.sxh.dao;

import java.util.List;
import java.util.Map;

/**
 * @author sxh
 * @date 2021/1/20
 */
public interface UserMapper {
    List<Map<String, Object>> selectAll();
}
