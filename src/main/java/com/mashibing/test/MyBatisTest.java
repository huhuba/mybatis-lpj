package com.mashibing.test;

import com.mashibing.bean.Emp;
import com.mashibing.dao.EmpDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {
  @Test
  public void test02() {
    // 根据全局配置文件创建出SqlSessionFactory
    // SqlSessionFactory:负责创建SqlSession对象的工厂
    // SqlSession:表示跟数据库建议的一次会话
    String resource = "mybatis-config.xml";
    InputStream inputStream = null;
    try {
      inputStream = Resources.getResourceAsStream(resource);
    } catch (IOException e) {
      e.printStackTrace();
    }
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//返回SqlSessionFactory对象
    // 获取数据库的会话,创建出数据库连接的会话对象（事务工厂，事务对象，执行器，如果有插件的话会进行插件的解析）
    SqlSession sqlSession = sqlSessionFactory.openSession();//返回DefaultSqlSession对象
    Emp empByEmpno = null;
    try {
      // 获取要调用的接口类,创建出对应的mapper的动态代理对象（mapperRegistry.knownMapper）
      EmpDao mapper = sqlSession.getMapper(EmpDao.class);//返回Mapper接口的代理对象，MapperProxy@5b11a194
      // 调用方法开始执行
      empByEmpno = mapper.findEmpByEmpnoAndEname(7369,"SMITH");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      sqlSession.close();
    }
    System.out.println(empByEmpno);
  }
}
