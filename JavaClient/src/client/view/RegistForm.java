package client.view;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//안드로이드로 폰의 사진 전송을 하려면, javaSE의 Http통신을 다룰줄 알아야 한다
public class RegistForm extends JFrame {
	JTextField t_category_idx, t_product_name, t_brand, t_price, t_discount;
	JButton bt_file, bt_regist;
	JTextArea t_detail;
	JFileChooser chooser;
	
	//http 통신을 위한 객체
	HttpURLConnection con;
	String host = "http://172.30.1.96:8888/admin/rest/product";
	String boundary = "**********"; //하이픈으로 감쌀, 데이터의 경계 기준 문자열
	String hypen = "--";
	String line = "\r\n";
	File file;//유저가 전송을 위해 선택한 파일
	
	public RegistForm() {
		t_category_idx = new JTextField("1", 25);
		t_product_name = new JTextField("rrr", 25);
		t_brand = new JTextField("some brand", 25);
		t_price= new JTextField("1500", 25);
		t_discount= new JTextField("1200", 25);
		t_detail = new JTextArea("some content");
		bt_file = new JButton("파일 찾기");
		bt_regist = new JButton("등록");
		chooser = new JFileChooser("C:\\Users\\admin\\Desktop");
		
		t_detail.setPreferredSize(new Dimension(270, 160));
		
		setLayout(new FlowLayout());
		
		add(t_category_idx);
		add(t_product_name);
		add(t_brand);
		add(t_price);
		add(t_discount);
		add(t_detail);
		add(bt_file);
		add(bt_regist);
		
		setSize(300, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		bt_file.addActionListener((c)->{
			selectFile();
		});
		bt_regist.addActionListener((c)->{
			int result = JOptionPane.showConfirmDialog(RegistForm.this, "서버로 전송하시겠습니까?");
			if(result != JOptionPane.OK_OPTION) return;
			
			//네트워크 통신은 별도의 쓰레드로 처리하는게 안정적
			Thread thread = new Thread() {
				public void run() {
					try {
						regist();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			thread.start();
		});
	}
	
	public void selectFile() {
		chooser.showOpenDialog(this);
		
		//유저가 선택한 파일 얻기
		file = chooser.getSelectedFile();
	}
	//text뿐만 아니라, 바이너리 파일도 함꼐 http 전송해야 하므로 
	//multipart/form-data 형식을 사용해야 한다
	public void regist() throws MalformedURLException, IOException{
		
		
		//웹전송을 위한 코드;
		URL url = new URL(host);
		con = (HttpURLConnection)url.openConnection();
		
		
		//헤더 구성하기
		con.setRequestProperty("Content-Type", "multipart/form-data;charset=utf-8;boundary="+boundary);
		con.setRequestMethod("POST");
		con.setDoOutput(true); //서버에 보낼 떄
		con.setDoInput(true); // 서버에서 가져올 때
		con.setUseCaches(false);
		con.setConnectTimeout(2500);
		
		//body 구성하기 (스트림으로 처리)
		DataOutputStream dos = new DataOutputStream(con.getOutputStream());
		
		//--(boundary)\r\n
		//텍스트 파라미터의 시작을 알리는 구분자 선언
		dos.writeBytes(hypen + boundary + line);
		//바디를 구성하는 요소들간에는 줄바꿈으로 구반한다
		dos.writeBytes("Content-Disposition:form-data;name=\"category.category_idx\"" + line); //파라미터 선언 뒤에는 줄바꿈 표시
		dos.writeBytes("Content-Type:text/plain;charset=utf-8"+line);
		dos.writeBytes(line);//값 지정 직후에는 라인으로 또 구분
		dos.writeBytes(t_category_idx.getText() + line);
		//여기까지 하나의 파라미터에 대한 대입
		
		dos.writeBytes(hypen + boundary + line);
		dos.writeBytes("Content-Disposition:form-data;name=\"product_name\"" + line);
		dos.writeBytes("Content-Type:text/plain;charset=utf-8"+line);
		dos.writeBytes(line);
		dos.writeBytes(t_product_name.getText() + line);
		
		dos.writeBytes(hypen + boundary + line);
		dos.writeBytes("Content-Disposition:form-data;name=\"brand\"" + line);
		dos.writeBytes("Content-Type:text/plain;charset=utf-8"+line);
		dos.writeBytes(line);
		dos.writeBytes(t_brand.getText() + line);
		
		dos.writeBytes(hypen + boundary + line);
		dos.writeBytes("Content-Disposition:form-data;name=\"price\"" + line);
		dos.writeBytes("Content-Type:text/plain;charset=utf-8"+line);
		dos.writeBytes(line);
		dos.writeBytes(t_price.getText() + line);
		
		dos.writeBytes(hypen + boundary + line);
		dos.writeBytes("Content-Disposition:form-data;name=\"discount\"" + line);
		dos.writeBytes("Content-Type:text/plain;charset=utf-8"+line);
		dos.writeBytes(line);
		dos.writeBytes(t_discount.getText() + line);
		
		dos.writeBytes(hypen + boundary + line);
		dos.writeBytes("Content-Disposition:form-data;name=\"detail\"" + line);
		dos.writeBytes("Content-Type:text/plain;charset=utf-8"+line);
		dos.writeBytes(line);
		dos.writeBytes(t_detail.getText() + line);
		
		//파일 파라미터 처리
		dos.writeBytes(hypen + boundary + line);//시작할 떄
		//파일은 name 말고도 filename을 적어줘야 한다
		dos.writeBytes("Content-Disposition:form-data;name=\"files\";filename=\""+file.getName()+"\"" + line);
		dos.writeBytes("Content-Type:image/jpeg" + line);//파일의 종류, 형식
		dos.writeBytes(line);
		
		//파일 쪼개서 전송
		FileInputStream fis = new FileInputStream(file);
		byte[] buff = new byte[1024];
		int data = -1;
		while(true) {
			data = fis.read(buff);
			if(data == -1) break;
			dos.write(buff);
		}
		
		//전송
		dos.writeBytes(line);
		dos.writeBytes(hypen + boundary + hypen); // 끝맺을때는 hypen을 뒤에도 줘야 한다
		dos.flush();
		dos.close();
		fis.close();
		
		//웹서버로부터 받은 http 상태코드로 성공여부를 따져보자
		int status = con.getResponseCode();
		if(status >= HttpURLConnection.HTTP_OK && status < HttpURLConnection.HTTP_MULT_CHOICE) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
			
		}
	}
	

	public static void main(String[] args) {
		new RegistForm();
	}
}
