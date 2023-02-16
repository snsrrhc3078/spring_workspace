package com.edu.springboard.client.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/*
 	웹 브라우저만이 Http 상의 요청과 응답을 받을 수 있는게 아니다
 	일반적인 응용프로그램이라면 어떤 언어라도 웹브라우저 역할의 프로그램을 제작할 수 있다
 	
 	이걸 JavaSE로 만드는 이유? android는 JavaSE를 포함하고 있기 때문에
 */
public class HttpClient {
	
	
	
	public static void main(String[] args) {
		//자바 언어에서 웹서버와의 요청 및 응답정보를 받기 위한 전용 객체는 바로
		//HttpURLConnection 객체가 있으며, URLConnection의 자식이다..
		DataOutputStream dos = null;
		BufferedReader buffr = null; //서버의 응답 받기용
		
		try {
			URL url = new URL("http://172.30.1.56:8888/rest/notice/regist");
			URLConnection connection = url.openConnection();
			HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
			
			//formdata 작성과 흡사함
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true); //서버에 데이터를 보낼 것 인지
			httpURLConnection.setUseCaches(false); //캐시 사용할 것인지
			//POST 전송 application/x-www-form-urlcoded
			httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			//파라미터 만들기
			String postData = "title=t2&writer=w2&content=qweqwe";
			
			//출력스트림을 이용하여 데이터 전송할 예정
			dos = new DataOutputStream(httpURLConnection.getOutputStream());
			dos.writeBytes(postData); //포스트 전송
			
			buffr = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			
			//서버의 응답 정보 받기
			String msg = null;
			while(true) {
				msg = buffr.readLine();
				if(msg == null) break;
				System.out.println(msg);
			}
			
			//출력 스트림을 이용하여 데이터 전송할 예정
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(buffr!=null) {
				try {
					buffr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(dos!=null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
