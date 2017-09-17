# JavaEE-Lab-note
//完全版本！！！！
________________________________________________________________________________html
login.html

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Login !!</h3>

<form action="LoginServlet">
please input you name:<input type="text" name="username" /><br />
input you password:<input type="text" name="password" /><br />
Login<input type="submit" /><br />
</form>
<form action="register.html">
Register<input type="submit" />
</form>
</body>
</html>

register.html

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Register ！！</h3>

<form action="loginCheckServlet">
please input you name:<input type="text" name="username" /><br />
input you password:<input type="text" name="password" /><br />
<input type="checkbox" name="isSave" />保存一周<br />
提交<input type="submit" />
</form>
<form action="login.html">
Back to login<input type="submit" />
</form>
</body>
</html>

productList.html

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<tr>
		<th>No.</th>
		<th>Name</th>
		<th>Price</th>
		<th>ShoppingCart</th>
	</tr>
	<tr>
		<td>1</td>
		<td>C</td>
		<td>60</td>
		<td><a href="AddShoppingCartServlet?id=1&name=C">Add to ShoppingCart</a></td>
	</tr>
	<tr>
		<td>2</td>
		<td>Java</td>
		<td>40</td>
		<td><a href = "AddShoppingCartServlet?id=2&name=Java">Add to ShoppingCart</a></td>
	</tr>
	<tr>
		<td>3</td>
		<td>Python</td>
		<td>50</td>
		<td><a href = "AddShoppingCartServlet?id=3&name=Pythin">Add to ShoppingCart</a></td>
	</tr>
	<tr>
		<td>4</td>
		<td>VB</td>
		<td>20</td>
		<td><a href="AddShoppingCartServlet?id=4&name=VB">Add to ShoppingCart</a></td>
	</tr>
</table>
<a href="ShowShoppingCartServlet">show ShoppingCart</a>
<a href="GoPayServlet">Go to Pay</a>
</body>
</html>

PayOver.html

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
You Pay secee!!!
<form action="productList.html">
Back to shopping:<input type="submit" />
</form>
</body>
</html>

________________________________________________________________________________servlet
//关键代码

