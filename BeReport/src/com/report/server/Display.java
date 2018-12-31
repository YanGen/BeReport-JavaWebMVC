package com.report.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.report.dao.impl.ReportDAOJdbcimpl;
import com.report.domain.Report;

/**
 * Servlet implementation class Display
 */
@WebServlet("/display")
public class Display extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Display() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		if(username != request.getSession().getAttribute("LoginUser").toString()){
			response.getWriter().write("用户名错误");
		}
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		ReportDAOJdbcimpl reportDaoJdbcimpl = new ReportDAOJdbcimpl();
		long num = reportDaoJdbcimpl.getCountWithInfo(id);
		if(num == 0){
			response.getWriter().write("查无此数据项");
		}
		Report report = reportDaoJdbcimpl.get(id);
		if(report.getType().toString()!=type){
			response.getWriter().write("类型参数有误!");
		}
		request.setAttribute("jdata", report.getJdata().toString().replace("\"", ""));
		request.getRequestDispatcher("/be"+type.toString()+".jsp").forward(request,response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String url = request.getParameter("url");
		response.sendRedirect(url);
	}

}
