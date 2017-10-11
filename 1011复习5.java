//---------------------session
//用户输入用户名 会显示欢迎语句 并有返回按钮  可以返回刚刚的界面
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		//使用Write
		PrintWriter writer = response.getWriter();
		//获取session  没有则为null
		HttpSession session = request.getSession();
		//Object attribute = session.getAttribute("userName");//获取Session对象“userName”属性的值(取得属性值使用方法session.getAttribute)
		//获取传递来的参数
		String name = request.getParameter("vName");
		
		if(name != null) {
			//给session赋值
			session.setAttribute("userName", name);
			//获取name值为 userName的value值
			Object attribute = session.getAttribute("userName");
			//welcome come back!
			writer.write("hi <b>"  +attribute.toString()+ "</b>, welcome come back!");
			//将name 制空
			name = null;
			//返回按钮  执行了else 语句  
			writer.write("<form method='post' action=''>back:<input type='submit' /></form>");
		}else {
			
			writer.write("<form method='post' action=''>please input your name:<input type='text' name='vName' /><input type='submit' /></form>");
			
		}
	
		
	}

//------------------****cookie判断登陆的用户名是否正确*****
/*关键代码*/
//----html
index.html
<body>
<form action="CookieDemo" method="post">
  please input your name:<input type="text" name="name" /> 
  <input type="submit" />
</form>
</body>

//-----servlet
CookieDemo
//判断是否是已注册用户
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
		Cookie[] cookies = request.getCookies();//建立 cookie数组
		String userName = request.getParameter("name");
		//创建访问者名字为空
		String visitorName = null;
		//判断cookies数组是否为空
		if (cookies != null) {
			//cookies不空
			//循环遍历cookies
			for (Cookie cookie : cookies) {
				//如果userName与cookise中的某一个Value相等 则 赋值于 viseorName
				if (userName.equals(cookie.getValue())) {
					visitorName = cookie.getValue();
					break;
				}
			}
			//判断visitorName 是否为空
			if (visitorName != null) {
				//循环后 visitorName 不为空  登陆者是用户  显示欢迎界面
				writer.write("hi <b>" + visitorName + "</b>, welcome come back!");
			} else {
				//如果循环后visitorName 仍然为空  既cookies中 没有  与userName相符的内容
				//则输出 添加用户名的代码
				writer.write("<form method='post' action='CookieDemoAdd'>There is on user is your name!<br>please input your name to add in users:<input type='text' name='name' /><input type='submit' /></form>");
			}
		} else {
			//cookise 为空  没有任何userName 
			//则输出 添加用户名的代码
			writer.write("<form method='post' action='CookieDemoAdd'>cookies is null!<br>please input your name to add in users:<input type='text' name='name' /><input type='submit' /></form>");
		}
	}
  
  CookieDemoAdd
  //未注册用户 添加
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		//获取上一界面传递过来的参数
		String vname = request.getParameter("name");
		//创建一个cookie对象 name = vName  ,value = vname
		Cookie cookie = new Cookie("vName", vname);
		//设置在客户端保存时间   不设置 则会在用户端关闭时  被删除
		cookie.setMaxAge(60*60*24*7); 
		//添加进cookie数组
		response.addCookie(cookie);
		//返回 登陆界面 
		response.sendRedirect("index.html");
	}


//-----------------------------Listener  监听器
//----------------------------Servlet会在执行之前 和执行之后 在此执行一些初始化 和 清理工作
package onest.dev;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ServletContextListenerDemo
 *
 */
@WebListener
/*<lisrener>
 *  <listener-class>onest.dev.ServletContextListenerDemo</listener-class>
 * </listener>
 * */
public class ServletContextListenerDemo implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ServletContextListenerDemo() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    	System.out.println("当Web应用停止时会执行该方法，我们可以在这里做一些清理工作");
    }


	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
    	System.out.println("当Web应用启动时会执行该方法，我们可以在这里做一些初始化工作");
    }

	
}

//------------------------------------------------------------------------------------/*cookie详尽代码*/
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="CookieDemo" method="post">
  please input your name:<input type="text" name="name" /> 
  <input type="submit" />
</form>
</body>
</html>

package onest.dev;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;//Cookie包!!
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;//PrintWriter 包！！





/**
 * Servlet implementation class CookieDemo
 */
@WebServlet("/CookieDemo")
public class CookieDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
		Cookie[] cookies = request.getCookies();//建立 cookie数组
		String userName = request.getParameter("name");
		//创建访问者名字为空
		String visitorName = null;
		//判断cookies数组是否为空
		if (cookies != null) {
			//cookies不空
			//循环遍历cookies
			for (Cookie cookie : cookies) {
				//如果userName与cookise中的某一个Value相等 则 赋值于 viseorName
				if (userName.equals(cookie.getValue())) {
					visitorName = cookie.getValue();
					break;
				}
			}
			//判断visitorName 是否为空
			if (visitorName != null) {
				//循环后 visitorName 不为空  登陆者是用户  显示欢迎界面
				writer.write("hi <b>" + visitorName + "</b>, welcome come back!");
			} else {
				//如果循环后visitorName 仍然为空  既cookies中 没有  与userName相符的内容
				//则输出 添加用户名的代码
				writer.write("<form method='post' action='CookieDemoAdd'>There is on user is your name!<br>please input your name to add in users:<input type='text' name='name' /><input type='submit' /></form>");
			}
		} else {
			//cookise 为空  没有任何userName 
			//则输出 添加用户名的代码
			writer.write("<form method='post' action='CookieDemoAdd'>cookies is null!<br>please input your name to add in users:<input type='text' name='name' /><input type='submit' /></form>");
		}
	}



}


package onest.dev;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieDemoAdd
 */
@WebServlet("/CookieDemoAdd")
public class CookieDemoAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieDemoAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		//获取上一界面传递过来的参数
		String vname = request.getParameter("name");
		//创建一个cookie对象 name = vName  ,value = vname
		Cookie cookie = new Cookie("vName", vname);
		//设置在客户端保存时间   不设置 则会在用户端关闭时  被删除
		cookie.setMaxAge(60*60*24*7); 
		//添加进cookie数组
		response.addCookie(cookie);
		//返回 登陆界面 
		response.sendRedirect("index.html");
	}


}

