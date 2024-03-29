package com.edu.SpringMVC1.model.emp;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.SpringMVC1.domain.Emp;
import com.edu.SpringMVC1.exception.DeptException;
import com.edu.SpringMVC1.exception.EmpException;
import com.edu.SpringMVC1.mybatis.MybatisConfig;

import lombok.Setter;


/*
 	이 객체는 모델 파트에서의 서비스 역할을 수행한다
 	만일 서비스의 존재가 없을 경우, 컨트롤러가 너무 세부적인 model
 	영역의 업무를 수행하게 된다.
 	또한 트랜잭션 상황에서 각각의 DAO 들이 업무수행이 성공했는지 여부를 판단하여
 	트랜잭션을 커밋할지 롤백할지 결정을 짓는 역할을 함
 	
 	주의) 직접 일하지 않고 각종 모델 영역의 객체들에게 일을 시킨다
 */
/*
 	어노테이션을 붙여놓으면 , 스프링 컴포넌트로 검색되어 자동 검색에
 	걸리게 되어 인스턴스를 올려준다
 */
@Service
@Setter
public class EmpServiceImpm implements EmpService{
	@Autowired private DeptDAO deptDAO;
	@Autowired private EmpDAO empDAO;
	MybatisConfig config = MybatisConfig.getInstance();
	
	
	
	//사원등록
	public void regist(Emp emp) {
		//세션 얻어와서 배분하기
		//3단계
		SqlSession sqlSession = config.getSqlSession();
		deptDAO.setSqlSession(sqlSession);
		empDAO.setSqlSession(sqlSession);
		
		try {
			deptDAO.insert(emp.getDept());
			empDAO.insert(emp);
			sqlSession.commit();
		} catch (DeptException e) {
			sqlSession.rollback();
			e.printStackTrace();
		} catch (EmpException e) {
			sqlSession.rollback();
			e.printStackTrace();
		}finally {
			config.release(sqlSession);
		}
	}
	
	public List selectAll() {
		SqlSession sqlSession = config.getSqlSession();
		empDAO.setSqlSession(sqlSession);
		List list = empDAO.selectAll();
		config.release(sqlSession);
		return list;
	}
	
	public void deleteSelected(String[] empnoList) {
		SqlSession sqlSession = config.getSqlSession();
		empDAO.setSqlSession(sqlSession);
		deptDAO.setSqlSession(sqlSession);
		
		try {
			for(int i =0;i<empnoList.length;i++) {
				int empno = Integer.parseInt(empnoList[i]);
				Emp emp = empDAO.select(empno);
				empDAO.delete(emp.getEmpno());
				deptDAO.delete(emp.getDept().getDeptno());
			}
			sqlSession.commit();
		} catch (DeptException e) {
			sqlSession.rollback();
			e.printStackTrace();
		} catch (EmpException e) {
			sqlSession.rollback();
			e.printStackTrace();
		}finally {
			config.release(sqlSession);
		}
	}
}
