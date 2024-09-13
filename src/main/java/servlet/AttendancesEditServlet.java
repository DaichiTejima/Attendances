package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
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
 * Servlet implementation class AttendancesEdit
 */
@WebServlet("/attendances-edit")
public class AttendancesEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendancesEditServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		AttendancesBean attendances = null;
		
		AttendancesDAO dao = new AttendancesDAO();
		
		try {
			attendances = dao.getAttendancesOne(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("attendances", attendances);
		RequestDispatcher rd = request.getRequestDispatcher("attendances-edit.jsp");
		rd.forward(request, response);
		
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		String start_timeString = request.getParameter("start_time");
		if (start_timeString.length() == 5) {
		    start_timeString += ":00";
		}
		Time start_time = Time.valueOf(start_timeString);
		
		String end_timeString = request.getParameter("end_time");
		if (end_timeString.length() == 5) {
		    end_timeString += ":00"; 
		}
		Time end_time = Time.valueOf(end_timeString);
		
		String break_timeString = request.getParameter("break_time");
		if (break_timeString.length() == 5) {
		    break_timeString += ":00";
		}
		Time break_time = Time.valueOf(break_timeString);

		String work_timeString = request.getParameter("work_time");
		if (work_timeString.length() == 5) {
		    work_timeString += ":00";
		}
		Time work_time = Time.valueOf(work_timeString);
		
		String over_timeString = request.getParameter("over_time");
		if (over_timeString.length() == 5) {
		    over_timeString += ":00";
		}
		Time over_time = Time.valueOf(over_timeString);
		
		
		AttendancesDAO dao = new AttendancesDAO();
		
		try {
			dao.editAttendances(id, start_time, end_time, break_time, work_time, over_time);
			
			List<AttendancesBean> attendancesList = dao.getAttendancesList();
			request.setAttribute("attendancesList", attendancesList);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("attendances-list.jsp");
		rd.forward(request, response);
	
	}

}
