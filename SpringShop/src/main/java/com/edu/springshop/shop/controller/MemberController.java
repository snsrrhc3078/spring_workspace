package com.edu.springshop.shop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.edu.springshop.domain.Member;
import com.edu.springshop.model.member.MemberService;
import com.edu.springshop.sns.GoogleLogin;
import com.edu.springshop.sns.GoogleOAuthToken;
import com.edu.springshop.sns.KakaoLogin;
import com.edu.springshop.sns.KakaoOAuthToken;
import com.edu.springshop.sns.NaverLogin;
import com.edu.springshop.sns.NaverOAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 	회원과 관련된 요청을 처리하는 하위 컨트롤러
 */
@Controller
public class MemberController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MemberService memberService;

	@Autowired
	private GoogleLogin googleLogin;

	@Autowired
	private KakaoLogin kakaoLogin;
	@Autowired
	private NaverLogin naverLogin;

	// 회원가입 폼 요청처리
	@GetMapping("/member/joinform")
	public ModelAndView getJoinForm(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("/shop/member/joinform");
		return mav;
	}

	// 회원가입 요청 처리
	@PostMapping("/member/regist")
	public ModelAndView regist(HttpServletRequest request, Member member) {

		// 3단계
		memberService.regist(member);

		ModelAndView mav = new ModelAndView("/shop/member/joinform");
		return mav;
	}

	@GetMapping("/member/loginform")
	public ModelAndView getLoginForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/shop/member/loginform");
		return mav;
	}

	// 상담 게시판 페이지 요청 처리
	@GetMapping("/member/chatform")
	public ModelAndView getChatForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/shop/member/chat");
		return mav;
	}

	@GetMapping("/member/authform/google")
	public ModelAndView getAuthForm(HttpServletRequest request) {
		// 사용자가 보게 될 인증화면에 대한 주소 구하기
		String url = "";

		ModelAndView mav = new ModelAndView("redirect:" + url);
		return mav;
	}

	// 구글 로그인 콜백
	@GetMapping("/sns/google/callback")
	public ModelAndView googleCallback(HttpServletRequest request, HttpSession session) {
		String code = (String) request.getParameter("code");
		logger.info("code : " + code);

		// 구글이 넘겨준 code 와 내 계정이 보유한 cid + secret을 조합하여
		// 구글 서버측에 token 발급을 요청해야 한다
		// 이때 우리 스프링 서버는 상대적으로 클라이언트가 된다
		// token은 회원정보에 접근할 수 있는 증명서 같은 개념

		/*
		 * 1. 토큰 취득을 위한 POST 헤더 구성하기
		 */
		String url = googleLogin.getToken_request_url();

		// body의 파라미터 구성하기 <파라미터명, 파라미터값>
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("code", code);
		params.add("client_id", googleLogin.getClient_id());
		params.add("client_secret", googleLogin.getClient_secret());
		params.add("redirect_uri", googleLogin.getRedirect_uri());
		params.add("grant_type", googleLogin.getGrant_type());

		// post 방식 헤더
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");

		// 머리와 몸 합치기
		HttpEntity httpEntity = new HttpEntity(params, headers);

		// 요청시도를 위한 객체 생성, 비동기방식의 요청을 위한 객체
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> entity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

		// 2. 토큰 요청 후 ResponseEntity 로부터 토큰 꺼내기
		String body = entity.getBody();
		logger.info("구글에서 넘겨받은 응답 정보 : " + body);

		// JSON으로 되어있는 문자열을 파싱하여 토큰을 가져오자
		ObjectMapper mapper = new ObjectMapper();
		GoogleOAuthToken oAuthToken = null;
		try {
			oAuthToken = mapper.readValue(body, GoogleOAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// 3. 토큰을 이용하여 회원정보에 접근
		// oAuthToken 안의 토큰을 이용하여 회원정보에 접근
		String userinfo_url = googleLogin.getUserinfo_url();
		logger.info("userinfo_url : " + userinfo_url);

		// get 방식 요청 구성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oAuthToken.getAccess_token()); // Bearer 뒤에 한칸 띄우고 token 넣어야 함
		HttpEntity entity2 = new HttpEntity(headers2);

		// 비동기객체를 이용한 GET 요청
		RestTemplate restTemplate2 = new RestTemplate();
		ResponseEntity<String> userEntity = restTemplate2.exchange(userinfo_url, HttpMethod.GET, entity2, String.class);
		String userBody = userEntity.getBody();
		logger.info("userBody : " + userBody);

		// 사용자 정보 추출하기
		HashMap<String, Object> userMap = null;
		try {
			userMap = mapper.readValue(userBody, HashMap.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		/*
		 * "id": "114720139729937030476", "email": "aa123@gmail.com", "verified_email":
		 * true, "name": "Mub", "given_name": "Mub", "picture":
		 * "https://lh3.googleusercontent.com/a/AGNmyxY-COmYzSx9lV7kVkqTjPSfYPw2EwtkbEdkKRpvjQ=s96-c",
		 * "locale": "ko"
		 */
		String id = (String) userMap.get("id");
		String email = (String) userMap.get("email");
		boolean verified_email = (Boolean) userMap.get("verified_email");
		String name = (String) userMap.get("name");
		String given_name = (String) userMap.get("given_name");
		String picture = (String) userMap.get("picture");
		String ko = (String) userMap.get("ko");

		if (false) {
			// 이미 db에 이 회원의 식별 고유 id 가 존재할 경우
			// 회원가입을 처리 (서비스의 regist) 세션에 담자

		} else {
			// 그렇지 않은 경우
			// 로그인 처리만 하자 (세션에 담자)

		}

		ModelAndView mav = new ModelAndView("redirect:/");
		return mav;
	}

	// 카카오 로그인 콜백
	@GetMapping("/sns/kakao/callback")
	public ModelAndView kakaoCallback(HttpServletRequest request, HttpSession session) {
		String code = (String) request.getParameter("code");
		logger.info("code : " + code);

		// 구글이 넘겨준 code 와 내 계정이 보유한 cid + secret을 조합하여
		// 구글 서버측에 token 발급을 요청해야 한다
		// 이때 우리 스프링 서버는 상대적으로 클라이언트가 된다
		// token은 회원정보에 접근할 수 있는 증명서 같은 개념

		/*
		 * 1. 토큰 취득을 위한 POST 헤더 구성하기
		 */
		String url = kakaoLogin.getToken_request_url();

		// body의 파라미터 구성하기 <파라미터명, 파라미터값>
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("code", code);
		params.add("client_id", kakaoLogin.getClient_id());
		params.add("redirect_uri", kakaoLogin.getRedirect_uri());
		params.add("grant_type", kakaoLogin.getGrant_type());

		// post 방식 헤더
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");

		// 머리와 몸 합치기
		HttpEntity httpEntity = new HttpEntity(params, headers);

		// 요청시도를 위한 객체 생성, 비동기방식의 요청을 위한 객체
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> entity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

		
		// 2. 토큰 요청 후 ResponseEntity 로부터 토큰 꺼내기 
		String body = entity.getBody();
		logger.info("구글에서 넘겨받은 응답 정보 : " + body);
		  
		// JSON으로 되어있는 문자열을 파싱하여 토큰을 가져오자 
		ObjectMapper mapper = new ObjectMapper();
		KakaoOAuthToken oAuthToken = null; 
		try { 
			oAuthToken = mapper.readValue(body, KakaoOAuthToken.class); 
		} catch (JsonMappingException e) {
			e.printStackTrace(); } 
		catch (JsonProcessingException e) {
			e.printStackTrace(); 
		}
		 
		
		//3. 토큰을 이용하여 회원정보에 접근 //oAuthToken 안의 토큰을 이용하여 회원정보에 접근 
		String userinfo_url = kakaoLogin.getUserinfo_url(); logger.info("userinfo_url : "+userinfo_url);
		
		//get 방식 요청 구성 
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+ oAuthToken.getAccess_token());
		//Bearer 뒤에 한칸 띄우고 token 넣어야 함 
		HttpEntity entity2 = new HttpEntity(headers2);
		
		//비동기객체를 이용한 GET 요청 
		RestTemplate restTemplate2 = new RestTemplate();
		ResponseEntity<String> userEntity = restTemplate2.exchange(userinfo_url, HttpMethod.GET, entity2, String.class); 
		String userBody = userEntity.getBody(); 
		logger.info("userBody : "+userBody);
		
		//사용자 정보 추출하기 
		HashMap<String, Object> userMap = null; 
		try { 
			userMap = mapper.readValue(userBody, HashMap.class); } 
		catch (JsonMappingException e) {
			e.printStackTrace(); 
		} 
		catch (JsonProcessingException e) {
			e.printStackTrace(); 
		}
		
		long id = (Long)userMap.get("id"); 
		String connected_at = (String)userMap.get("connected_at");
		//내부의 json은 맵으로 처리
		Map<String, Object> properties = (Map)userMap.get("properties");
		String nickname = (String)properties.get("nickname");
		logger.info("닉네임 : "+nickname);
		if(false) { //이미 db에 이 회원의 식별 고유 id 가 존재할 경우 //회원가입을 처리 (서비스의 regist) 세션에 담자
		
		}else { //그렇지 않은 경우 //로그인 처리만 하자 (세션에 담자)
		
		}
		 

		ModelAndView mav = new ModelAndView("redirect:/");
		return mav;
	}
	
	// 네이버 로그인 콜백
	@GetMapping("/sns/naver/callback")
	public ModelAndView naverCallback(HttpServletRequest request, HttpSession session) {
		String code = (String) request.getParameter("code");
		logger.info("code : " + code);

		// 구글이 넘겨준 code 와 내 계정이 보유한 cid + secret을 조합하여
		// 구글 서버측에 token 발급을 요청해야 한다
		// 이때 우리 스프링 서버는 상대적으로 클라이언트가 된다
		// token은 회원정보에 접근할 수 있는 증명서 같은 개념

		/*
		 * 1. 토큰 취득을 위한 POST 헤더 구성하기
		 */
		String url = naverLogin.getToken_request_url();

		// body의 파라미터 구성하기 <파라미터명, 파라미터값>
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("code", code);
		params.add("client_id", naverLogin.getClient_id());
		params.add("client_secret", naverLogin.getClient_secret());
		params.add("redirect_uri", naverLogin.getRedirect_uri());
		params.add("grant_type", naverLogin.getGrant_type());
		params.add("state", naverLogin.getState());

		// post 방식 헤더
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");

		// 머리와 몸 합치기
		HttpEntity httpEntity = new HttpEntity(params, headers);

		// 요청시도를 위한 객체 생성, 비동기방식의 요청을 위한 객체
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> entity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

		
		// 2. 토큰 요청 후 ResponseEntity 로부터 토큰 꺼내기 
		String body = entity.getBody();
		logger.info("네이버에서 넘겨받은 응답 정보 : " + body);
		  
		// JSON으로 되어있는 문자열을 파싱하여 토큰을 가져오자 
		ObjectMapper mapper = new ObjectMapper();
		NaverOAuthToken oAuthToken = null; 
		try { 
			oAuthToken = mapper.readValue(body, NaverOAuthToken.class); 
		} catch (JsonMappingException e) {
			e.printStackTrace(); } 
		catch (JsonProcessingException e) {
			e.printStackTrace(); 
		}
		 
		
		//3. 토큰을 이용하여 회원정보에 접근 //oAuthToken 안의 토큰을 이용하여 회원정보에 접근 
		String userinfo_url = naverLogin.getUserinfo_url(); 
		logger.info("userinfo_url : "+userinfo_url);
		
		//get 방식 요청 구성 
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+ oAuthToken.getAccess_token());
		logger.info("Access_Token : "+oAuthToken.getAccess_token());
		//Bearer 뒤에 한칸 띄우고 token 넣어야 함 
		HttpEntity entity2 = new HttpEntity(headers2);
		
		//비동기객체를 이용한 GET 요청 
		RestTemplate restTemplate2 = new RestTemplate();
		ResponseEntity<String> userEntity = restTemplate2.exchange(userinfo_url, HttpMethod.GET, entity2, String.class); 
		String userBody = userEntity.getBody(); 
		logger.info("userBody : "+userBody);
//		
//		//사용자 정보 추출하기 
//		HashMap<String, Object> userMap = null; 
//		try { 
//			userMap = mapper.readValue(userBody, HashMap.class); } 
//		catch (JsonMappingException e) {
//			e.printStackTrace(); 
//		} 
//		catch (JsonProcessingException e) {
//			e.printStackTrace(); 
//		}
//		
//		long id = (Long)userMap.get("id"); 
//		String connected_at = (String)userMap.get("connected_at");
//		//내부의 json은 맵으로 처리
//		Map<String, Object> properties = (Map)userMap.get("properties");
//		String nickname = (String)properties.get("nickname");
//		logger.info("닉네임 : "+nickname);
//		if(false) { //이미 db에 이 회원의 식별 고유 id 가 존재할 경우 //회원가입을 처리 (서비스의 regist) 세션에 담자
//		
//		}else { //그렇지 않은 경우 //로그인 처리만 하자 (세션에 담자)
//		
//		}
		 

		ModelAndView mav = new ModelAndView("redirect:/");
		return mav;
	}

}
