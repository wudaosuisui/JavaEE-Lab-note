# JavaEE-Lab-note
会话
———————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
最后成功实例

index.html

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
import java.io.PrintWriter;


CookieDemo


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
	protected void doPost(HttpServletRequest request
			, HttpServletResponse response) 
	        throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		Cookie[] cookies = request.getCookies();//建立 cookie数组
		String userName = request.getParameter("name");
		String visitorName = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (userName.equals(cookie.getValue())) {
					visitorName = cookie.getValue();
					break;
				}
			}
			if (visitorName != null) {
				writer.write("hi <b>" + visitorName + "</b>, welcome come back!");
			} else {
				writer.write("<form method='post' action='CookieDemoAdd'>please input your name:<input type='text' name='name' /><input type='submit' /></form>");
			}
		} else {
			writer.write("<form method='post' action='CookieDemoAdd'>please input your name:<input type='text' name='name' /><input type='submit' /></form>");
		}
	}



}

CookieDemoAdd

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
	protected void doPost(HttpServletRequest request
			, HttpServletResponse response) 
	        throws ServletException, IOException {
		String vname = request.getParameter("name");
		Cookie cookie = new Cookie("vName", vname);
		cookie.setMaxAge(60*60*24*7); //在客户端保存时间
		response.addCookie(cookie);
		response.sendRedirect("index.html");
	}

}

运行的最终结果
hi aad1, welcome come back!
其中有两三次页面跳转

————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————


CookieDemoAdd.java

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
	protected void doPost(HttpServletRequest request
			, HttpServletResponse response) 
	        throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		Cookie[] cookies = request.getCookies();
		
		for(int i = 0;i < cookies.length;i++) {//Cookie cookie : cookies
			writer.write(i+"<br>cookie.getValue(): "+cookies[i].getValue()+"<br/>"+
						"coocie.getName():"+cookies[i].getName()+"<br><br>");
		}
		String vname = "name2";//request.getParameter("name");//获取用户名字？
		Cookie cookie = new Cookie("vName2", vname);//保存在cookie中
		cookie.setMaxAge(60*60*24*7); //在客户端保存时间
		response.addCookie(cookie);//保存到客户端Cookie中
		//response.sendRedirect("CookieDemo");//跳转到CookieDemo页面??  跳转到index界面？？
	}


}

运行结果
—————— 1次
0
cookie.getValue(): rre
coocie.getName():vName

1
cookie.getValue(): ddk
coocie.getName():name

2
cookie.getValue(): name1
coocie.getName():vName1

3
cookie.getValue(): name2
coocie.getName():vName2

—————— 2次
0
cookie.getValue(): rre
coocie.getName():vName

1
cookie.getValue(): ddk
coocie.getName():name

2
cookie.getValue(): name1
coocie.getName():vName1

3
cookie.getValue(): name2
coocie.getName():vName2

4
cookie.getValue(): name3
coocie.getName():vName3

————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
index.html

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

CookieDemo.java  版本1

package onest.dev;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;//Cookie包!!
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;





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
	protected void doPost(HttpServletRequest request
			, HttpServletResponse response) 
	        throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		Cookie[] cookies = request.getCookies();

		String visitorName = null;
		//visitorName创建成功  one
		writer.write("visitorName:<b>" + visitorName + "</b> <br/>");
		String userName = request.getParameter("name");
		//userName 获取成功 tow
		writer.write("userName: <b>" + userName + "</b> <br/>");
		//查看cookie 数组 00
		for(Cookie cookie : cookies) {
			writer.write("cookie.getValue(): <b>"+cookie.getValue()+"<br/>");
		}
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				//循环检测
				writer.write("cookie.getName():<b>"+cookie.getName()+"</b>FOR <br/>");
				writer.write("cookie.getValue():<b>"+cookie.getValue()+"</b>FOR <br/>");
				if (userName.equals(cookie.getName())) {
					//getName()内容01
					writer.write("Name:<b>"+cookie.getName()+"</b>,welcome come back!01<br/>");
					//getValue()内容02
					writer.write("Value:<b>"+cookie.getValue()+"</b>,welcome come back!02<br/>");
					visitorName = cookie.getValue();
					break;
				}
			}
			//visitorName 内容 03
			writer.write("visitorName: <b>"+visitorName+"</b> 03<br/>");
			if (visitorName != null) {
				//if执行成功 04
				writer.write("visitorName:hi <b>" + visitorName + "</b>,  04<br/>");
			} else {
				//else执行成功 05
				writer.write("<form method='post' action='CookieDemoAdd'>please input your name05:<input type='text' name='name' /><input type='submit' /></form>");
			}
		} else {
			//else执行成功 06
			writer.write("<form method='post' action='CookieDemoAdd'>please input your name06:<input type='text' name='name' /><input type='submit' /></form>");
		}
	}


}


