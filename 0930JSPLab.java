//——————————————————————————————————————————————————————————————————————————乘法表
//实验一 利用JSP的标签, 输出乘法表
//声明<%! %>、脚本<%  %>、表达式<%=  %>。
index.jap
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%--嵌套循环输出乘法表
 --%>
 <%--
 
 for(int i = 0;i<10;i++){
 	for(int j = 1;j<i+1;j++){
 		print(j+"*"+i+"="+j*i)
 	}
 	println()
 }
 
 
  --%>
  <% 
  	for(int i =1;i<10;i++){
  		for(int j = 1;j<=i;j++){
  			%>
  			<%=j %>X<%=i %>=<%=i*j %>
 	<% 
  		}
  		out.println("<br/>");
  	}
  %>
</body>
</html>

//——————————————————————————————————————————————————————————————————————————JSP中 伪指令属性的使用
//实验三 区分JSP 中伪指令静态包含与JSP动作指令动态包含的区别

header.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%!String name = "header"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table height="20%" width="100%" bgcolor="99ccff">
	<tr>
		<td align="center">==<%=name %>==</td>
	</tr>
</table>
</body>
</html>

sid1.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>

<%--包含header.jsp  --%>
<%@ include file="header.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>side</title>
</head>
<body>
<% name = "side"; %>
<table height="20%" width="100%" bgcolor="5577ff">
	<tr>
		<td align="center">==<%=name %>==</td>
	</tr>
</table>
</body>
</html>

body1.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="UTF-8"%>
    <%--包含side1.jsp  --%>
<%@ include file="side1.jsp"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>body</title>
</head>
<body>
<% name = "body"; %>
<table height="40%" width="100%" bgcolor="9900ff">
	<tr>
		<td align="center">==<%=name %>==</td>
	</tr>
</table>
</body>
</html>

footer1.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%--包含body1.jsp  --%>
<%@ include file="body1.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>footer</title>
</head>
<body>
<% name = "footer"; %>
<table height="20%" width="100%" bgcolor="777777">
	<tr>
		<td align="center">==<%=name %>==</td>
	</tr>
</table>
</body>
</html>

main1.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%--包含footer1.jsp  --%>
<%@ include file="footer1.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>

/*在“03_tag/WebContent”目录下新建side2.jsp,
其中采用<jsp:include page=“header.jsp”/>包含header.jsp 
并对header中定义的name重新赋值name=“side”，
我们会发现编译都通不过，会报错，name没有声明*/

/*
通过以上实验的完成我们会总结出
静态包含<%@include file=””%>与动态包含<jsp:include page=””/>的
区别：
静态包含是机械的将一个A页面放到另一个B页面中，跟把A的代码直接粘贴到B页面效果是一样的；
动态包含是在程序运行过程中动态的将A页面加载到B页面中去的，
在页面代码中写入<jsp:include />代码时并不把A的内容加载到B页面上
（所以side2.jsp中找不到header.jsp中定义的name，报错）
*/


//实验二 练习JSP 中page伪指令属性的使用

page.jsp

<%--利用page伪指令的 language指定页面使用的脚本语言是Java --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>

<%--import导入java.util.Date类 --%>
<%@ page import="java.util.*" %>

<%--session指出页面需要一个HTTP会话 --%>
<%@ page session="true" %>

<%--buffer指定客户端缓冲区的大小事12K --%>
<%@ page buffer="12kb" %>

<%--autoFlush指定当缓冲区满时到客户端的输出自动被刷新 --%>
<%@ page autoFlush="true" %>

<%-- info描述页面信息 --%>
<%@ page info="a test directtive jsp page" %>

<%--errorPage指定当页面出现异常时应调用错误提示页面 --%>
<%@ page isErrorPage="false" %>

<%--contentType指定字符编码格式 --%>
<%@ page errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>a test direstive jsp page</title>
</head>
<body>
<h1>use page dierective</h1>
<%=new Date().toLocaleString() %>
</body>
</html>

error.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>error!</title>
</head>
<body>
error!
<br>
Here has a error!
<br>
<br>
<font color="red"><%=exception.getMessage() %></font>
</body>
</html>