AddShopingCarServlet.java

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		//将物品添加到购物车的功能
		String bookName = request.getParameter("name");//获得name
		List<String> booklist = null;
		HttpSession session = request.getSession();
		Object shopingcart = session.getAttribute("shoppingcart");
		if (shopingcart == null) {
			booklist = new ArrayList<String>();
			booklist.add(bookName);
			session.setAttribute("shoppingcart", booklist);//添加到session
		} else {
			booklist = (List<String>) shopingcart;
			booklist.add(bookName);
			session.setAttribute("shoppingcart", booklist);//添加到session
		}
		//返回购物界面
		response.sendRedirect("productList.html");
	}
  
  GoPayServlet.java
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		
		Object shoppingcart = session.getAttribute("shoppingcart");
		class  Book {
			String bookName;
			int P;
			Book(String bookName,int P){
				this.bookName = bookName;
				this.P = P;
			}
			public String getBookName() {
				return bookName;
			}
			public int getBookP() {
				return P;
			}
		}
		Book[] books = {new Book("C",60),new Book("Java",40),new Book("Pythin",50),new Book("VB",20)};//new Book("name",20);
		//前往支付  界面一
		if (shoppingcart == null) {//理论上不会出现这种情况
			//没有可支付的东西
			writer.write("You haven't get anyting!<br/>Go for someting!");
			//返回购物
			writer.write("<form action=\"productList.html\">" + 
					"Back to shopping<input type=\"submit\" />" + 
					"</form>");
		} else {
			//有可支付的东西
			List<String> booklist = (List<String>) shoppingcart;//shoppingcart的数组
			int Pay = 0;
			//展示购物的内容
			writer.write("Id  BookName            Pay <br/>");
			for (int i = 0; i < booklist.size(); i++) {
				writer.write(i + ": " + booklist.get(i).toString() + "   ");
				for (int k = 0; k < books.length; k++) {
					if(booklist.get(i).toString().equals(books[k].getBookName())) {
						writer.write(books[k].getBookP() + "<br />");
						Pay = Pay + books[k].getBookP();
					}
				}
			}
			//支付总金额
			writer.write("All Pay is :"+ Pay + "<br />");
			//返回购物
			writer.write("<form action=\"productList.html\">" + 
					"Back to shopping<input type=\"submit\" />" + 
					"</form>");
			//进行支付
			writer.write("<form action=\"PayOver.html\">" + 
					"Pay New<input type=\"submit\" />" + 
					"</form>");
		}
	}
  
  loginCheckServlet.java
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		//注册功能   没有界面的展示  由register界面跳转至此
		String parameter = request.getParameter("isSave");
		String username = request.getParameter("username");
		Cookie cookie = new Cookie("userName", username);
		response.addCookie(cookie);
		if ("on".equals(parameter))
			cookie.setMaxAge(60 * 60 * 24 * 7);
		response.sendRedirect("login.html");//返回login界面

	}
  
  loginCheckSerclet.java
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
		Cookie[] cookies = request.getCookies();
		String userName = request.getParameter("username");
		//判断登陆的用户是否合法   合法 前往购物界面   否则前往注册界面   由login界面 链接至此 
		if (cookies == null) {//判断是否有cookies
			//writer.write("null cookies!01");
			response.sendRedirect("register.html");
		} else {
			String cookieValue = null;
			for (Cookie cookie : cookies) {//查找用户名
				if (userName.equals(cookie.getValue())) {
					cookieValue = cookie.getValue();
					break;
				}
			}
			if (cookieValue == null)//没有此用户
				//writer.write("Null this userName!Go to register!02");
				response.sendRedirect("register.html");
			else//查找到用户
				//writer.write("Go to prductList!03");
				response.sendRedirect("productList.html");//登陆成功 前往购物列表界面
		}
	}
  
  ShowShoppingCarServlet.java
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		Object shoppingcart = session.getAttribute("shoppingcart");
		class  Book {
			String bookName;
			int P;
			Book(String bookName,int P){
				this.bookName = bookName;
				this.P = P;
			}
			public String getBookName() {
				return bookName;
			}
			public int getBookP() {
				return P;
			}
		}
		Book[] books = {new Book("C",60),new Book("Java",40),new Book("Pythin",50),new Book("VB",20)};//new Book("name",20);
		//！！！！！！购物车
		
		if (shoppingcart == null) {
			//没有购物
			writer.write("No Selected......<br />");
			//展示最终支付金额
			writer.write("All Pay is : 0 <br />");
			//返回购物
			writer.write("<form action=\"productList.html\">" + 
					"Back to shopping<input type=\"submit\" />" + 
					"</form>");
			//立即支付
			writer.write("<form action=\"GoPayServlet\">" + 
					"Pay New<input type=\"submit\" />" + 
					"</form>");
		} else {
			//有购物
			//展示购物内容
			writer.write("your Selected:<br />");
			List<String> booklist = (List<String>) shoppingcart;//shoppingcart的数组
			int Pay = 0;
			for (int i = 0; i < booklist.size(); i++) {
				writer.write(i + ": " + booklist.get(i).toString() + "<br />");
				for (int k = 0; k < books.length; k++) {
					if(booklist.get(i).toString().equals(books[k].getBookName())) {
						Pay = Pay + books[k].getBookP();
					}
				}
			}
			//展示最终支付金额
			writer.write("All Pay is :"+ Pay + "<br />");
			//返回购物
			writer.write("<form action=\"productList.html\">" + 
					"Back to shopping<input type=\"submit\" />" + 
					"</form>");
			//立即支付
			writer.write("<form action=\"GoPayServlet\">" + 
					"Pay New<input type=\"submit\" />" + 
					"</form>");
		}
	}
