package com.edu.mvc3.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		String dataPath =  application.getInitParameter("dataPath");
		application.setAttribute("dataPath", application.getRealPath(dataPath));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
}
