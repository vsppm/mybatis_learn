package com.mclean.core;

import com.mclean.bean.UserBean;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Title:
 * @author: IMUKL
 * @Description:
 * @date: 2019/3/1 10:07
 */
public class UserDbTest {

    SqlSessionFactory sqlSessionFactory = null;

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void readMybtisConf(String resource) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    public SqlSession getSqlSession(){
        SqlSession session = sqlSessionFactory.openSession();
        return session;
    }

    public static void main(String args[]){
        String resource = "mybatis-conf.xml";
        UserDbTest userDbTest = new UserDbTest();

        // 读取mybatis配置文件
        try {
            userDbTest.readMybtisConf(resource);
        }
        catch (IOException e){
            System.out.println(e);
        }

        // 从获取sqlSessionFactory获取session
        try {
            SqlSession session = userDbTest.getSqlSession();
            try {
                // 这是以前的mybatis的查询方式
                UserBean user = (UserBean) session.selectOne("com.mclean.mapper.UserMapper.selectUser", 1);
                System.out.print(user);
            } finally {
                session.close();
            }
        }
        catch (NullPointerException e){
            System.out.println(e);
        }


    }
}
