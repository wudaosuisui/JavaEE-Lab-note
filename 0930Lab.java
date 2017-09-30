//---------------------------------------------------------------------静态包含


//实验二 动态包含的登录 


//实验一 静态包含  和实验九的其中一个实验重复了
//编写JSP程序使用动态包含技术include动作指令实现web组件复用 实现功能： 用户登录


 +//实验三 区分JSP 中伪指令静态包含与JSP动作指令动态包含的区别
 +
 +header.jsp
 +
 +<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 +    pageEncoding="UTF-8"%>
 +<%!String name = "header"; %>
 +<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 +<html>
 +<head>
 +<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 +<title>Insert title here</title>
 +</head>
 +<body>
 +<table height="20%" width="100%" bgcolor="99ccff">
 +	<tr>
 +		<td align="center">==<%=name %>==</td>
 +	</tr>
 +</table>
 +</body>
 +</html>
 +
 +sid1.jsp
 +
 +<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 +    pageEncoding="UTF-8"%>
 +
 +<%--包含header.jsp  --%>
 +<%@ include file="header.jsp"%>    
 +<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 +<html>
 +<head>
 +<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 +<title>side</title>
 +</head>
 +<body>
 +<% name = "side"; %>
 +<table height="20%" width="100%" bgcolor="5577ff">
 +	<tr>
 +		<td align="center">==<%=name %>==</td>
 +	</tr>
 +</table>
 +</body>
 +</html>
 +
 +body1.jsp
 +
 +<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 +   pageEncoding="UTF-8"%>
 +    <%--包含side1.jsp  --%>
 +<%@ include file="side1.jsp"%>  
 +<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 +<html>
 +<head>
 +<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 +<title>body</title>
 +</head>
 +<body>
 +<% name = "body"; %>
 +<table height="40%" width="100%" bgcolor="9900ff">
 +	<tr>
 +		<td align="center">==<%=name %>==</td>
 +	</tr>
 +</table>
 +</body>
 +</html>
 +
 +footer1.jsp
 +
 +<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 +    pageEncoding="UTF-8"%>
 +    <%--包含body1.jsp  --%>
 +<%@ include file="body1.jsp"%> 
 +<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 +<html>
 +<head>
 +<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 +<title>footer</title>
 +</head>
 +<body>
 +<% name = "footer"; %>
 +<table height="20%" width="100%" bgcolor="777777">
 +	<tr>
 +		<td align="center">==<%=name %>==</td>
 +	</tr>
 +</table>
 +</body>
 +</html>
 +
 +main1.jsp
 +
 +<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 +    pageEncoding="UTF-8"%>
 +    <%--包含footer1.jsp  --%>
 +<%@ include file="footer1.jsp"%> 
 +<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 +<html>
 +<head>
 +<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 +<title>Insert title here</title>
 +</head>
 +<body>
 +
 +</body>
 +</html>
 +
 +/*在“03_tag/WebContent”目录下新建side2.jsp,
 +其中采用<jsp:include page=“header.jsp”/>包含header.jsp 
 +并对header中定义的name重新赋值name=“side”，
 +我们会发现编译都通不过，会报错，name没有声明*/
 +
 +/*
 +通过以上实验的完成我们会总结出
 +静态包含<%@include file=””%>与动态包含<jsp:include page=””/>的
 +区别：
 +静态包含是机械的将一个A页面放到另一个B页面中，跟把A的代码直接粘贴到B页面效果是一样的；
 +动态包含是在程序运行过程中动态的将A页面加载到B页面中去的，
 +在页面代码中写入<jsp:include />代码时并不把A的内容加载到B页面上
 +（所以side2.jsp中找不到header.jsp中定义的name，报错）
 +*/
