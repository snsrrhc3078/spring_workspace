package com.edu.springshop.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.edu.springshop.domain.Product;
import com.edu.springshop.exception.UploadException;

import lombok.Setter;

@Component
@Setter
public class FileManager {
	
	/**
	 * 
	 * @param filename 확장자를 얻기 위한 파일명
	 * @return
	 */
	public String createFileName(String filename) {
		String ext = filename.substring(filename.lastIndexOf("."), filename.length());
		String result = System.currentTimeMillis() + ext;
		return result;
	}
	
	//지정된 디렉토리로 파일 저장 
	/**
	 * MultipartFile을 실제 경로에 파일명을 현재시간으로 해서 저장하는 메서드
	 * 
	 * @param product 지정된 파일 저장
	 * @param path 저장될 디렉토리 위치
	 */
	public List saveFiles(MultipartFile[] files, String path) throws UploadException{
		List<String> filenameList = new ArrayList();
		try {
			for(MultipartFile file : files) {
				String filename = createFileName(file.getOriginalFilename());
				filenameList.add(filename);
				file.transferTo(new File(path + filename));
				Thread.sleep(10);
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new UploadException("업로드 실패", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new UploadException("업로드 실패", e);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return filenameList;
	}
}
