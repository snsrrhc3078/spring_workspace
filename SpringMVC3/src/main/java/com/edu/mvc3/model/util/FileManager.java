package com.edu.mvc3.model.util;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.edu.mvc3.exception.UploadException;

//Component scan에 의해 메모리에 자동생성
@Component
public class FileManager {
	
	//파일명 생성하기
	public String createFileName(String fileName) {
		return System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."),  fileName.length());
	}
	
	//서버에 저장
	public String saveFile(MultipartFile file, String dest) throws UploadException{
		String fileName = file.getOriginalFilename();
		fileName = createFileName(fileName);
		try {
			file.transferTo(new File(dest + fileName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new UploadException("업로드 실패", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new UploadException("업로드 실패", e);
		}
		return fileName;
	}
}
