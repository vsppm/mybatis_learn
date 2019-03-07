package com.mclean.core;

import com.mclean.bean.UserBean;
import com.mclean.dao.IUserCacheMapper;
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
 * @date: 2019/3/7 15:49
 */
public class UserDbCacheTest {
    SqlSessionFactory sqlSessionFactory = null;

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void readMybtisConf(String resource) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    public SqlSession getSqlSession() {
        SqlSession session = sqlSessionFactory.openSession();
        return session;
    }

    /*
    * 两级缓存
    *
    * 本地缓存
    * --> 与数据库同一次会话期间查询到的数据放到本地缓存,以后在获取直接从缓存中提取数据
    *
    * 一级缓存失效情况分析:
    * 1.非同一个SqlSession对象
    * 2.同一个SqlSession对象,查询条件不同
    * 3.同一个SqlSession对象,两次查询之间,有其他sql的执行操作
    * 4.同一个SqlSession对象利用clearCache清理缓存
    *
    * 全局缓存
    * */
    public static void main(String args[]) {
        String resource = "mybatis-conf.xml";
        UserDbCacheTest cacheTest = new UserDbCacheTest();

        // 读取mybatis全局配置文件
        try {
            cacheTest.readMybtisConf(resource);
        } catch (IOException e) {
            System.out.println(e);
        }

        // 从获取sqlSessionFactory获取session
        try {
            // SqlSession为 非线程安全
            SqlSession session = cacheTest.getSqlSession();
            SqlSession session2 = cacheTest.getSqlSession();

            try {
                // session
                IUserCacheMapper cacheMapper = session.getMapper(IUserCacheMapper.class);
                UserBean user = cacheMapper.selectCacheUser(1);
                System.out.println(user);

                // 可以看到sql只是在第一次查询的时候执行了,同一session第二次查询,mybatis直接从缓存提取数据
                /*
                * DEBUG [main] - Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@cc285f4]
                * DEBUG [main] - ==>  Preparing: select u.id id,u.name name,u.email email, u.nick_name nick_name, d.id dept_id, d.dept_name dept_name from user u,dept d where u.dept_id=d.id and u.id=?
                * DEBUG [main] - ==> Parameters: 1(Integer)
                * DEBUG [main] - <==      Total: 1
                * UserBean{id=1, name='imukl', email='imukl@sina.com', nickName='xutianyou', dept=DeptBean{id=1, deptName='研发部'}, addresses=null}
                * UserBean{id=1, name='imukl', email='imukl@sina.com', nickName='xutianyou', dept=DeptBean{id=1, deptName='研发部'}, addresses=null}
                * */
                UserBean user2 = cacheMapper.selectCacheUser(1);
                System.out.println(user2);
                // session两次查询数据对象比较
                System.out.println(user==user2); // 打印为true


                // session2
                IUserCacheMapper cacheMapper2 = session2.getMapper(IUserCacheMapper.class);
                UserBean user3 = cacheMapper2.selectCacheUser(1);
                System.out.println(user3);

                UserBean user4 = cacheMapper2.selectCacheUser(1);
                System.out.println(user4);
                // session2两次查询数据对象比较
                System.out.println(user3==user4); // 打印为true

                // session与session2数据对象比较
                System.out.println(user==user3); // 打印为false
            } finally {
                session.close();
                session2.close();
            }
        } catch (NullPointerException e) {
            System.out.println(e);
        }

    }
}
