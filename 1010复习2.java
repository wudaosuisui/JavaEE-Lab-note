//--------------------------------------doGet 与 doPost 区别

index.html-->RequestDemo-->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="RequestDemo">
  goto RequestDemo Servlet with GET 
</a>
<!--在url中显示参数 适合少量的传递参数-->
<form action=" RequestDemo" method="get">
  please input your nick:<input type="text" name="nick" /> 
  submit a GET request.<input type="submit" />
</form>
<!--在url中不显示参数  适合传递大量信息 因为post传递的内容 打包在请求里-->
<form action=" RequestDemo" method="post">
  please input your nick:<input type="text" name="nick" /> 
  submit a POST request.<input type="submit" />
</form>

</body>
</html>

//------------------------------------------System.out.println("可以将信息显示在控制台");
//  Git Post 的内容都会执行 都会显示在控制台 
package onest.dev;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestPostAndGet
 */
@WebServlet("/TestPostAndGet")
public class TestPostAndGet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestPostAndGet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request
    		, HttpServletResponse response) 
            throws ServletException, IOException {
    	System.out.println("You send a GET request");
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request
    		, HttpServletResponse response) 
            throws ServletException, IOException {
    	System.out.println("You send a POST request");
    }
}

//--------------------------------------跳转页面传递参数
//
/*
关键代码
创建一个Attribute 
   request.setAttribute("request.Attribute", "liuzhanhong");
jamp to TargerServlet & 传递 request response & 刚刚创建的Attribute
   request.getRequestDispatcher("TargetServlet").forward(request, response);
*/
ForwardDemo.java
 
protected void doPost(HttpServletRequest request
    		, HttpServletResponse response) 
            throws ServletException, IOException {
     //创建一个Attribute 
    	request.setAttribute("request.Attribute", "liuzhanhong");
    	//jamp to TargerServlet & 传递刚刚创建的Attribute
    	request.getRequestDispatcher("TargetServlet").forward(request, response);
    }

RedirectDemo.java

protected void doPost(HttpServletRequest request
    		, HttpServletResponse response) 
            throws ServletException, IOException {
      //创建一个Attribute 
    	request.setAttribute("request.Attribute", "liuzhanhong");
      //jamp to TargerServlet
    	response.sendRedirect("TargetServlet");
    } 
TargetServlet.java

protected void doPost(HttpServletRequest request
    		, HttpServletResponse response) 
            throws ServletException, IOException {
      //接收刚刚的Attribute  ForwardDemo可接收到 RedirectDemo 接收不到
    	Object attribute = request.getAttribute("request.Attribute");
      //创建PrintWriter
    	PrintWriter writer = response.getWriter();
    	writer.println("hi,Welcome<br />");
      //打印获取的 attribute
    	writer.println("request.Attribute is : " + attribute);
    }

/*
详尽代码
*/
package onest.dev;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForwardDemo
 */
@WebServlet("/ForwardDemo")
public class ForwardDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForwardDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request
    		, HttpServletResponse response) 
            throws ServletException, IOException {
    	doPost(request, response);
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request
    		, HttpServletResponse response) 
            throws ServletException, IOException {
    	request.setAttribute("request.Attribute", "liuzhanhong");
    	//jamp to TargerServlet
    	request.getRequestDispatcher("TargetServlet").forward(request, response);
    }

}

package onest.dev;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RedirectDemo
 */
@WebServlet("/RedirectDemo")
public class RedirectDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request
    		, HttpServletResponse response) 
            throws ServletException, IOException {
    	this.doPost(request, response);
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request
    		, HttpServletResponse response) 
            throws ServletException, IOException {
    	request.setAttribute("request.Attribute", "liuzhanhong");
    	response.sendRedirect("TargetServlet");
    }


}

package onest.dev;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Servlet implementation class TargetServlet
 */
@WebServlet("/TargetServlet")
public class TargetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TargetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request
    		, HttpServletResponse response) 
            throws ServletException, IOException {
    	this.doPost(request, response);
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request
    		, HttpServletResponse response) 
            throws ServletException, IOException {
    	Object attribute = request.getAttribute("request.Attribute");
    	PrintWriter writer = response.getWriter();
    	writer.println("hi,Welcome<br />");
    	writer.println("request.Attribute is : " + attribute);
    }


}

