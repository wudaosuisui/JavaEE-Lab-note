package net.onest;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReceiveMailDemo
 */
@WebServlet("/ReceiveMailDemo")
public class ReceiveMailDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReceiveMailDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		try {

			// 定义连接POP3服务器的属性信息
			String pop3Server = "pop.126.com";
			String protocol = "pop3";
			String username = "mailAddress";//自己的邮箱地址
			String password = "邮箱授权码";//需修改为自己的邮箱的授权码
			Properties props = new Properties();
			props.setProperty("mail.store.protocol", protocol); // 使用的协议（JavaMail规范要求）
			props.setProperty("mail.pop3.host", pop3Server); // 收件人的邮箱的pop服务器地址
			// 获取连接
			Session session = Session.getInstance(props);
			session.setDebug(false);
			// 获取Store对象
			Store store  = session.getStore(protocol);				
			store.connect(pop3Server,username,password); // POP3服务器的登陆认证
			// 通过POP3协议获得Store对象调用这个方法时，邮件夹名称只能指定为"INBOX"
			Folder folder;
			folder = store.getFolder("INBOX");
			// 获得用户的邮件帐户
			folder.open(Folder.READ_WRITE); // 设置对邮件帐户的访问权限
			Message[] messages = folder.getMessages();// 得到邮箱帐户中的所有邮件
			for (Message message : messages) {
				String subject = message.getSubject();// 获得邮件主题
				System.out.println("主题"+subject);
				Address from = (Address) message.getFrom()[0];// 获得发送者地址
				System.out.println("发件人"+from);
			}
			folder.close(false);// 关闭邮件夹对象
			store.close(); // 关闭连接对象
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
