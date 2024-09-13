package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.EmployeesDAO;
import model.entity.EmployeesBean;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	
		
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request,  response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		EmployeesDAO dao = new EmployeesDAO();
		String errorMessage = null;
		
		String employee_id = request.getParameter("employee_id");
		String password = request.getParameter("password");
		
		try {
			List<EmployeesBean>  employeesList = dao.getEmployees(employee_id, password);
			
			if (employeesList != null && !employeesList.isEmpty()) {
				HttpSession session = request.getSession();
				session.setAttribute("employees", employeesList.get(0));
				response.sendRedirect("menu.jsp");
				return;
				
				
			} else {
				errorMessage = "従業員IDまたはパスワードが間違えています。";
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("errorMessage", errorMessage);
		
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request,  response);
	}

}

