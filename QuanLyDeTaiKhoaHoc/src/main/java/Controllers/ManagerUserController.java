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

import com.google.gson.Gson;

import DAO.ManagementStaffDAO;
import DTO.NoticeDTO;
import Models.ManagementStaff;
import Util.CustomTimeGson;
import Util.JsonResponse;

@WebServlet("/ManagerUserController/*")
public class ManagerUserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ManagementStaffDAO managementStaffDAO = new ManagementStaffDAO();
	private Gson gson;
	public void init() {
		CustomTimeGson customGson = new CustomTimeGson();
		gson = customGson.createGson();
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
		String managerCode = request.getParameter("id");
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        JsonResponse<ManagementStaff> jsonResponse  = null;
        try {
        	ManagementStaff manager = managementStaffDAO.selectByEmpCode(managerCode);
        	if(manager!=null) {
        		jsonResponse = new JsonResponse<ManagementStaff>(true, HttpServletResponse.SC_OK, "Thành công!", manager);
        	}
        	else {
        		jsonResponse = new JsonResponse<ManagementStaff>(false, HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy nhân viên", manager);
        	}          
        } catch (Exception e) {
            jsonResponse = new JsonResponse<ManagementStaff>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            								"Có lỗi xảy ra.", null);
            e.printStackTrace();
        }
        String jsonOutput = gson.toJson(jsonResponse);
        response.getWriter().write(jsonOutput);
	
	}

}
