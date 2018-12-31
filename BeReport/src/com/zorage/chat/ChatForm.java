package com.zorage.chat;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
/**
 * Servlet implementation class ChatForm
 */
@WebServlet("/chatform")
public class ChatForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatForm() {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String username = (String) request.getSession().getAttribute("LoginUser");
		String manager = (String) request.getSession().getAttribute("IsManager");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式
		Date nowDate = new Date();//得到当前时间
		String date = sdf.format(nowDate );
		String content = request.getParameter("massage");
		System.out.println(content);
		ChatDao chatDao = new ChatDao();
		chatDao.CreateMassage(username, date, content,manager);
		response.setHeader("refresh", "0.5;url=/BeReport/chatwin.jsp");
	}

}
