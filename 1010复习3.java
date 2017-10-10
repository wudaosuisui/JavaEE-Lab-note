//------------------------------------------一次访问次数 计数
//每次返回的 次数都不同  A B 之间 通用 进行累计

package onest.dev;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletA
 */
@WebServlet("/ServletA")
public class ServletA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request
    		, HttpServletResponse response) 
            throws ServletException, IOException {
    	// get ServletContext
    	ServletContext servletContext = this.getServletContext();
    	//get name 为 requestNumber 的 Attribute 的 值 
    	//用Object类型获取
    	Object number = servletContext.getAttribute("requestNumber");
    	
    	int i = 0;
    	//判断
    	if (number == null)//如果 number 为空 即 之前无人访问  
    		//则创建一个Attribute name 为 requestNumber value为0
    		servletContext.setAttribute("requestNumber", 0);
    	else {//number 不为空 有值 之前已经开始访问了
    		//使i= number 
    		//toSring()
    		/*Object 类的 toString 方法返回一个字符串，
    		 * 该字符串由类名（对象是该类的一个实例）、at标记符“@”、此对象哈希码的无符号十六进制表示组成。
    		 * 换句话说，该方法返回一个字符串，它的值等于：
    		 * getClass().getName() + '@' + Integer.toHexString(hashCode())
    		 * 返回：该对象的字符串表示形式。
    		 * */
    		i = new Integer(number.toString());//强制类型转换为integer
    		//i加 1
    		i++;
    		//重新保存
    		servletContext.setAttribute("requestNumber", i);
    	}
    	PrintWriter writer = response.getWriter();
    	//输出i
    	writer.println("I'm ServletA, the request number is " + i);
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package onest.dev;

import java.io.IOException;
import java.io.PrintWriter;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletB
 */
@WebServlet("/ServletB")
public class ServletB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request
    		, HttpServletResponse response) 
            throws ServletException, IOException {
    	ServletContext servletContext = this.getServletContext();
    	Object number = servletContext.getAttribute("requestNumber");
    	int i = 0;
    	if (number == null)
    		servletContext.setAttribute("requestNumber", 0);
    	else {
    		i = new Integer(number.toString());
    		i++;
    		servletContext.setAttribute("requestNumber", i);
    	}
    	PrintWriter writer = response.getWriter();
    	writer.println("I'm ServletB, the request number is " + i);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
