package com.edu.SpringMVC1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.SpringMVC1.domain.Emp;
import com.edu.SpringMVC1.model.emp.EmpService;

import lombok.Setter;

@Controller
@Setter
public class EmpController {
	
	//상위 자료형을 보유해야, 즉 DI를 구현해야 유지보수성이 높아짐..결합도 낮아짐
	
	//자동엮음 기능에 의해, empService의 인스턴스가 자동으로 주입된다..
	@Autowired
	private EmpService empService;
	
	@RequestMapping("/emps")
	public String getList(Model model) {
		
		//3단계
		List<Emp> empList = empService.selectAll();
		
		//4단계 결과저장
		//1) 직접 request 객체를 이용하는방법
		//2) 간접적으로 request 객체를 이용하는 방법
		model.addAttribute("empList", empList);
		//개발자가 redirect 키워드를 명시하지 않으면 디폴드가 foward 이다
		return "emp/list";
	}
}
