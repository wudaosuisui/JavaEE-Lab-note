package net.onest;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ActivateCode
 */
@WebServlet("/ActivateCode")
public class ActivateCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivateCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//模拟发送邮件（准备2个邮箱地址）
		//1. 构造Session（邮件会话）对象，首先要有Properties对象
		Properties props = new Properties();
		//设置协议
		props.put("mail.transport.protocol", "smtp");
		//设置邮件服务器
		props.put("mail.smtp.host", "smtp.126.com");
		//设置授权
		props.put("mail.smtp.auth", true);
		//构造邮件会话对象(Session)
		Session session = Session.getInstance(props, 
				new Authenticator() {

					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						// 两个参数：1. 发件人地址，2. 授权码
						//("bddylww@126.com", "lww123")
						return new PasswordAuthentication("126发件人邮箱email", "邮箱授权码");
					}
				
		});
		//2. 创建邮件，也就是要创建一个Message（MIMEMessage子类）对象
		Message msg = new MimeMessage(session);
		//给邮件对象设置发件人、收件人、邮件主题、邮件内容
		try {
			//设置邮件发件人
			//("bddylww@126.com")
			msg.setFrom(new InternetAddress("126发件人email"));
			//设置邮件收件人
			//("273429028@qq.com")
			msg.setRecipient(Message.RecipientType.TO, 
					new InternetAddress("收件人email"));
			//设置邮件主题
			msg.setSubject("测试邮件的主题");
			//设置邮件的内容
			msg.setText("这是邮件的具体内容");
			//设置邮件发送时间
			msg.setSentDate(new Date());

			//3. 使用Transport发送邮件
			Transport.send(msg);
			
			System.out.println("邮件发送成功");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
