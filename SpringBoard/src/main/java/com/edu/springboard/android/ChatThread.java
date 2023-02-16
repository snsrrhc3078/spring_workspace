package com.edu.springboard.android;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.Socket;
import java.util.Vector;

//소켓을 보관하고 + 각각 독립적으로 해당 소켓을 이용하여 메시지 주고받는
//객체가 되어야 함 == 쓰레드
public class ChatThread implements Runnable{
	//Runnable은 이미 해당 클래스가 누군가의 자식일 경우 쓰레드를
	//상속할 수 없을 때 사용할 수 있는 인터페이스이다. 주의할점은
	//Runnable은 쓰레드 자체가 아닌, 그냥 run() 메서드만을 보유한 객체
	//이다. 따라서 Runnable을 구현하더라도 쓰레드 객체는 필요하다
	Thread thread;
	Socket socket; //서버 소켓이 접속자를 발견하면, 그때 넘겨받게 되는 소켓
	BufferedReader buffr;
	BufferedWriter buffw;
	ChatServer chatServer;
	public ChatThread(Socket socket, ChatServer chatServer) {
		thread = new Thread(this); 
		//Runnable 구현 객체를 매개변수로 넣는다
		//이때부터 Runnable의 Run 메서드와 쓰레드 객체가 연계된다
		this.chatServer = chatServer;
		this.socket = socket;
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		thread.start();
	}
	
	//무한청취 시작
	public void run() {
		while(true) {
			listen();
		}
	}
	
	//듣기
	public void listen() {
		String msg = null;
		
		try {
			msg = buffr.readLine();
			System.out.println("msg : " + msg);
			//나에게만 보내지 말고, 서버에 접속한 모든 쓰레드 객체가 가진 send() 호출 (Broad Casting)
			for(int i =0;i<chatServer.clientList.size();i++) {
				ChatThread ct = chatServer.clientList.get(i);
				ct.send(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//말하기
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
