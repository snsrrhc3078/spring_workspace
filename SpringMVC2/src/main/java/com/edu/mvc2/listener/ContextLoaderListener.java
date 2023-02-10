package com.edu.mvc2.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextLoaderListener implements ServletContextListener{
	/*
 	jsp에서의 application 내장객체
 	application .getRealPath()에 이용했었음
 	javaee 기반의 서버의 메모리엣 ㅓ데이터를 개발자가 심을 수 있는 객체가 3가지 있다
 	request, session, application
	 */
	ServletContext context;
	
	ApplicationContext applicationContext; 
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("서버시작감지");
		System.out.println(sce.getServletContext().getInitParameter("contextConfigLocation"));;
		applicationContext = new ClassPathXmlApplicationContext(sce.getServletContext().getInitParameter("contextConfigLocation"));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("서버종료감지");
	}

}
