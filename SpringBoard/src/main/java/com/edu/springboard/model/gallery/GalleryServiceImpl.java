package com.edu.springboard.model.gallery;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.edu.springboard.domain.Gallery;
import com.edu.springboard.domain.Photo;
import com.edu.springboard.exception.FileUploadException;
import com.edu.springboard.exception.GalleryException;
import com.edu.springboard.exception.PhotoException;
import com.edu.springboard.model.util.FileManager;

@Service
public class GalleryServiceImpl implements GalleryService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("jdbcGalleryDAO")
	private GalleryDAO galleryDAO;
	
	@Autowired
	@Qualifier("jdbcPhotoDAO")
	private PhotoDAO photoDAO;
	
	@Autowired
	private FileManager fileManager;
	
	public List selectAll() {
		return galleryDAO.selectAll();
	}

	public Gallery select(int gallery_idx) {
		return galleryDAO.select(gallery_idx);
	}

	public void regist(Gallery gallery, String path) throws FileUploadException, GalleryException, PhotoException{
		//fileManager 시키기
		fileManager.saveFiles(gallery, path);
		//gallery dao 시키기
		//mybatis의 selectkey에 의해 insert 문 수행 이후에는 gallery DTO
		//의 gallery_idx값이 이미 채워져있을것임
		logger.info("insert 이전의  Gallery_idx값 : "+gallery.getGallery_idx());
		galleryDAO.insert(gallery);
		logger.info("insert 이후의  Gallery_idx값 : "+gallery.getGallery_idx());
		
		//photo dao 시키기
		//파일매니저에 의해 채워진 photoList 활용하기
		List<Photo> photoList = gallery.getPhotoList();
		for(int i =0;i<photoList.size();i++) {
			photoDAO.insert(photoList.get(i));
		}
		
	}

	public void update(Gallery gallery) {
		
	}

	public void delete(Gallery gallery) {
		
	}

}
