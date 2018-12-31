package com.report.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.report.dao.impl.ReportDAOJdbcimpl;
import com.report.domain.Report;

/**
 * Servlet implementation class dataline
 */
@WebServlet("/timeline")
public class dataline extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dataline() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getSession().getAttribute("LoginUser").toString();
		ReportDAOJdbcimpl reportDaoJdbcimpl = new ReportDAOJdbcimpl();
		ArrayList<Report> reportObjectList = (ArrayList<Report>) reportDaoJdbcimpl.getListByUsername(username);
		Collections.reverse(reportObjectList);
		request.setAttribute("reportObjectList", reportObjectList);
		request.getRequestDispatcher(request.getContextPath()+"/timeline.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
