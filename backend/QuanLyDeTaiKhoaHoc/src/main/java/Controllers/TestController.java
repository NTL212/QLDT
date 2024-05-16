package Controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ManagementStaffDAO;
import DAO.AccountDAO;
import DAO.LecturerDAO;
import DAO.ManagementStaffDAO;
import DAO.AdminDAO;
import Models.Account;
import Models.Lecturer;
import Models.ManagementStaff;
import Models.Admin;

@WebServlet("/api")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountDAO accDAO;
	private LecturerDAO lecturerDAO;

	private ManagementStaffDAO mgtStaffDAO;
	private AdminDAO adminDAO;

	public void init() {
		accDAO = new AccountDAO();
		lecturerDAO = new LecturerDAO();
		mgtStaffDAO = new ManagementStaffDAO();
		adminDAO = new AdminDAO();
	}

	public TestController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("Login/Login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		authenticate(request, response);
	}

	private void authenticate(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Account loginData = new Account(username, password);
		try {
			Account acc = accDAO.validate(loginData);
			if (acc == null) {
				request.setAttribute("errMsg", "Tên đăng nhập hoặc mật khẩu không đúng!");
				request.setAttribute("loginData", loginData);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Login/Login.jsp");
				dispatcher.forward(request, response);
				return;
			}
			HttpSession session = request.getSession();
			session.setAttribute("userRole", acc.getRole());
			if (acc.getRole().equals("ROLE_ADMIN")) {
				Admin admin = adminDAO.selectByAdCode(username);
				session.setAttribute("user", admin);
				response.sendRedirect(request.getContextPath() + "/Admin/Home/Home.jsp");
			} else if (acc.getRole().equals("ROLE_MGT_STAFF")) {
				ManagementStaff manager = mgtStaffDAO.selectByEmpCode(username);
				session.setAttribute("user", manager);
				response.sendRedirect(request.getContextPath() + "/manager-notification/getNotify");
			} else if (acc.getRole().equals("ROLE_LECT")) {
				Lecturer lecturer = lecturerDAO.selectLecturerByLectCode(username);
				session.setAttribute("user", lecturer);
				response.sendRedirect(request.getContextPath() + "/lecturer-project/list");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
