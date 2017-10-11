//--------------------------------使用 JSP　嵌套循环输出乘法表
index.jsp

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
//运行效果
1X1=1 
 1X2=2 2X2=4 
 1X3=3 2X3=6 3X3=9 
 1X4=4 2X4=8 3X4=12 4X4=16 
 1X5=5 2X5=10 3X5=15 4X5=20 5X5=25 
 1X6=6 2X6=12 3X6=18 4X6=24 5X6=30 6X6=36 
 1X7=7 2X7=14 3X7=21 4X7=28 5X7=35 6X7=42 7X7=49 
 1X8=8 2X8=16 3X8=24 4X8=32 5X8=40 6X8=48 7X8=56 8X8=64 
 1X9=9 2X9=18 3X9=27 4X9=36 5X9=45 6X9=54 7X9=63 8X9=72 9X9=81 

//--------------------------------------------循环输出绿色和蓝色
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%--将pageEncoding 设置为 "UTF-8" --%>
    <!-- pageEncoding="ISO-8859-1" 为 原有值-->
<%@ page info="a classic JSP" %><%-- 不是很懂 --%>
<%@ page import="java.util.*" %><%-- 引入所有java包？大概吧 --%>
<%-- This is a classic JSP ,it contain all elements of JSP --%>
<%! 
	//输出时间和日期？输出在哪里？控制台也没找到
	String setDate(){
		return (new Date()).toLocaleString();
   }
	//设置 count 为 10
	int count = 10;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- 建立table -->
<table>
	<!-- 第一行是灰色的  -->
	<tr bgcolor="777777">
		<td>====================================</td>
	</tr>
	<!-- 循环输出绿色和蓝色  -->
	<%//这个是脚本标签
		int i;
	String color1 = "99ccff";//绿色
	String color2 = "88cc33";//蓝色
	for(i = 1;i<=count;i++){//count = 10 定义在地14行
		String color = "";
		if(i%2 == 0)
			color = color1;
		else
			color = color2;
		out.println("<tr bgcolor='"+color+
				"'><td>====================================</td></tr>");
	}
	%>
</table>
</body>
</html>

//-------------------------------------引用其他页面
header.jsp
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- 定义名字 -->
<%!String name = "header"; %>
<table height="20%" width="100%" bgcolor="99ccff">
	<tr>
		<!-- 显示名字 -->
		<td align="center">==<%=name %>==</td>
	</tr>
</table>
</body>
</html>

side.jsp
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

<% name = ".jsp"; %>
<table height="20%" width="100%" bgcolor="5577ff">
	<tr>
		<td align="center">==<%=name %>==</td>
	</tr>
</table>
</body>
</html>

body.jsp
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

footer.jsp
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

//-------------------------------------输出当前时间！

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
<!-- 输出当前时间 -->
<%=new Date().toLocaleString() %>
</body>
</html>
