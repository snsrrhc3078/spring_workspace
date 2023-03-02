package com.edu.springshop.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.edu.springshop.exception.AdminException;

public class AdminLoginCheckAdvise {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Object sessionCheck(ProceedingJoinPoint joinPoint) throws AdminException, Throwable{
		Object result = null; //proceed 후 반환되는 객체를 담기위한 변수 (ModelAndView 혹은 다른객체인지 알 수 없으므로)
		/*
		 	ProceedingJoinPoint
		 	주로 타겟에 대한 정보를 가지고있음
		 	원래 호출하려면 타겟 객체가 무엇인지, 그 타겟 객체의 무슨 메서드를
		 	호출하려고 했는지, 그 타겟 메서드의 매개변수는 무엇인지
		 */
		Class targetClass = joinPoint.getTarget().getClass();
		Object[] args = joinPoint.getArgs();
		logger.info("호출하려는 타겟 클래스는 :" +  targetClass.getName());
		logger.info("호출하려는 타겟 메서드의 매개변수의 수는 :" +  args.length);
		
		//현재 이 요청에 대해 session에 값이 들어있는지 여부를 조사해보자
		//원래 호출하려했던 메서드의 매개변수 정보를 현재 대리객체가 추출할 수 있으므로,
		//원래 호출혀라했던 메서드에는 HttpServletRequest가 명시되어 있어야 한다

		//타겟 메서드에서 HttpServletRequest 추출하여 session에 관리자 객체가 들어있는지
		//체크해보자
		HttpServletRequest request = null;
		HttpSession session = null;
		for(int i = 0;i<args.length;i++) {
			if(args[i] instanceof HttpServletRequest) {
				request = (HttpServletRequest)args[i];
				break;
			}
		}
		
		//로그인을 체크해야되는 경우와, 그냥 보내야 하는 경우를 나눈다
		//로그인이 필요한 서비스에서만 아래의 코드들이 수행되어야 한다
		session = request.getSession();
		String uri = request.getRequestURI(); //
		if(
				!(
				uri.equals("/admin/loginform") || //로그인폼 요청시 제외
				uri.equals("/admin/rest/login/admin") || //비동기 로그인 요청시 제외
				uri.equals("/admin/login")//동기방식으로 로그인 요청으로 들어올 때 제외
				)
				
		) { //로그인 필요 없는 경우인지 확인
			//로그인 체크
			if(session.getAttribute("admin")==null) {
				logger.info("AOP에 의해 로그인 예외 발생 : 세션 없음");
				throw new AdminException("로그인이 필요한 서비스입니다");
			}
		}
		
		
		return joinPoint.proceed();
	}
}
