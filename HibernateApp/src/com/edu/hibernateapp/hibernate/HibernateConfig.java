package com.edu.hibernateapp.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {
	private static HibernateConfig instance;
	
	private SessionFactory sessionFactory;
	String res = "com/edu/hibernateapp/hibernate/hibernate.cfg.xml";
	
	private HibernateConfig() {
		//설정 xml을 읽기
		Configuration config = new Configuration().configure(res);
		StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
		sb.applySettings(config.getProperties());
		
		StandardServiceRegistry registry=sb.build();
		sessionFactory = config.buildSessionFactory(registry);
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void release(SessionFactory sessionFactory) {
		if(sessionFactory!=null) {
			sessionFactory.close();
		}
	}
	
	public static HibernateConfig getInstance() {
		if(instance == null) instance = new HibernateConfig();
		return instance;
	}
}
