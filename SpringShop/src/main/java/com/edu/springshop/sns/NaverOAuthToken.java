package com.edu.springshop.sns;

import lombok.Data;

//구글 서버에서 전송받은 json 문자열을 자바의 객체로 담아놓기 위한 목적
@Data
public class NaverOAuthToken {
	private String access_token;
	private String refresh_token;
	private String token_type;
	private int expires_in;
}
