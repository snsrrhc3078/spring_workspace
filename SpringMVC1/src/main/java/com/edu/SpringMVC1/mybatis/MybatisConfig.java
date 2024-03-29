package com.edu.SpringMVC1.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//세션을 얻기 위한 팩토리 구하기
public class MybatisConfig {
	private static MybatisConfig instance;
	private SqlSessionFactory factory;
	
	
	private MybatisConfig() {
		String resource = "com/edu/SpringMVC1/mybatis/config.xml";
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static MybatisConfig getInstance() {
		if(instance == null) {
			instance = new MybatisConfig();
		}
		return instance;
	}
	
	//세션얻기
	public SqlSession getSqlSession() {
		return factory.openSession();
	}
	
	//세션반납
	public void release(SqlSession sqlSession) {
		if(sqlSession!=null) {
			sqlSession.close();
		}
	}
}
