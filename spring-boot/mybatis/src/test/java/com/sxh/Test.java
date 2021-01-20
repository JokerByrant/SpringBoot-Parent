package com.sxh;

import com.sxh.dao.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author sxh
 * @date 2021/1/20
 */
public class Test {
    @org.junit.Test
    public void insert() {
        // mybatis链接sql配置
        InputStream stream = Test.class.getClassLoader().getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession session = factory.openSession(true);
        try {
            // 扫描映射层文件
            UserMapper userMapper = session.getMapper(UserMapper.class);

            final List<Map<String, Object>> map = userMapper.selectAll();
            System.out.println(map);
        } finally {
            session.close();
        }
    }
}
