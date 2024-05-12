package Controllers;

import java.io.BufferedReader;
import java.io.IOException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.AccountDAO;
import DAO.LecturerDAO;
import Models.Account;
import Models.Lecturer;
import DAO.AdminDAO;
import DAO.FalculityDAO;
import Models.ManagementStaff;
import Models.Project;
import Util.CustomTimeGson;
import Util.FormDataReader;
import Util.JsonResponse;
import DAO.ManagementStaffDAO;
import DTO.ProjectDTO;
import Models.Admin;
import Models.Falculity;

@WebServlet("/admincontroller/*")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AccountDAO accountDAO = new AccountDAO();
	private Account account = new Account();
	private LecturerDAO lecturerDAO = new LecturerDAO();
	private AdminDAO AdminDAO = new AdminDAO();
	private FalculityDAO falculityDAO =  new FalculityDAO();
	private ManagementStaffDAO managementStaffDAO = new ManagementStaffDAO();
	private Gson gson;

	public AdminController() {
		super();
	}

	public void init() {
		CustomTimeGson customGson = new CustomTimeGson();
		gson = customGson.createGson();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getPathInfo();
		try {
			switch (action) {
			case "/api/listadmin":
				listadmin(request, response);
				break;
			case "/api/listlecturer":
				listlecturer(request, response);
				break;
			case "/api/listmanager":
				listmanager(request, response);
				break;
			case "/api/insertlecturer":
				insertlecturer(request, response);
				break;
			case "/api/insertadmin":
				insertadmin(request, response);
				break;
			case "/api/insertmanager":
				insertmanager(request, response);
				break;
			case "/api/deleteacc":
				deleteacc(request, response);
				break;
			case "/api/editacc":
				editacc(request, response);
				break;
			case "/api/allfalculities":
				allfalculities(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	private void allfalculities(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
//		        List < Account > listAdmin = accountDAO.selectByAdmin("ROLE_ADMIN");
//		        request.setAttribute("listAdmin", listAdmin);
//		        RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/ADMIN/ADMIN.jsp");
//		        dispatcher.forward(request, response);
//		        
//		        String lectCode = request.getParameter("id");

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JsonResponse<List<Falculity>> jsonResponse = null;
		try {
			List<Falculity> list = falculityDAO.getAllFalculities();
			jsonResponse = new JsonResponse<List<Falculity>>(true, HttpServletResponse.SC_OK, "Thành công!", list);
		} catch (Exception e) {
			jsonResponse = new JsonResponse<List<Falculity>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra.", null);
		}
		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
	}

	private void listadmin(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
//		        List < Account > listAdmin = accountDAO.selectByAdmin("ROLE_ADMIN");
//		        request.setAttribute("listAdmin", listAdmin);
//		        RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/ADMIN/ADMIN.jsp");
//		        dispatcher.forward(request, response);
//		        
//		        String lectCode = request.getParameter("id");

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JsonResponse<List<Account>> jsonResponse = null;
		try {
			List<Account> listAdmin = accountDAO.selectByAdmin("ROLE_ADMIN");
			jsonResponse = new JsonResponse<List<Account>>(true, HttpServletResponse.SC_OK, "Thành công!", listAdmin);
		} catch (Exception e) {
			jsonResponse = new JsonResponse<List<Account>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra.", null);
		}
		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
	}

	private void listlecturer(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
//		        List < Account > listLecturer = accountDAO.selectByLecturer("ROLE_LECT");
//		        request.setAttribute("listLect", listLecturer);
//		        RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/Lecturer/Lecturer.jsp");
//		        dispatcher.forward(request, response);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JsonResponse<List<Lecturer>> jsonResponse = null;
		List<Lecturer> lecturers = new ArrayList<>();
		try {
			List<Account> listLecturer = accountDAO.selectByLecturer("ROLE_LECT");
			for (Account account : listLecturer) {
				lecturers.add(lecturerDAO.selectLecturerByLectCode(account.getUsername()));
			}
			jsonResponse = new JsonResponse<List<Lecturer>>(true, HttpServletResponse.SC_OK, "Thành công!", lecturers);
		} catch (Exception e) {
			jsonResponse = new JsonResponse<List<Lecturer>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra.", null);
		}
		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
	}

	private void listmanager(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
//		List<Account> listManager = accountDAO.selectByManager("ROLE_MGT_STAFF");
//		request.setAttribute("listManager", listManager);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/UserManggement/UserManggement.jsp");
//		dispatcher.forward(request, response);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JsonResponse<List<ManagementStaff>> jsonResponse = null;
		List<ManagementStaff> managementStaffs = new ArrayList<>();
		try {
			List<Account> listManager = accountDAO.selectByManager("ROLE_MGT_STAFF");
			for (Account account : listManager) {
				managementStaffs.add(managementStaffDAO.selectByEmpCode(account.getUsername()));
			}
			jsonResponse = new JsonResponse<List<ManagementStaff>>(true, HttpServletResponse.SC_OK, "Thành công!",
					managementStaffs);
		} catch (Exception e) {
			jsonResponse = new JsonResponse<List<ManagementStaff>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra.", null);
		}
		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
	}

	private void editacc(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		FormDataReader reader = new FormDataReader(request);
		Map<String, String> formData = reader.getData();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String username = formData.get("adCode");
		String password = formData.get("password");
		String name = formData.get("name");
		LocalDate birthday = LocalDate.parse(formData.get("birthday"));
		String address = formData.get("address");
		String idNum = formData.get("idNum");
		String phoneNum = formData.get("phoneNum");
		String email = formData.get("email");
		String sex = formData.get("sex");
		String falculityCode = formData.get("falculityCode");

		JsonResponse<String> jsonResponse = null;
		Account acc = accountDAO.selectByUsername(username);
		if (acc != null) {
			try {
				if (acc.getRole().contains("ROLE_ADMIN")) {
					if (AdminDAO.selectByAdCode(username) != null) {
						accountDAO.editAccountByUsername(new Account(username, password, "ROLE_ADMIN"));
						AdminDAO.updateAdmin(new Admin(username, name, birthday, idNum, phoneNum, email, sex, address));
						jsonResponse = new JsonResponse<>(true, HttpServletResponse.SC_CREATED, "Thành công!", null);
					} else {
						jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_CONFLICT, "Tài khoản không tồn tại", null);
					}
				} else if (acc.getRole().contains("ROLE_MGT_STAFF")) {
					if (managementStaffDAO.selectByEmpCode(username) != null) {
						accountDAO.editAccountByUsername(new Account(username, password, "ROLE_MGT_STAFF"));
						managementStaffDAO.updateManagementStaff(
								new ManagementStaff(username, name, birthday, idNum, phoneNum, email, sex, address));
						jsonResponse = new JsonResponse<>(true, HttpServletResponse.SC_CREATED, "Thành công!", null);
					} else {
						jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_CONFLICT, "Tài khoản không tồn tại", null);
					}
				} else if (acc.getRole().contains("ROLE_LECT")) {
					if (lecturerDAO.selectLecturerByLectCode(username) != null) {
						accountDAO.editAccountByUsername(new Account(username, password, "ROLE_LECT"));
						lecturerDAO.updateLecturer(new Lecturer(username, name, birthday, address, idNum, phoneNum,
								email, sex, falculityCode));
						jsonResponse = new JsonResponse<>(true, HttpServletResponse.SC_CREATED, "Thành công!", null);
					} else {
						jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_CONFLICT, "Tài khoản không tồn tại",
								null);
					}
				}

			} catch (Exception e) {
				jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra.",
						null);
				e.printStackTrace();
			}
		}

		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
	}

	private void deleteacc(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String username = request.getParameter("accCode");
		AccountDAO accountDAO = new AccountDAO();
		Account account = new Account();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		// response.sendRedirect("listadmin");

		JsonResponse<String> jsonResponse = null;
		try {
			Account acc = accountDAO.selectByUsername(username);
			if (acc != null && acc.getRole() != "ROLE_ADMIN") {
				boolean d = false;
				if (acc.getRole().contains("ROLE_MGT_STAFF")) {
					d = managementStaffDAO.deleteManagementStaffById(username);
				} else if (acc.getRole().contains("ROLE_LECT")) {
					d = lecturerDAO.deleteLectureById(username);
				}
				if (d) {
					account.setUsername(username);
					accountDAO.deleteAccountByUsername(account);

					jsonResponse = new JsonResponse<String>(true, HttpServletResponse.SC_ACCEPTED, "Thành công!", null);
				} else {
					jsonResponse = new JsonResponse<String>(true, HttpServletResponse.SC_CONFLICT,
							"Xóa không thành công", null);
				}

			} else {
				jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_CONFLICT,
						"Tài khoản không tồn tại", null);
			}

		} catch (Exception e) {
			jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra.", null);
			e.printStackTrace();
		}
		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
	}

	private void insertlecturer(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		FormDataReader reader = new FormDataReader(request);
		Map<String, String> formData = reader.getData();

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String username = formData.get("magv");
		String password = formData.get("password");
		String lecturerCode = formData.get("magv");
		String name = formData.get("name");
		LocalDate birthday = LocalDate.parse(formData.get("birthday"));
		String address = formData.get("address");
		String idNum = formData.get("idNum");
		String phoneNum = formData.get("phoneNum");
		String email = formData.get("email");
		String sex = formData.get("sex");
		String falculityCode = formData.get("falculityCode");

		Account account = new Account(username, password, "ROLE_LECT");

		Lecturer lecturer = new Lecturer(lecturerCode, name, birthday, address, idNum, phoneNum, email, sex,
				falculityCode);

		JsonResponse<String> jsonResponse = null;
		try {
			if (lecturerDAO.selectLecturerByLectCode(lecturerCode) == null) {
				accountDAO.insertAccount(account);
				lecturerDAO.insertLecturer(lecturer);
				jsonResponse = new JsonResponse<String>(true, HttpServletResponse.SC_CREATED, "Thành công!", null);
			} else {
				jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_CONFLICT, "Mã gv bị trùng", null);
			}

		} catch (Exception e) {
			jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra.", null);
			e.printStackTrace();
		}
		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);

	}

	private void insertadmin(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		FormDataReader reader = new FormDataReader(request);
		Map<String, String> formData = reader.getData();

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String username = formData.get("adCode");
		String password = formData.get("password");
		String adCode = formData.get("adCode");
		String name = formData.get("name");
		LocalDate birthday = LocalDate.parse(formData.get("birthday"));
		String address = formData.get("address");
		String idNum = formData.get("idNum");
		String phoneNum = formData.get("phoneNum");
		String email = formData.get("email");
		String sex = formData.get("sex");

		Account account = new Account(username, password, "ROLE_ADMIN");
		Admin admin = new Admin(adCode, name, birthday, idNum, phoneNum, email, sex, address);

		JsonResponse<String> jsonResponse = null;
		try {
			if (AdminDAO.selectByAdCode(adCode) == null) {
				accountDAO.insertAccount(account);
				AdminDAO.insertAdmin(admin);
				jsonResponse = new JsonResponse<String>(true, HttpServletResponse.SC_CREATED, "Thành công!", null);
			} else {
				jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_CONFLICT, "Mã bị trùng", null);
			}

		} catch (Exception e) {
			jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra.", null);
			e.printStackTrace();
		}
		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
	}

	private void insertmanager(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		FormDataReader reader = new FormDataReader(request);
		Map<String, String> formData = reader.getData();

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String username = formData.get("empCode");
		String password = formData.get("password");
		String empCode = formData.get("empCode");
		String name = formData.get("name");
		LocalDate birthday = LocalDate.parse(formData.get("birthday"));
		String address = formData.get("address");
		String idNum = formData.get("idNum");
		String phoneNum = formData.get("phoneNum");
		String email = formData.get("email");
		String sex = formData.get("sex");
		Account account = new Account(username, password, "ROLE_MGT_STAFF");
		ManagementStaff manager = new ManagementStaff(empCode, name, birthday, idNum, phoneNum, email, sex, address);

		JsonResponse<String> jsonResponse = null;
		try {
			if (managementStaffDAO.selectByEmpCode(empCode) == null) {
				accountDAO.insertAccount(account);
				managementStaffDAO.insertManagementStaff(manager);
				jsonResponse = new JsonResponse<String>(true, HttpServletResponse.SC_CREATED, "Thành công!", null);
			} else {
				jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_CONFLICT, "Mã bị trùng", null);
			}

		} catch (Exception e) {
			jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra.", null);
			e.printStackTrace();
		}
		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
