package com.edu.springboard.model.gallery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.edu.springboard.model.Gallery;
import com.edu.springboard.model.util.FileManager;

@Service
public class GalleryServiceImpl implements GalleryService{
	@Autowired
	@Qualifier("mybatisGalleryDAO")
	private GalleryDAO galleryDAO;
	
	@Autowired
	@Qualifier("mybatisPhotoDAO")
	private PhotoDAO photoDAO;
	
	@Autowired
	private FileManager fileManager;
	
	public List selectAll() {
		return null;
	}

	public Gallery select(int gallery_idx) {
		return null;
	}

	public void regist(Gallery gallery, String path) {
		//gallery dao 시키기

		
		//photo dao 시키기
		
		//fileManager 시키기
		
	}

	public void update(Gallery gallery) {
		
	}

	public void delete(Gallery gallery) {
		
	}

}
