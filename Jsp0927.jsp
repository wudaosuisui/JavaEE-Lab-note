


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My first JSP</title>
</head>
<body>
<!-- succeed -->
<%
//解决乱码
//乱码问题  未能解决
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	response.setCharacterEncoding("UTF-8");
//获取name
	String name = request.getParameter("name");
	if(name != null){
		out.println("hello "+name+ "<br>");
	}
%>

<form action="index.jsp" > 
 	您的姓名：<input type="text" name ="name" id="name"/><br>
 	<input type="submit" value="提交">
</form>
</body>
</html>

<%--defeat --%>
<% String name = "header.jsp" %>
<jsp:include page="<%= name %>"%>

<!-- Form to get name -->
<!-- 默认调用的是git方法 -->
<!-- succeed -->
<form action="index.jsp">
UserName:<input type = "text" name="name">
<input type="submit" value = "Hello" /><br />
</form>



<!-- Say hello to user -->
<!-- succeed -->
<% String name = request.getParameter("name");
      if (name != null) {
            out.println("hello " + name + "<br>");
         }
       %>
       
       
      <%--声明标签  包含的是一段代码 --%>
<%!  int  count =0;%>
<!-- defeat -->

<%! String color[] = {"red","green","yellow"};
String getColor(int i){
    return color[i];
  }

%>

<%=count %><br><%--表达式  会输出0 --%><!-- succeed -->
<%="Hello Woeld" %>
