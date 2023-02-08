package com.edu.SpringMVC1.model.emp;

import java.util.List;

import com.edu.SpringMVC1.domain.Emp;

//컨트롤러가 사용할 이 객체는 DI를 적용시키기 위해 인터페이스로 선언한다
//이유는 의존성을 약화시키기 위함이다

public interface EmpService {
	public void regist(Emp emp);
	public List selectAll();
	public void deleteSelected(String[] empList);
}
