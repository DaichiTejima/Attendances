package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.EmployeesDAO;

/**
 * Servlet implementation class EmployeesRegisterServlet
 */
@WebServlet("/employees-register")
public class EmployeesRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("employees-register.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String employee_id = request.getParameter("employee_id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String ageStr= request.getParameter("age");
	
		EmployeesDAO dao = new EmployeesDAO();
		String errorMessage = null;
		int age = 0;
		if (employee_id == null || employee_id.isEmpty() ||
	            password == null || password.isEmpty() ||
	            name == null || name.isEmpty() ||
	            ageStr == null || ageStr.isEmpty()) {

	            errorMessage = "全てのフィールドを入力してください。";
	        } else {
	            try {
	                age = Integer.parseInt(ageStr);
	            } catch (NumberFormatException e) {
	                errorMessage = "年齢は数字で入力してください。";
	            }
	        }
			

        if (errorMessage == null) {
			try {
				int result = dao.registerEmployees(employee_id, password, name, age);
				
				if (result > 0) {
					response.sendRedirect("menu.jsp");
					return;
				} 
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				errorMessage = "システムエラーが発生しました。";
			}
        }
				request.setAttribute("errorMessage", errorMessage);
				RequestDispatcher rd = request.getRequestDispatcher("employees-register.jsp");
				rd.forward(request, response);
	}

}
