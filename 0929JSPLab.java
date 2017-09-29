index.jsp

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
