package gui.spring.context;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import gui.school.Student;
import gui.view.JoinForm;

//어플리케이션에서 사용할 모든 객체들(빈=bean)을 앞으로는 개발자가
//자바 코드 내에서 직접 New 하지 않고, 외부의 조력자인 즉 빈컨테이너가
//빈을 생성하여 주입하는 방식으로 개발해야, 유지보수성이 높아진다..
//어플리케이션에서 사용될 모든 빈들의 명단을 작성해 놓으면 스프링이 알아서
//객체를 생성해주고 주입도 해준다..
public class AppContext {
	
	public AppContext() {
		//이 객체는 지정된 xml을 읽어들여, 전체 어플리케이션에서 사용될
		//Bean 들을 관리해주는 컨테이너 객체이다
		ClassPathXmlApplicationContext context;
		context = new ClassPathXmlApplicationContext("gui/spring/context/config.xml");
		
		//context가 컨테이너에 모아놓은 빈들 중 필요한게
		//있다면 개발자는 꺼내올 수 있다
//		JoinForm joinForm = (JoinForm)context.getBean("joinForm");
//		joinForm.create();
		
		//학생꺼내서 사용하기
		Student student = (Student)context.getBean("student");
		student.goSchool();
		student.startStudy();
		student.endStudy();
		student.goHome();
		
		context.close();
	}
	
	public static void main(String[] args) {
		new AppContext();
	}
}
