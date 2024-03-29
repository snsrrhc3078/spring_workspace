package com.edu.mvc3.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edu.mvc3.domain.Gallery;
import com.edu.mvc3.exception.GalleryException;

import lombok.Setter;

//Component-scan 에 의해 검색되어 자동으로 인스턴스 생성됨
@Repository
@Setter
public class MybatisGalleryDAO implements GalleryDAO {
	// 스프링이 주도하여 주입시키지 못하고, 아직은 우리가 GalleryService에서
	// 주입시켜야 한다.. 다음주 스프링이 지원하는 DB 연동기술을 배우게 되면 이 문제가 해결됨
	SqlSession sqlSession;

	public List SelectAll() {
		return sqlSession.selectList("Gallery.selectAll");
	}

	public Gallery select(int gallery_idx) {
		return sqlSession.selectOne("Gallery.select", gallery_idx);
	}

	public void insert(Gallery gallery) throws GalleryException{
		int result = sqlSession.insert("Gallery.insert", gallery);
		if (result < 1) {
			throw new GalleryException(" 실패");
		}
	}

	public void update(Gallery gallery) throws GalleryException{
		int result = sqlSession.update("Gallery.update", gallery);
		if (result < 1) {
			throw new GalleryException(" 실패");
		}

	}

	public void delete(int gallery_idx) throws GalleryException{
		int result = sqlSession.delete("Gallery.delete", gallery_idx);
		if(result<1) {
			throw new GalleryException(" 실패");
		}
					
	}

}