CookieDemo.java  版本2

package onest.dev;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;//Cookie包!!
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;





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
	protected void doPost(HttpServletRequest request
			, HttpServletResponse response) 
	        throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		Cookie[] cookies = request.getCookies();

		String visitorName = null;
		//visitorName创建成功  one
		writer.write("visitorName:<b>" + visitorName + "</b> <br/>");
		String userName = request.getParameter("name");
		//userName 获取成功 tow
		writer.write("userName: <b>" + userName + "</b> <br/>");
		//查看cookie 数组 00
		for(Cookie cookie : cookies) {
			writer.write("cookie.getValue(): <b>"+cookie.getValue()+"<br/>");
		}
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				//循环检测
				writer.write("cookie.getName():<b>"+cookie.getName()+"</b>FOR <br/>");
				writer.write("cookie.getValue():<b>"+cookie.getValue()+"</b>FOR <br/>");
				if ("vName".equals(cookie.getName())) {
					//getName()内容01
					writer.write("Name:<b>"+cookie.getName()+"</b>,welcome come back!01<br/>");
					//getValue()内容02
					writer.write("Value:<b>"+cookie.getValue()+"</b>,welcome come back!02<br/>");
					visitorName = cookie.getValue();
					break;
				}
			}
			//visitorName 内容 03
			writer.write("visitorName: <b>"+visitorName+"</b> 03<br/>");
			if (visitorName != null) {
				//if执行成功 04
				writer.write("visitorName:hi <b>" + visitorName + "</b>,  04<br/>");
			} else {
				//else执行成功 05
				writer.write("<form method='post' action='CookieDemoAdd'>please input your name05:<input type='text' name='name' /><input type='submit' /></form>");
			}
		} else {
			//else执行成功 06
			writer.write("<form method='post' action='CookieDemoAdd'>please input your name06:<input type='text' name='name' /><input type='submit' /></form>");
		}
	}


}


CookieDemoAdd.java  

package onest.dev;


import java.io.IOException;
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
	protected void doPost(HttpServletRequest request
			, HttpServletResponse response) 
	        throws ServletException, IOException {
		String vname = request.getParameter("name");//获取用户名字？
		Cookie cookie = new Cookie("name", vname);//保存在cookie中
		cookie.setMaxAge(60*60*24*7); //在客户端保存时间
		response.addCookie(cookie);//保存到客户端Cookie中
		response.sendRedirect("CookieDemo");//跳转到CookieDemo页面??  跳转到index界面？？
	}


}

运行结果

版本1

visitorName:null 
userName: kko 
cookie.getValue(): rre
cookie.getValue(): ddk
cookie.getName():vNameFOR 
cookie.getValue():rreFOR 
cookie.getName():nameFOR 
cookie.getValue():ddkFOR 
visitorName: null 03

please input your name05:


版本2

visitorName:null 
userName: kko 
cookie.getValue(): rre
cookie.getValue(): ddk
cookie.getName():vNameFOR 
cookie.getValue():rreFOR 
Name:vName,welcome come back!01
Value:rre,welcome come back!02
visitorName: rre 03
visitorName:hi rre, 04
