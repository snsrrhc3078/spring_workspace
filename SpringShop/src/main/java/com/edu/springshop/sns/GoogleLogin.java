package com.edu.springshop.sns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;

import lombok.Data;

@Data
public class GoogleLogin {
	//스프링 빈 설정파일에 등록한 구글 전용 빈을 가져와서 개발하자
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	
	@Autowired
	OAuth2Parameters oAuth2Parameters;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public String handle() {
		//인증화면과 관련된 URL 만들어내기
		OAuth2Operations operations = googleConnectionFactory.getOAuthOperations();
		String url = operations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, oAuth2Parameters);
		logger.info("인증에 사용할 url은 :\n" + url);
		return url;
	}
}
