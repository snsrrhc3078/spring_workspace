package com.edu.mvc3.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfig {
	private static MybatisConfig instance;
	private SqlSessionFactory factory;

	private MybatisConfig() {
		String resource = "com/edu/mvc3/mybatis/config.xml";
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static public MybatisConfig getInstance() {
		if (instance == null) {
			instance = new MybatisConfig();
		}
		return instance;
	}

	public SqlSession getSqlSession() {
		return factory.openSession();
	}

	public void release(SqlSession sqlSession) {
		if (sqlSession != null) {
			sqlSession.close();
		}
	}
}
