package com.edu.springboard.model.reboard;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.springboard.domain.ReBoard;
import com.edu.springboard.exception.ReBoardException;

@Repository //자식을 올리면 부모가 같이 올라가기 때문에
public class MybatisReBoardDAO implements ReBoardDAO{

	/*Autowired의 역할:
	 * xml로 표현했다면 아래와 같이 했었어야 했다. 이제는 @를 사용하기로 했으니 골뱅이 태그만 잘 사용해주면됨
	 * <bean class="MybatisReBoardDAO">
	 * 		<property name="sqlSessionTemplate" ref="sqlSessionTemplate"/>
	 * </bean>
	 * */
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("ReBoard.selectAll");
	}

	@Override
	public ReBoard select(int reboard_idx) {
		return sqlSessionTemplate.selectOne("ReBoard.select", reboard_idx);
	}

	@Override
	public void insert(ReBoard reboard) throws ReBoardException{
		int result=sqlSessionTemplate.insert("ReBoard.insert", reboard);
		if(result<1) {
			throw new ReBoardException("원글 등록실패");
		}
	}

	@Override
	public void update(ReBoard reboard) throws ReBoardException{
		int result=sqlSessionTemplate.update("ReBoard.update", reboard);
		if(result<1) {
			throw new ReBoardException("글 수정실패");
		}
	}

	@Override
	public void delete(int reboard_idx) throws ReBoardException{
		int result=sqlSessionTemplate.delete("ReBoard.delete", reboard_idx);
		if(result<1) {
			throw new ReBoardException("글 삭제실패");
		}
	}

	//이거는 왜 Exception처리를 안해주어도 되나?
	@Override
	public void updateStep(ReBoard reboard) {
		sqlSessionTemplate.update("ReBoard.updateStep", reboard);
	}

	@Override
	public void reply(ReBoard reboard) throws ReBoardException{
		int result=sqlSessionTemplate.insert("ReBoard.reply", reboard);
		if(result<1) {
			throw new ReBoardException("답글 등록실패");
		}
	}

}
