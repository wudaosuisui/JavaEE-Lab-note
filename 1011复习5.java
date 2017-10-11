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
