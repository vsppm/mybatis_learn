package com.mclean.core;

import com.mclean.bean.AddressBean;
import com.mclean.bean.UserBean;
import com.mclean.dao.IAddressMapper;
import com.mclean.dao.IUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title:
 * @author: IMUKL
 * @Description:
 * @date: 2019/3/4 9:51
 */
public class UserDbMapperTest {
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

    public static void main(String args[]) {
        String resource = "mybatis-conf.xml";
        UserDbMapperTest userMapper = new UserDbMapperTest();

        // 读取mybatis全局配置文件
        try {
            userMapper.readMybtisConf(resource);
        } catch (IOException e) {
            System.out.println(e);
        }

        // 从获取sqlSessionFactory获取session
        try {
            // SqlSession为 非线程安全
            SqlSession session = userMapper.getSqlSession();
            try {
                // mapper类与xml mapper文件如何关联呢? 通过namspace标签,此处Namespace必须是mapper类的全路径
                IUserMapper mapper = session.getMapper(IUserMapper.class);
                // 此处打印发现mapper为org.apache.ibatis.binding.MapperProxy,即mybatis会为你提供一个代理对象,
                // 由代理对象依据sql映射mapper xml文件为你提供增删改查

                UserBean ub = new UserBean();
                ub.setId(3);
                ub.setName("mclean");
                ub.setEmail("mclean@sina.com");
                ub.setNickName("51mclean");

                System.out.println(mapper);
                UserBean user = mapper.selectUser(1);
                // mapper.insertUser(ub);
                // mapper.updateUser(ub);
                // session.commit();
                System.out.println(user);

                // 多参数查询
                Map<String, Object> map = new HashMap<>();
                map.put("id",3);
                map.put("name", "mclean");
                UserBean user2 =  mapper.selectUserBy(map);
                System.out.println(user2);

                UserBean user3 =  mapper.selectUserAndDept(1);
                System.out.println(user3);

                // 当调用具有关联关系的类时,可以采用延迟加载的方式,mybatis-config.xml使用设置:
                // <setting name="lazyLoadingEnabled" value="true"/>
                // <!--当开启时，任何方法的调用都会加载该对象的所有属性。否则，每个属性会按需加载-->
                // <setting name="aggressiveLazyLoading" value="false"></setting>
                // 当调用user4.getName()时,mybatis不会执行查询部门信息的sql
                UserBean user4 =  mapper.selectUserStep(1);
                System.out.println(user4.getName());


                IAddressMapper mapper2 = session.getMapper(IAddressMapper.class);
                List<AddressBean> list_address= mapper2.getAddressByUserId(1);
                System.out.println(list_address);


                UserBean user5 =  mapper.selectUserFullInfoBySqlStep(1);
                System.out.println(user5.getName());
                System.out.println(user5.getAddresses());
            } finally {
                session.close();
            }
        } catch (NullPointerException e) {
            System.out.println(e);
        }

    }
}
