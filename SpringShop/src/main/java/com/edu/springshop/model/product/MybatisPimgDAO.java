package com.edu.springshop.model.product;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.springshop.domain.Pimg;
import com.edu.springshop.exception.PimgException;

@Repository
public class MybatisPimgDAO implements PimgDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<Pimg> selectAll() {
		return sqlSessionTemplate.selectList("Pimg.selectAll");
	}

	public Pimg select(int pimg_idx) {
		return sqlSessionTemplate.selectOne("Pimg.select", pimg_idx);
	}

	public List<Pimg> selectAllByFK(int product_idx) {
		return sqlSessionTemplate.selectList("Pimg.selectAllByFK", product_idx);
	}

	public void insert(Pimg pimg) throws PimgException{
		int result = sqlSessionTemplate.insert("Pimg.insert", pimg);
		if(result < 1) {
			throw new PimgException("이미지 등록 실패");
		}
	}

	public void delete(int pimg_idx) throws PimgException{
		int result = sqlSessionTemplate.delete("Pimg.delete", pimg_idx);
		if(result < 1) {
			throw new PimgException("이미지 삭제 실패");
		}
	}

}
