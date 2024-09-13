package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AttendancesDAO;
import model.entity.AttendancesBean;

/**
 * Servlet implementation class AttendancesSearchServlet
 */
@WebServlet("/attendances-search")
public class AttendancesSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendancesSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String attendancesDate = request.getParameter("date");
		
		Date date = null;
		try {
			date = Date.valueOf(attendancesDate);
		} catch (IllegalArgumentException e){
			return;
		}
		
		AttendancesDAO dao = new AttendancesDAO();
		
		List<AttendancesBean> attendancesList = new ArrayList<>();
		
		try {
			 attendancesList = dao.searchAttendances(date);
		
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
			
		request.setAttribute("attendancesList", attendancesList);
		
		RequestDispatcher rd = request.getRequestDispatcher("attendances-search.jsp");
		rd.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("attendances-list.jsp");
		rd.forward(request, response);
	}
}
