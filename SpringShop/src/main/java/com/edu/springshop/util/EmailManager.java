package com.edu.springshop.util;

import java.io.UnsupportedEncodingException;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import com.edu.springshop.exception.EmailException;

public class EmailManager {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void send(String email) throws EmailException{
		//이메일 정보를 담을 메시지 객체를 생성
		//이때 MimeMessage 객체를 사용
		MimeMessage msg = javaMailSender.createMimeMessage();
		
		//받을 사람 정보 설정
		//여러사람한테 보낼경우 s를 붙인다 ex. addRecipients
		try {
			msg.addRecipient(RecipientType.TO, new InternetAddress(email));
			
			//보내는 사람 정보 설정
			msg.addFrom(new InternetAddress[] {
					new InternetAddress("snsrrhc3078@gmail.com", "webmaster")
			});
			
			//메일 제목
			msg.setSubject("쇼핑몰 회원가입 완료");
			//메일 내용
			msg.setText("회원가입을 축하 드립니다", "UTF-8");
			
			//메일 전송
			javaMailSender.send(msg);
		} catch (AddressException e) {
			e.printStackTrace();
			throw new EmailException("받는사람 메일정보 설정 실패", e);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new EmailException("받는사람 메일정보 설정 실패", e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new EmailException("보내는사람 메일정보 설정 실패", e);
		}
	}
}
