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
import DAO.MajorDAO;
import Models.Account;
import Models.Lecturer;
import Models.Major;
import DAO.AdminDAO;
import DAO.ClassDAO;
import DAO.FalculityDAO;
import Models.ManagementStaff;
import Models.Project;
import Models.Student;
import Util.CustomTimeGson;
import Util.FormDataReader;
import Util.JsonResponse;
import DAO.ManagementStaffDAO;
import DAO.StudentDAO;
import DTO.AdminDTO;
import DTO.LectureDTO;
import DTO.ManagerDTO;
import DTO.ProjectDTO;
import DTO.StudentDTO;
import Models.Admin;
import Models.Falculity;
import Models.Class;

@WebServlet("/admincontroller/*")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AccountDAO accountDAO = new AccountDAO();
	private Account account = new Account();
	private LecturerDAO lecturerDAO = new LecturerDAO();
	private AdminDAO AdminDAO = new AdminDAO();
	private FalculityDAO falculityDAO =  new FalculityDAO();
	private StudentDAO studentDAO = new StudentDAO();
	private ManagementStaffDAO managementStaffDAO = new ManagementStaffDAO();
	private MajorDAO majorDAO = new MajorDAO();
	private ClassDAO classDAO = new ClassDAO();
	private AdminDAO adminDAO = new AdminDAO();
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
			case "/api/adminprofile":
				adminprofile(request, response);
				break;
			case "/api/listlecturer":
				listlecturer(request, response);
				break;
			case "/api/listmanager":
				listmanager(request, response);
				break;			
			case "/api/liststudent":
				liststudent(request, response);
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
			case "/api/insertstudent":
				insertstudent(request, response);
				break;
			case "/api/deleteacc":
				deleteacc(request, response);
				break;
			case "/api/deletestudent":
				deleteStudent(request, response);
				break;
			case "/api/editacc":
				editacc(request, response);
				break;
			case "/api/updatestudent":
				updateStudent(request, response);
				break;
			case "/api/allfalculities":
				allfalculities(request, response);
				break;
			case "/api/getlecturerbyid":
				getLectureDetail(request, response);
				break;
			case "/api/getmanagerbyid":
				getManagerDetail(request, response);
				break;
			case "/api/getstudentbyid":
				getDetailByStudentId(request, response);
				break;
			case "/api/getadminbyid":
				getAdminDetail(request, response);
				break;
			case "/api/getmajorbyfalculityid":
				getListMajorByFalculityCode(request, response);
				break;
			case "/api/getclassbymajorcode":
				getListClassByMajorCode(request, response);
				break;
				
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void adminprofile(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
//		        List < Account > listAdmin = accountDAO.selectByAdmin("ROLE_ADMIN");
//		        request.setAttribute("listAdmin", listAdmin);
//		        RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/ADMIN/ADMIN.jsp");
//		        dispatcher.forward(request, response);
//		        
//		        String lectCode = request.getParameter("id");
		String adcode = request.getParameter("id");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JsonResponse<Admin> jsonResponse = null;
		try {
			Admin admin = AdminDAO.selectByAdCode(adcode);
			jsonResponse = new JsonResponse<Admin>(true, HttpServletResponse.SC_OK, "Thành Công", admin);
		} catch (Exception e) {
			jsonResponse = new JsonResponse<Admin>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra", null);
		}
		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
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
			jsonResponse = new JsonResponse<List<Falculity>>(true, HttpServletResponse.SC_OK, "Thành Công", list);
		} catch (Exception e) {
			jsonResponse = new JsonResponse<List<Falculity>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra", null);
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
			jsonResponse = new JsonResponse<List<Account>>(true, HttpServletResponse.SC_OK, "Thành Công", listAdmin);
		} catch (Exception e) {
			jsonResponse = new JsonResponse<List<Account>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra", null);
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
		JsonResponse<List<LectureDTO>> jsonResponse = null;
		List<Lecturer> lecturers = new ArrayList<>();
		List<LectureDTO> lecturerDTOs = new ArrayList<>();
		try {
			List<Account> listLecturer = accountDAO.selectByLecturer("ROLE_LECT");
			for (Account account : listLecturer) {
				LectureDTO lect = new LectureDTO();
				lect.setAccount(account);
				lect.setLecturer(lecturerDAO.selectLecturerByLectCode(account.getUsername()));
				lecturerDTOs.add(lect);
			}
			jsonResponse = new JsonResponse<List<LectureDTO>>(true, HttpServletResponse.SC_OK, "Thành Công", lecturerDTOs);
		} catch (Exception e) {
			jsonResponse = new JsonResponse<List<LectureDTO>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra", null);
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
		JsonResponse<List<ManagerDTO>> jsonResponse = null;
		List<ManagementStaff> managementStaffs = new ArrayList<>();
		List<ManagerDTO> managerDTOs = new ArrayList<>();
		
		try {
			List<Account> listManager = accountDAO.selectByManager("ROLE_MGT_STAFF");
			for (Account account : listManager) {
				ManagerDTO managerDTO = new ManagerDTO();
				managerDTO.setAccount(account);
				managerDTO.setManagementStaff(managementStaffDAO.selectByEmpCode(account.getUsername()));
				managerDTOs.add(managerDTO);
			}
			jsonResponse = new JsonResponse<List<ManagerDTO>>(true, HttpServletResponse.SC_OK, "Thành Công",
					managerDTOs);
		} catch (Exception e) {
			jsonResponse = new JsonResponse<List<ManagerDTO>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra", null);
		}
		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
	}
	private void getListMajorByFalculityCode(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
//		List<Account> listManager = accountDAO.selectByManager("ROLE_MGT_STAFF");
//		request.setAttribute("listManager", listManager);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/UserManggement/UserManggement.jsp");
//		dispatcher.forward(request, response);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String falculityCode = request.getParameter("khoa");
		JsonResponse<List<Major>> jsonResponse = null;
		try {
			List<Major> majors = majorDAO.selectMajorByFalculityCode(falculityCode);
			jsonResponse = new JsonResponse<List<Major>>(true, HttpServletResponse.SC_OK, "Thành Công",
					majors);
		} catch (Exception e) {
			jsonResponse = new JsonResponse<List<Major>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra", null);
		}
		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
	}
	private void getListClassByMajorCode(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
//		List<Account> listManager = accountDAO.selectByManager("ROLE_MGT_STAFF");
//		request.setAttribute("listManager", listManager);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/UserManggement/UserManggement.jsp");
//		dispatcher.forward(request, response);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String majorCode = request.getParameter("manganh");
		JsonResponse<List<Class>> jsonResponse = null;
		try {
			List<Class> classes = classDAO.selectClassByMajorCode(majorCode);
			jsonResponse = new JsonResponse<List<Class>>(true, HttpServletResponse.SC_OK, "Thành Công",
					classes);
		} catch (Exception e) {
			jsonResponse = new JsonResponse<List<Class>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra", null);
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

					jsonResponse = new JsonResponse<String>(true, HttpServletResponse.SC_ACCEPTED, "Thành Công", null);
				} else {
					jsonResponse = new JsonResponse<String>(true, HttpServletResponse.SC_CONFLICT,
							"Xoá không thành công", null);
				}

			} else {
				jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_CONFLICT,
						"Tài khoản không tồn tại", null);
			}

		} catch (Exception e) {
			jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra", null);
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
				jsonResponse = new JsonResponse<String>(true, HttpServletResponse.SC_CREATED, "Thành Công", null);
			} else {
				jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_CONFLICT, "MÃ£ gv bá»‹ trÃ¹ng", null);
			}

		} catch (Exception e) {
			jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra", null);
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
				jsonResponse = new JsonResponse<String>(true, HttpServletResponse.SC_CREATED, "Thành Công", null);
			} else {
				jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_CONFLICT, "MÃ£ bá»‹ trÃ¹ng", null);
			}

		} catch (Exception e) {
			jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra", null);
			e.printStackTrace();
		}
		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
	}
