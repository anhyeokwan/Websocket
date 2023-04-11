package kr.or.member.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class MailSender {

	public String sendMail(String email) {
		boolean result = false;
		
		Random r = new Random();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 8; i++) {
			// 숫자, 영어소문자, 영어대문자 섞어서 8개조합
			
			int flag = r.nextInt(3);
			if(flag == 0) {
				// 0~9
				int randomNum = r.nextInt(10);
				sb.append(randomNum);
			}else if(flag == 1) {
				char randomChar = (char)(r.nextInt(26)+65);
				sb.append(randomChar);
			}else if(flag == 2) {
				char randomSmall = (char)(r.nextInt(26)+97);
				sb.append(randomSmall);
			}
		}
		
		// 지금부터 쓰는 코드는 이메일라이브러리를 쓰기 위함
		Properties prop = System.getProperties();
	      prop.put("mail.smtp.host","smtp.gmail.com");
	      prop.put("mail.smtp.port", 465); //포트설정
	      prop.put("mail.smtp.auth", "true");
	      prop.put("mail.smtp.ssl.enable", "true");
	      prop.put("mail.smtp.ssl.trust","smtp.gmail.com");
	      prop.put("mail.smtp.starttls.required", "true");
	      prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
	      prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	      //인증정보설정(로그인) javax.mail-Session
	      Session session =Session.getDefaultInstance(prop, 
	            new Authenticator() {
	               protected PasswordAuthentication getPasswordAuthentication() { //구글 아이디 패스워드
	                  PasswordAuthentication pa = new PasswordAuthentication("bomkh123","oplntjofdzxwuvaf");
	                  return pa;//여기까지가 구글에서 로그인 한 시점
	            }
	         }   
	      );
	      
	    //이메일을 작성해서 전송하는 객체 
	      MimeMessage msg = new MimeMessage(session);      
	      try {
	         //이메일작성
	         msg.setSentDate(new Date());
	         //보내는 사람 정보
	         msg.setFrom(new InternetAddress("bomKh123@gmail.com","KH 당산 A클래스"));
	         //받는 사람정보 
	         InternetAddress to = new InternetAddress(email);
	         msg.setRecipient(Message.RecipientType.TO, to);
	         // 이ㅣ메일 제목설정
	         msg.setSubject("안형관 홈페이지 인증메일입니다.","UTF-8"); // 인증메일 확인 구문
	         //이메일 본문설정
	         msg.setContent("<h1>안녕하세요. 안형관 홈페이지입니다.</h1>"
	        		 + "<h3>인증번호는 [<span style='color:red'>" + sb.toString() + "</span>] 입니다.","text/html;charset=utf-8");
	         //이메일전송
	         Transport.send(msg);
	         result = true;
	      }catch(MessagingException e) {
	         e.printStackTrace();
	      }catch(UnsupportedEncodingException e) {
	         e.printStackTrace();
	         
	      }
	      
	      if(result) {
	    	  return sb.toString();
	      }else {
	    	  return null;
	      }
		
	}
	
	

}
