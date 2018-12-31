package com.report.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.report.dao.impl.UserDAOJdbcimpl;
import com.report.domain.User;

/**
 * Servlet implementation class register
 */
@WebServlet("/registerservlet")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		UserDAOJdbcimpl userDAOJdbcimpl = new UserDAOJdbcimpl();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String confirm_password = request.getParameter("confirm_password");
		String agree = request.getParameter("agree");
		PrintWriter out = response.getWriter();
		if(username.length()>8 || username.length()<2){
			out.println("用户名格式有误！");
		}else if(password.length()>10|| password.length()<6){
			out.println("密码格式有误");
		}
		if(password.equals(confirm_password)){
			
		}else{
			out.print("输入密码不一致！");
		}
		if(agree==null){
			out.println("有意见不给注册！");
		}
		long num = userDAOJdbcimpl.getCountWithUsername(username);
		if(num == 0){
			User user = new User();
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password);
			userDAOJdbcimpl.save(user);
			response.getWriter().write("注册成功，3秒后跳转登录页面！");
		    response.setHeader("refresh", "3;url=login.jsp");
		}else{
			out.println("用户已存在");
		}
		
	}

}
