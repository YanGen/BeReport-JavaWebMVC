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
 * Servlet implementation class login
 */
@WebServlet("/loginservlet")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().write("The GET method is no allowed!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDAOJdbcimpl userDAOJdbcimpl = new UserDAOJdbcimpl();
		System.out.println("账号:"+username);
		System.out.println("密码:"+password);
		User num = userDAOJdbcimpl.get(username);
		PrintWriter out = response.getWriter();
		System.out.println("查询出来的账号:"+num.getUsername());
		System.out.println("查询对应的密码:"+num.getPassword());
		if(num.getPassword()==(password)){
			out.print("password Error");
		}else if (num.getUsername()==null){
			out.print("user no created");
		}else{
			if(request.getSession().getAttribute("username")!=null){
				response.getWriter().write("请先注册！");
			}else{
			User user = userDAOJdbcimpl.get(username);
			System.out.println(username);
			request.getSession().setAttribute("LoginUser",username);
			request.getSession().setAttribute("UserEmail", user.getEmail());
			request.getSession().setAttribute("IsManager", user.getManager());
			response.getWriter().write("登录成功");
			System.out.println(request.getParameter("IsIndex"));
			if(request.getParameter("IsIndex").equals("T")){
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}else if(request.getParameter("IsIndex").equals("N")){
				request.getSession().setAttribute("refresh",1);
				response.sendRedirect(request.getContextPath()+"/chatwin.jsp");
			}else{
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}
			}
		}
		
//		request.setAttribute("LoginUser", );
	}

}
