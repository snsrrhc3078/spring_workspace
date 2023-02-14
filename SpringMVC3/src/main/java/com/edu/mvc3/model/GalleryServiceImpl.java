package com.edu.mvc3.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.edu.mvc3.domain.Gallery;
import com.edu.mvc3.model.util.FileManager;
import com.edu.mvc3.mybatis.MybatisConfig;

public class GalleryServiceImpl implements GalleryService{
	//파일 저장 + DB insert
	@Autowired private GalleryDAO galleryDAO;
	@Autowired private MybatisConfig config;
	@Autowired private FileManager fileManager;
	
	@Override
	public List SelectAll() {
		return null;
	}

	@Override
	public Gallery select(int gallery_idx) {
		return null;
	}

	@Override
	public void regist(Gallery gallery) {
		//FileManager
		
		//GalleryDAO
	}

	@Override
	public void update(Gallery gallery) {
		
	}

	@Override
	public void delete(int gallery_idx) {
		
	}

}
