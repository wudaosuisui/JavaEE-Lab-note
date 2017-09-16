

___________________________________________________________________________________________________________Login
login.html

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="loginCheckServlet">
please input you name:<input type="text" name="username" /><br />
input you password:<input type="text" name="password" /><br />
<input type="checkbox" name="isSave" />保存一周<br />
<input type="submit" />
</form>
</body>
</html>

loginCheckServlet

package onest.dev;

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {

		String parameter = request.getParameter("isSave");
		String username = request.getParameter("username");
		Cookie cookie = new Cookie("userName", username);
		response.addCookie(cookie);
		if ("on".equals(parameter))
			cookie.setMaxAge(60 * 60 * 24 * 7);
		response.sendRedirect("LoginServlet");

	}
}

LoginServlet

protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			response.sendRedirect("login.html");
		} else {
//			for(Cookie cookie : cookies) {
//				writer.write("Value:" + cookie.getValue()+"</br Name:>"+cookie.getName());
//			}
			String cookieValue = null;
			for (Cookie cookie : cookies) {
				if ("userName".equals(cookie.getName())) {
					cookieValue = cookie.getValue();
					break;
				}
			}
			if (cookieValue == null)
				response.sendRedirect("login.html");
			else
				response.getWriter().write("hello:" + cookieValue.toString());
		}
	}



___________________________________________________________________________________________________________session
扩展
怎样修改代码，当用户提交后显示的是当时输入的名称(Session中保存的已经是最新的用户输入值)。
o=( 0 ^ 0 )=o
package onest.dev;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionDemo
 */
@WebServlet("/SessionDemo")
public class SessionDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionDemo() {
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
	
	protected void doPost(HttpServletRequest request
			, HttpServletResponse response) 
	        throws ServletException, IOException {
		PrintWriter writer = response.getWriter();//使用Write
		HttpSession session = request.getSession();//建立Session （数组？？？）
		//Object attribute = session.getAttribute("userName");//获取Session对象“userName”属性的值(取得属性值使用方法session.getAttribute)
		
		String name = request.getParameter("vName");
		
		if(name != null) {
			session.setAttribute("userName", name);
			Object attribute = session.getAttribute("userName");
			writer.write("hi <b>"  +attribute.toString()+ "</b>, welcome come back!");//welcome come back!
			name = null;
			writer.write("<form method='post' action=''>back:<input type='submit' /></form>");
		}else {
			
			writer.write("<form method='post' action=''>please input your name:<input type='text' name='vName' /><input type='submit' /></form>");
			
		}
	
		
	}

}



___________________________________________________________________________________________________________session

一：  
  框内：testA
  name = null  ;   [userName,null]
二： 
  框内：testB
  name = testA ;   [userName,testA]
三：
  testA,welcome back!
  注：此后的每次运行  都将如界面三，因为session内 已经有内容了

package onest.dev;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionDemo
 */
@WebServlet("/SessionDemo")
public class SessionDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionDemo() {
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
	protected void doPost(HttpServletRequest request
			, HttpServletResponse response) 
	        throws ServletException, IOException {
		PrintWriter writer = response.getWriter();//使用Write
		HttpSession session = request.getSession();//建立Session （数组？？？）
		Object attribute = session.getAttribute("userName");//获取Session对象“userName”属性的值(取得属性值使用方法session.getAttribute)
		if (attribute == null) {
			writer.write("<form method='post' action=''>please input your name:<input type='text' name='vName' /><input type='submit' /></form>");
			String name = request.getParameter("vName");//注意！！！获取的是上一次界面的vName!!! "vName" because <input type='text' name='vName' />
			session.setAttribute("userName", name);//将该值保存到Session对象中(session.setAttribute方法)
		} else {
			writer.write("hi <b>" + attribute.toString() + "</b>, welcome come back!");//welcome come back!
		}
	}


}
