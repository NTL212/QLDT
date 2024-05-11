package Controllers;

import java.io.IOException;






import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AccountDAO;
import DAO.LecturerDAO;
import Models.Account;
import Models.Lecturer;
import DAO.AdminDAO;
import Models.ManagementStaff;
import DAO.ManagementStaffDAO;
import Models.Admin;


@WebServlet("/admincontroller/*")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private AccountDAO accountDAO = new AccountDAO();
    private Account account = new Account();
    private LecturerDAO lecturerDAO = new LecturerDAO();
    private AdminDAO AdminDAO = new AdminDAO();
    private ManagementStaffDAO managementStaffDAO = new ManagementStaffDAO();

    public AdminController() {
        super();
    }
    public void init() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getPathInfo();
        try {
            switch (action) {
                case "/listadmin":
                	listadmin(request, response);
                    break;
                case "/listlecturer":
                	listlecturer(request, response);
                    break;
                case "/listmanager":
                	listmanager(request, response);
                    break;
                case "/insertlecturer":
                	insertlecturer(request, response);
                	break;
                case "/insertadmin":
                	insertadmin(request, response);
                	break;
                case "/insertmanager":
                	insertmanager(request, response);
                	break;
                case "/deleteacc":
                	deleteacc(request, response);
                	break;
                case "/editacc":
                	editacc(request, response);
                	break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}
	private void listadmin(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		        List < Account > listAdmin = accountDAO.selectByAdmin("ROLE_ADMIN");
		        request.setAttribute("listAdmin", listAdmin);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/ADMIN/ADMIN.jsp");
		        dispatcher.forward(request, response);
		    }
	private void listlecturer(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		        List < Account > listLecturer = accountDAO.selectByLecturer("ROLE_LECT");
		        request.setAttribute("listLect", listLecturer);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/Lecturer/Lecturer.jsp");
		        dispatcher.forward(request, response);
		    }
	private void listmanager(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		        List < Account > listManager = accountDAO.selectByManager("ROLE_MGT_STAFF");
		        request.setAttribute("listManager", listManager);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/UserManggement/UserManggement.jsp");
		        dispatcher.forward(request, response);
		    }
    private void editacc(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        String username = request.getParameter("username");
        Account account = accountDAO.selectByUsername(username);
        request.setAttribute("account", account);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/EditAccount/EditAccount.jsp");
        dispatcher.forward(request, response);
    }
    
    private void deleteacc(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
    	String username = request.getParameter("adCode");
        AccountDAO accountDAO = new AccountDAO();
        Account account = new Account();
        account.setUsername(username);
        accountDAO.deleteAccountByUsername(account);
        //response.sendRedirect("listadmin");
    }
    
    private void insertlecturer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String username = request.getParameter("magv");
        String password = request.getParameter("password");
        String lecturerCode = request.getParameter("magv");
        String name = request.getParameter("name");
        LocalDate birthday = LocalDate.parse(request.getParameter("birthday"));
        String address = request.getParameter("address");
        String idNum = request.getParameter("idNum");
        String phoneNum = request.getParameter("phoneNum");
        String email = request.getParameter("email");
        String sex = request.getParameter("sex");
        String falculityCode = request.getParameter("falculityCode");
        Account account = new Account(username, password, "ROLE_LECT");
        Lecturer lecturer = new Lecturer(lecturerCode, name, birthday, address, idNum, phoneNum, email, sex, falculityCode);
        accountDAO.insertAccount(account);
        lecturerDAO.insertLecturer(lecturer);   
        response.sendRedirect(request.getContextPath() + "/admincontroller/listlecturer");
    }
    private void insertadmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String username = request.getParameter("adCode");
        String password = request.getParameter("password");
        String adCode = request.getParameter("adCode");
        String name = request.getParameter("name");
        LocalDate birthday = LocalDate.parse(request.getParameter("birthday"));
        String address = request.getParameter("address");
        String idNum = request.getParameter("idNum");
        String phoneNum = request.getParameter("phoneNum");
        String email = request.getParameter("email");
        String sex = request.getParameter("sex");
        Account account = new Account(username, password, "ROLE_ADMIN");
        Admin admin = new Admin(adCode, name, birthday, idNum, phoneNum, email, sex, address);
        accountDAO.insertAccount(account);
        AdminDAO.insertAdmin(admin);   
        response.sendRedirect(request.getContextPath() + "/admincontroller/listadmin");
    }
    private void insertmanager(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String username = request.getParameter("empCode");
        String password = request.getParameter("password");
        String empCode = request.getParameter("empCode");
        String name = request.getParameter("name");
        LocalDate birthday = LocalDate.parse(request.getParameter("birthday"));
        String address = request.getParameter("address");
        String idNum = request.getParameter("idNum");
        String phoneNum = request.getParameter("phoneNum");
        String email = request.getParameter("email");
        String sex = request.getParameter("sex");
        Account account = new Account(username, password, "ROLE_MGT_STAFF");
        ManagementStaff manager = new ManagementStaff(empCode, name, birthday, idNum, phoneNum, email, sex, address);
        accountDAO.insertAccount(account);
		managementStaffDAO.insertManagementStaff(manager);   
        response.sendRedirect(request.getContextPath() + "/admincontroller/listmanager");
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