//	private void insertstudent(HttpServletRequest request, HttpServletResponse response)
//		    throws SQLException, IOException, ServletException {
//		    	BufferedReader reader = request.getReader();
//		        response.setContentType("application/json");
//		        response.setCharacterEncoding("UTF-8");
//		        JsonResponse<String> jsonResponse = null;
//		    	try {
//		            Student newStudent = gson.fromJson(reader, Student.class);
//		            if (studentDAO.selectStudentByStudentCode(newStudent.getStudentCode()) == null)
//		            {
//		                studentDAO.insertStudent(newStudent);
//		                jsonResponse = new JsonResponse<String>(true, HttpServletResponse.SC_CREATED, "Thêm sinh viên thành công", null);
//		            }
//		            else {
//		                jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_CONFLICT, "Mã sinh viên đã tồn tại", null);
//		            }
//
//		        } catch (Exception e) {
//		            jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra.", null);
//		        }
//		    	response.getWriter().write(gson.toJson(jsonResponse)); 
//		    }
	private void insertstudent(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException {
	    // Use FormDataReader to read form data from the request
	    FormDataReader reader = new FormDataReader(request);
	    Map<String, String> formData = reader.getData();

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    // Extracting data from formData
	    String studentCode = formData.get("studentCode");
	    String name = formData.get("name");
	    LocalDate birthday = LocalDate.parse(formData.get("birthday"));
	    String address = formData.get("address");
	    String idNum = formData.get("idNum");
	    String phoneNum = formData.get("phoneNum");
	    String email = formData.get("email");
	    String sex = formData.get("sex");
	    String classCode = formData.get("classCode");

	    // Creating the Student object
	    Student student = new Student(name, birthday, address, idNum, phoneNum, email, sex, studentCode, classCode);

	    JsonResponse<String> jsonResponse = null;
	    try {
	        // Check if the student already exists
	        if (studentDAO.selectStudentByStudentCode(studentCode) == null) {
	            // Insert the student into the database
	            studentDAO.insertStudent(student);
	            jsonResponse = new JsonResponse<>(true, HttpServletResponse.SC_CREATED, "Thêm sinh viên thành công", null);
	        } else {
	            jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_CONFLICT, "Mã sinh viên đã tồn tại", null);
	        }
	    } catch (Exception e) {
	        jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra", null);
	        e.printStackTrace();
	    }

	    // Write the response as JSON
	    String jsonOutput = gson.toJson(jsonResponse);
	    response.getWriter().write(jsonOutput);
	}
	private void updateStudent(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException {
	    FormDataReader reader = new FormDataReader(request);
	    Map<String, String> formData = reader.getData();

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    String studentCode = formData.get("studentCode");
	    String name = formData.get("name");
	    String birthday = formData.get("birthday");
	    String address = formData.get("address");
	    String idNum = formData.get("idNum");
	    String phoneNum = formData.get("phoneNum");
	    String email = formData.get("email");
	    String sex = formData.get("sex");
	    String classCode = formData.get("classCode");

	    JsonResponse<String> jsonResponse = null;
	    try {
	        Student student = new Student(name, LocalDate.parse(birthday), address, idNum, phoneNum, email, sex, studentCode, classCode);
	        boolean isUpdated = studentDAO.updateStudent(student);
	        if (isUpdated) {
	            jsonResponse = new JsonResponse<>(true, HttpServletResponse.SC_OK, "Cập nhật thông tin sinh viên thành công", null);
	        } else {
	            jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Không thể cập nhật thông tin sinh viên", null);
	        }
	    } catch (Exception e) {
	        jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra", null);
	        e.printStackTrace();
	    }

	    String jsonOutput = gson.toJson(jsonResponse);
	    response.getWriter().write(jsonOutput);
	}
	
	



	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException {
	    // Use FormDataReader to read form data from the request
	    FormDataReader reader = new FormDataReader(request);
	    Map<String, String> formData = reader.getData();

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    // Extracting the student code from formData
	    String studentCode = formData.get("studentCode");

	    JsonResponse<String> jsonResponse = null;
	    try {
	        // Check if the student exists
	        if (studentDAO.selectStudentByStudentCode(studentCode) != null) {
	            // Delete the student from the database
	            boolean isDeleted = studentDAO.deleteStudent(studentCode);
	            if (isDeleted) {
	                jsonResponse = new JsonResponse<>(true, HttpServletResponse.SC_OK, "Xóa sinh viên thành công", null);
	            } else {
	                jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Không thể xóa sinh viên", null);
	            }
	        } else {
	            jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_NOT_FOUND, "Mã sinh viên không tồn tại", null);
	        }
	    } catch (Exception e) {
	        jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra", null);
	        e.printStackTrace();
	    }

	    // Write the response as JSON
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
				jsonResponse = new JsonResponse<String>(true, HttpServletResponse.SC_CREATED, "Thành Công", null);
			} else {
				jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_CONFLICT, "MÃ£ bá»‹ trÃ¹ng", null);
			}

		} catch (Exception e) {
			jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra", null);
			e.printStackTrace();
		}
		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
	}
	private void getLectureDetail(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		String lectCode = request.getParameter("magv");
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonResponse<LectureDTO> jsonResponse = null;
        try {
        	Lecturer lect = lecturerDAO.selectLecturerByLectCode(lectCode);
        	Account account = accountDAO.selectAccountByUsername(lectCode);
        	LectureDTO lectureDTO = new LectureDTO(lect, account);
            jsonResponse = new JsonResponse<LectureDTO>(true, HttpServletResponse.SC_OK, "Thành Công", new LectureDTO(lect,account));
        } catch (Exception e) {
            jsonResponse = new JsonResponse<LectureDTO>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            								"Có lỗi xảy ra", null);
        }
        String jsonOutput = gson.toJson(jsonResponse);
        response.getWriter().write(jsonOutput);
	}
	
	private void getManagerDetail(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		String empCode = request.getParameter("manv");
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonResponse<ManagerDTO> jsonResponse = null;
        try {
        	ManagementStaff managementStaff = managementStaffDAO.selectByEmpCode(empCode);
        	Account account = accountDAO.selectAccountByUsername(empCode);
            jsonResponse = new JsonResponse<ManagerDTO>(true, HttpServletResponse.SC_OK, "Thành Công", new ManagerDTO(managementStaff ,account));
        } catch (Exception e) {
            jsonResponse = new JsonResponse<ManagerDTO>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            								"Có lỗi xảy ra", null);
        }
        String jsonOutput = gson.toJson(jsonResponse);
        response.getWriter().write(jsonOutput);
	}
	private void getAdminDetail(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String adCode = request.getParameter("adCode");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonResponse<AdminDTO> jsonResponse = null;
        try {
            Admin admin = adminDAO.selectByAdCode(adCode);
            Account account = accountDAO.selectAccountByUsername(adCode);
            if (admin != null) {
                jsonResponse = new JsonResponse<AdminDTO>(true, HttpServletResponse.SC_OK, "Thành Công", new AdminDTO(admin, account));
            } else {
                jsonResponse = new JsonResponse<AdminDTO>(false, HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy Admin", null);
            }
        } catch (Exception e) {
            jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra", null);
        }
        String jsonOutput = gson.toJson(jsonResponse);
        response.getWriter().write(jsonOutput);
    }
	private void liststudent(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {        
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
		        JsonResponse<List<StudentDTO>> jsonResponse = null;
		        try {
		        	List <Student> lstStudent = studentDAO.selectAll();
		        	List<StudentDTO> lstStudentDTO = new ArrayList<StudentDTO>();
		        	for (Student student :lstStudent) {
		        		lstStudentDTO.add(new StudentDTO(student));
		        	}
		            jsonResponse = new JsonResponse<List<StudentDTO>>(true, HttpServletResponse.SC_OK, "Thành Công", lstStudentDTO);
		        } catch (Exception e) {
		            jsonResponse = new JsonResponse<List<StudentDTO>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
		            								"Có lỗi xảy ra", null);
		            e.printStackTrace();
		        }
		        String jsonOutput = gson.toJson(jsonResponse);
		        response.getWriter().write(jsonOutput);
		    }
	
	private void getDetailByStudentId(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
		
		FormDataReader reader = new FormDataReader(request);
	    Map<String, String> formData = reader.getData();
	    
	    if (formData.containsKey("studentId")) {
	        String studentId = formData.get("studentId");
	        JsonResponse<Student> jsonResponse = null;
			try {
				Student student = studentDAO.selectStudentByStudentCode(studentId);
	            if (student == null) {
	                jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy sinh viên", null);            

	            } else {
	                jsonResponse = new JsonResponse<Student>(true, HttpServletResponse.SC_OK, "Thành công", student);            
	            }
	        } catch (Exception e) {
	        	jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra", null);
	        	e.printStackTrace();
	        }
			response.getWriter().write(gson.toJson(jsonResponse));
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	

}
