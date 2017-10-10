//-------------------------------添加了下文中字段 会在初始化servlet时  在控制台输出相应信息
package onest.dev;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitAndDestroy
 */
//@WebServlet("/InitAndDestroy")
public class InitAndDestroy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitAndDestroy() {
        super();
        // TODO Auto-generated constructor stub
    }
    /*！！！！！！初始化时  会执行此字段 ！！！！！！*/
    @Override
    public void init(ServletConfig config) throws ServletException {
    	System.out.println("InitAndDestroy Servlet is init...");
    	System.out.println(config.getInitParameter("init.test"));
    	ServletContext servletContext = config.getServletContext();
    	System.out.println(servletContext.getInitParameter("context.text"));
    }



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

}
//-------------------------------------过滤器  用户点击不同的权限 会执行进入不同的界面

//---------------html

index.html

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- janp to servlet -->
<form action = "RoleServlet" method = "post">
权限：
<input type = "radio" name = "roles" value = "e"><!-- everyone -->
任意用户
<input type = "radio" name = "roles" value = "u"><!-- user -->
用户
<input type = "radio" name = "roles" value = "a"><!-- admin -->
管理员<br>
<input type = "submit" vlaue = "提交"/>
</form>
</body>
</html>

admin.html

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
jast only admin access this page!
</body>
</html>

publik.html

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
everyone access this page!
</body>
</html>

user.html

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
admin or user access this page!
</body>
</html>

//---------------------servlet

org.edu.filter

AdminFilter.java


package org.edu.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/AdminFilter")
public class AdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		if(		//只有 admin可以进入
				"a".equals(((HttpServletRequest)request).getSession().getAttribute("admin"))) {
			((HttpServletRequest)request).getRequestDispatcher("admin.html").forward(request, response);	
		}else {
			((HttpServletResponse)response).sendRedirect("public.html");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

UserFilter.java

package org.edu.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class UserFilter
 */
@WebFilter("/UserFilter")
public class UserFilter implements Filter {

    /**
     * Default constructor. 
     */
    public UserFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(		//admin  & user  都可进入
				"a".equals(((HttpServletRequest)request).getSession().getAttribute("admin"))
				||"u".equals(((HttpServletRequest)request).getSession().getAttribute("user"))) {
			//跳转至user 页面
			((HttpServletRequest)request).getRequestDispatcher("user.html").forward(request, response);	
		}else {
			((HttpServletResponse)response).sendRedirect("public.html");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}


org.edu.servlet
RoleServlet

package org.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RoleServlet
 */
@WebServlet("/RoleServlet")
public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoleServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*PrintWriter write = response.getWriter();
		write.write("jamp to servlet seccess");*/
		
		String role = request.getParameter("roles");//get form radio
		//判断 role
		if("a".equals(role)) {
			//创建 session
			request.getSession().setAttribute("admin",role);
			//jamp to other pages
			response.sendRedirect("AdminFilter");
		}else if("u".equals(role)){
			//创建 session
			request.getSession().setAttribute("user",role);
			//jamp to other pages
			response.sendRedirect("UserFilter");
		}else {
			//liean session
			request.getSession().removeAttribute("admin");
			request.getSession().removeAttribute("user");
			//jamp to public.html
			response.sendRedirect("public.html");
		}
	}

}





