

html界面、表单常用项、将表单内容传递给servlet、选择sevlert执行的方法
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
  <!--  action内容显示所传递的servlet  method 内容显示servlet实行的方法 get执行diGet  post 执行 doPost -->
    <form action="RequestDemo3" method="post">
   
    <tr>
    	<td>
      		  用户名：
      	</td>
      	<td>
      		<input type="text" name="userName"></td><br/>
        </tr>
    <tr>
    	<td>
        	密码：
        </td>
        <td>
        <input type="text" name="pwd">
        </td>
    </tr>
    <tr>
    	<td>
       		 性别：
        </td>
        <td>
        	<input type="radio" name="sex" value="男" checked="checked">男
            <input type="radio" name="sex" value="女">女<br/>
        </td>
    </tr>
    <tr>
	    <td>
	    	爱好：
	    </td>
	    <td><input type="checkbox" name="hobby" value="足球">足球
	            <input type="checkbox" name="hobby" value="篮球">篮球
	            <input type="checkbox" name="hobby" value="排球">排球
	            <input type="checkbox" name="hobby" value="羽毛球">羽毛球<br/>
	    </td> 
    </tr>
    <tr>
    	<td>
    		 所在城市：
    	</td>
    	<td>
    		<select name="city">
                 <option>---请选择---</option>
                 <option value="bj">北京</option>
                 <option value="sh">上海</option>
                 <option value="sy">沈阳</option>
               </select>  
         </td>      
     </tr>
     <tr>
     	<td>
        <input type="submit" value="点击注册">
        </td>
        <td></td>
	 </tr>
    </form>
    </table>
</body>
</html>

所对应的Servlet

import java.io.IOException;
import java.util.Iterator;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestDemo3
 */
@WebServlet("/RequestDemo3")
public class RequestDemo3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestDemo3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //获取传过来的表单数据,根据表单中的name获取所填写的值
        String userName = request.getParameter("userName");
        String pwd = request.getParameter("pwd");
        String sex = request.getParameter("sex");
        String[] hobbys = request.getParameterValues("hobby");
        
        System.out.println(userName);
        System.out.println(pwd);
        System.out.println(sex);
        for (int i = 0; hobbys!=null&&i < hobbys.length; i++) {
            System.out.println(hobbys[i]+"\t");
        }
        }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
        }

}
对应的User类


public class User {
	private String userName;
    private String pwd;
    private String sex;
    private String[] hobby;
    private String city;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String[] getHobby() {
        return hobby;
    }
    public void setHobby(String[] hobby) {
        this.hobby = hobby;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    
}
