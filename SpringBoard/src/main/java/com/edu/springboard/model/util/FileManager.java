package com.edu.springboard.model.util;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.edu.springboard.model.Gallery;

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
	public void saveFiles(Gallery gallery, String path) {
		MultipartFile[] photo = gallery.getPhoto();
		
		try {
			for(int i = 0;i<photo.length;i++) {
				photo[i].transferTo(new File(path + createFileName(photo[i].getOriginalFilename())));
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
