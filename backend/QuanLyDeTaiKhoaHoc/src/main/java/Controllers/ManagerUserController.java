package Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ManagementStaffDAO;
import Models.ManagementStaff;
@WebServlet("/ManagerUserController/*")
public class ManagerUserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    private ManagementStaffDAO managementStaffDAO = new ManagementStaffDAO();

    public void init() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String action = request.getPathInfo();
        try {
            switch (action) {
                case "/showinformanger":
                	getMangerByCode(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void getMangerByCode(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
    	response.sendRedirect(request.getContextPath()+"/ProjectManager/Profile/Profile.jsp");
    }

}
