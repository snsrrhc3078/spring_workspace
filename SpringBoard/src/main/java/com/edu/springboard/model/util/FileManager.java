package com.edu.springboard.model.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.edu.springboard.domain.Gallery;
import com.edu.springboard.domain.Photo;
import com.edu.springboard.exception.FileUploadException;

@Component
public class FileManager {
	
	//확장자 구하기
	public String getExt(String path) {
		String ext = path.substring(path.lastIndexOf(".")+1, path.length());
		return ext;
	}
	
	//파일명 얻기
	public String createFileName(String path) {
		String filename = System.currentTimeMillis() + "." + getExt(path);
		return filename;
	}
	
	//서버에 저장
	/**
	 * 
	 * @param gallery 저장할 MultipartFile 배열을 가지고 있는 Gallery DTO
	 * @param path 서버의 하드디스크 풀 경로
	 */
	public void saveFiles(Gallery gallery, String path) throws FileUploadException{
		MultipartFile[] photo = gallery.getPhoto();
		List<Photo> photoList = new ArrayList<Photo>();
		try {
			for(int i = 0;i<photo.length;i++) {
				
				String filename = createFileName(photo[i].getOriginalFilename());
				photo[i].transferTo(new File(path + filename));
				
				Photo pt = new Photo();
				pt.setFilename(filename);
				pt.setGallery(gallery);
				photoList.add(pt);
				System.out.println("filename : " + filename);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new FileUploadException("서버에 파일 저장 실패", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileUploadException("서버에 파일 저장 실패", e);
		}
		
		gallery.setPhotoList(photoList);
	}
}
