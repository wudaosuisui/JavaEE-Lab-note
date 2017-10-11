//--------------------------------------------------------------------------------------请求&响应
//--------------------------------------------------------------------请求
//请求的参数等信息  皆包含在此对象内
HttpServletRequest request




//------------获取表达请求中的参数
      request.getParameter("表单name属性的值")
      //示例
      String userName = request.getParameter("name");

//--------------------------------------------------------------------响应
//响应的内容等信息 皆包含在此对象内
HttpServletResponse response




//-----------向页面输出信息
      //java包
      import java.io.PrintWriter;//PrintWriter 包！！
      //创建对象
      PrintWriter writer = response.getWriter();
      //输出示例
      writer.write("You can print anything!");
      
      
      
 //--------------------------------------------------------------------判断内容是否相同
A.equals(b);
//返回布尔值
//示例
userName.equals(cookie.getValue());
      
//---------------------------------------------------------------------cookie
            //建立 cookie数组
            Cookie[] cookies = request.getCookies();
            //获取cookie value
            cookie.getValue()
            //获取cookie name
            cookie.getName()
            //获取上一界面传递过来的参数
		String vname = request.getParameter("name");
		//创建一个cookie对象 name = vName  ,value = vname
		Cookie cookie = new Cookie("vName", vname);
		//设置在客户端保存时间  单位为秒 不设置 则会在用户端关闭时  被删除
		cookie.setMaxAge(60*60*24*7); 
		//添加进cookie数组
		response.addCookie(cookie); 
      
//---------------------------------------------------------------------session











