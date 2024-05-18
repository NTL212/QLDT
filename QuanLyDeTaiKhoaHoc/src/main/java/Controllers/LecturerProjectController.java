package Controllers;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import Models.Lecturer;
import Models.ManagementStaff;
import Models.Project;
import Models.Registration;
import Models.Topic;
import Util.CustomTimeGson;
import Util.FormDataReader;
import Util.JsonResponse;
import DAO.ProjectDAO;
import DAO.RegistrationDAO;
import DAO.TopicDAO;
import DTO.ProjectDTO;
import DTO.ProjectDetailDTO;
import DAO.FileDAO;
import DAO.LecturerDAO;
import Models.Account;
import Models.Admin;
import Models.FileDTO;

@WebServlet("/lecturer-project/*")
public class LecturerProjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectDAO projectDAO;
	private RegistrationDAO regDAO;

	private TopicDAO topicDAO;
	private LecturerDAO lecturerDAO;
	private FileDAO fileDAO;
	private Gson gson;

	public LecturerProjectController() {
		super();
	}

	public void init() {
		projectDAO = new ProjectDAO();
		regDAO = new RegistrationDAO();
		topicDAO = new TopicDAO();
		lecturerDAO = new LecturerDAO();
		fileDAO = new FileDAO();
		CustomTimeGson customGson = new CustomTimeGson();
		gson = customGson.createGson();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getPathInfo();
		try {
			switch (action) {
			case "/api/detail":
				showInfoProj(request, response);
				break;
			case "/api/list":
				listProject(request, response);
				break;
			case "/api/listsearch":
				listSearchProject(request, response);
				break;
			case "/api/propose":
				ProposeProject(request, response);
				break;
			case "/api/myproj":
				myProject(request, response);
				break;
			case "/api/register-project":
				regProj(request, response);
				break;
			case "/api/load-propose-page":
				loadProposePage(request, response);
				break;
			case "/api/my-project/detail":
				showMyProject(request, response);
				break;
			case "/api/submit":
				submitFile(request, response);
				break;
			case "/api/get-lecture":
				getLecture(request, response);
				break;
			case "/api/editlecture":
				editProfile(request, response);
				break;
			case "/api/getfile":
				getFileByProAngLect(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void getFileByProAngLect(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String projectCode = request.getParameter("projCode");
		String lectCode = request.getParameter("lectCode");
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/LecturerPage/RegisterProject/Detail/Detail.jsp");
//		request.setAttribute("project", project);
//		dispatcher.forward(request, response);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JsonResponse<FileDTO> jsonResponse = null;
		try {
			FileDTO fileDTO = fileDAO.getFileByLectIdAndProId(projectCode, lectCode);

			jsonResponse = new JsonResponse<FileDTO>(true, HttpServletResponse.SC_OK, "Thành công!", fileDTO);
		} catch (Exception e) {
			jsonResponse = new JsonResponse<FileDTO>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra.", null);
		}
		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
	}

	private void showMyProject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String projectCode = request.getParameter("id");
//		Project project = projectDAO.selectProjectByProjectCode(projectCode);
//		String status = project.getProjectStatusOfLecturer();
//		RequestDispatcher dispatcher = null;
//		System.out.println(status);
//		if (status.equals("Đang thực hiện")) {
//			
//			dispatcher = request.getRequestDispatcher("/LecturerPage/MyProject/Pending/Pending.jsp");
//			request.setAttribute("project", project);
//		} else if (status.equals("Đã hoàn thành")) {
//			dispatcher = request.getRequestDispatcher("/LecturerPage/MyProject/Done/DoneProject.jsp");
//			request.setAttribute("project", project);
//		} else {
//			dispatcher = request.getRequestDispatcher("/lecturer-project/myproj");
//		}
//		dispatcher.forward(request, response);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JsonResponse<ProjectDetailDTO> jsonResponse = null;
		try {
			// List<Project> listProject = projectDAO.selectActiveProjectForLecturer();
			Project project = projectDAO.selectProjectByProjectCode(projectCode);
			jsonResponse = new JsonResponse<ProjectDetailDTO>(true, HttpServletResponse.SC_OK, "Thành công!",
					new ProjectDetailDTO(project));
		} catch (Exception e) {
			jsonResponse = new JsonResponse<ProjectDetailDTO>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra.", null);
		}
		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
	}

	private void showInfoProj(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String projectCode = request.getParameter("id");

//		RequestDispatcher dispatcher = request.getRequestDispatcher("/LecturerPage/RegisterProject/Detail/Detail.jsp");
//		request.setAttribute("project", project);
//		dispatcher.forward(request, response);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JsonResponse<Project> jsonResponse = null;
		try {
			// List<Project> listProject = projectDAO.selectActiveProjectForLecturer();
			Project project = projectDAO.selectProjectByProjectCode(projectCode);
			jsonResponse = new JsonResponse<Project>(true, HttpServletResponse.SC_OK, "Thành công!", project);
		} catch (Exception e) {
			jsonResponse = new JsonResponse<Project>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra.", null);
		}
		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
	}

	private void listProject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

//		request.setAttribute("listProject", listProject);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/LecturerPage/RegisterProject/List/List.jsp");
//		dispatcher.forward(request, response);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JsonResponse<List<Project>> jsonResponse = null;
		try {
			List<Project> listProject = projectDAO.selectActiveProjectForLecturer();
//        	List<ProjectDTO> listProjectDTO = new ArrayList<ProjectDTO>();
//        	for (int i = 0; i < listProject.size(); i++) {
//        		listProjectDTO.add(new ProjectDTO(listProject.get(i))) ;
//        	}
			// List<Project> listProject = projectDAO.selectAllProject();
			jsonResponse = new JsonResponse<List<Project>>(true, HttpServletResponse.SC_OK, "Thành công!", listProject);
		} catch (Exception e) {
			jsonResponse = new JsonResponse<List<Project>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra.", null);
		}
		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
	}

	private void listSearchProject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		String searchText = request.getParameter("searchText");

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JsonResponse<List<Project>> jsonResponse = null;
		try {
			List<Project> listProject = projectDAO.selectActiveProjectForLecturer();
			List<Project> listSearchProject = new ArrayList<>();
			for (Project project : listProject) {
				if (project.getName().toLowerCase().contains(searchText.toLowerCase())) {
					listSearchProject.add(project);
				}
			}

			jsonResponse = new JsonResponse<List<Project>>(true, HttpServletResponse.SC_OK, "Thành công!",
					listSearchProject);
		} catch (Exception e) {
			jsonResponse = new JsonResponse<List<Project>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra.", null);
		}
		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
	}

	private void myProject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
//		HttpSession session = request.getSession(false);
//		Lecturer lect = (Lecturer) session.getAttribute("user");
//		if (lect != null) {
//			List<Project> myProject = projectDAO.selectProjectsByLecturerCode(lect.getLecturerCode());
//			request.setAttribute("myProject", myProject);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/LecturerPage/MyProject/List/ListProject.jsp");
//			dispatcher.forward(request, response);
//		}
		String lectCode = request.getParameter("id");
		String searchText = request.getParameter("searchText");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JsonResponse<List<ProjectDTO>> jsonResponse = null;
		try {
			// List<Project> listProject = projectDAO.selectActiveProjectForLecturer();
			List<Project> myProject = projectDAO.selectProjectsByLecturerCode(lectCode);
			List<ProjectDTO> listProjectDTO = new ArrayList<ProjectDTO>();
			for (int i = 0; i < myProject.size(); i++) {
				if (myProject.get(i).getName().toLowerCase().contains(searchText.toLowerCase())) {
					listProjectDTO.add(new ProjectDTO(myProject.get(i)));
				}
			}
			jsonResponse = new JsonResponse<List<ProjectDTO>>(true, HttpServletResponse.SC_OK, "Thành công!",
					listProjectDTO);
		} catch (Exception e) {
			jsonResponse = new JsonResponse<List<ProjectDTO>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra.", null);
		}
		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
	}

	private void ProposeProject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		BufferedReader reader = request.getReader();
		Project propose_project = gson.fromJson(reader, Project.class);

		HttpSession session = request.getSession(false);
		Lecturer lect = (Lecturer) propose_project.getLecturer();
		if (lect != null) {
			String projectCode = propose_project.getProjectCode();
			String name = propose_project.getName();
			String topic = propose_project.getTopic().getTopicCode();
			LocalDate startDate = propose_project.getStartDate();
			LocalDate endDate = propose_project.getEndDate();
			float expectedBudget = propose_project.getEstBudget();
			int teamMembers = propose_project.getMaxMember();
			String projectDescription = propose_project.getDescription();
			Project project = new Project(projectCode, name, null, projectDescription, teamMembers, null, null,
					startDate, endDate, null, expectedBudget, null, null, topic, lect.getLecturerCode(), null, true);

//		if (projectDAO.proposeProject(project)) {
//			Registration reg = new Registration(lect.getLecturerCode(), projectCode);
//			if (regDAO.insertRegestration(reg)) {
//				request.setAttribute("successMsg", "Đề xuất thành công");
//			} else {
//				Project projTemp = projectDAO.selectProjectByProjectCode(projectCode);
//				if (projTemp != null && projTemp.getProjectCode() == projectCode) {
//					projectDAO.deleteProject(projTemp);
//				}
//				request.setAttribute("errMsg", "Đề xuất thất bại");
//			}
//		} else {
//			request.setAttribute("errMsg", "Đề xuất thất bại");
//		}

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			JsonResponse<Project> jsonResponse = null;
			try {
				if (projectDAO.selectProjectByProjectCode(projectCode) != null) {
					jsonResponse = new JsonResponse<Project>(false, HttpServletResponse.SC_CONFLICT,
							"Mã dề tài đã tồn tại", null);
				} else {
					if (projectDAO.proposeProject(project)) {
						Registration reg = new Registration(lect.getLecturerCode(), projectCode);
						if (regDAO.insertRegestration(reg)) {
						} else {
							Project projTemp = projectDAO.selectProjectByProjectCode(projectCode);
							if (projTemp != null && projTemp.getProjectCode() == projectCode) {
								projectDAO.deleteProject(projTemp);
							}
						}
						jsonResponse = new JsonResponse<Project>(true, HttpServletResponse.SC_CREATED,
								"De tai đã được de xuat thành công,", propose_project);
					} else {
						jsonResponse = new JsonResponse<Project>(false, HttpServletResponse.SC_CONFLICT,
								"Đề xuất thất bại.", null);
					}
				}

			} catch (Exception e) {
				jsonResponse = new JsonResponse<Project>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"Có lỗi xảy ra.", null);
			}
			response.getWriter().write(gson.toJson(jsonResponse));
		}
	}

	private void regProj(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
//		String projCode = request.getParameter("projCode");
//		HttpSession session = request.getSession(false);
//		Lecturer lect = (Lecturer) session.getAttribute("user");
//		Registration reg = new Registration(lect.getLecturerCode(), projCode);
//		if(regDAO.insertRegestration(reg)) {
//			request.setAttribute("successMsg", "Đăng ký thành công");
//		} else {
//			request.setAttribute("errMsg", "Đăng ký thất bại");
//		}
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/lecturer-project/list");
//		dispatcher.forward(request, response);

		FormDataReader reader = new FormDataReader(request);
		Map<String, String> formData = reader.getData();

		String lectCode = formData.get("lectCode");
		String projCode = formData.get("projCode");
		Registration reg = new Registration(lectCode, projCode);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JsonResponse<Registration> jsonResponse = null;
		try {
			if (regDAO.insertRegestration(reg)) {
				jsonResponse = new JsonResponse<Registration>(true, HttpServletResponse.SC_CREATED,
						"Đang ký thành công", reg);
			} else {
				jsonResponse = new JsonResponse<Registration>(false, HttpServletResponse.SC_CONFLICT,
						"Đăng ký thất bại.", null);
			}

		} catch (Exception e) {
			jsonResponse = new JsonResponse<Registration>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra.", null);
		}
		response.getWriter().write(gson.toJson(jsonResponse));

	}

	private void loadProposePage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Topic> listTopic = topicDAO.selectActiveTopic();
		request.setAttribute("listTopic", listTopic);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/LecturerPage/ProposeProject/ProposeProject.jsp");
		dispatcher.forward(request, response);
	}

	private void submitFile(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String projCode = request.getParameter("projCode");
		String lectCode = request.getParameter("lectCode");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JsonResponse<FileDTO> jsonResponse = null;

		byte[] fileData = null;
		FileDTO file_exist = fileDAO.getFileByLectIdAndProId(projCode, lectCode);
		if (file_exist == null) {
			try {
				InputStream fileContent = request.getInputStream();
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = fileContent.read(buffer)) != -1) {
					byteArrayOutputStream.write(buffer, 0, bytesRead);
				}
				fileData = byteArrayOutputStream.toByteArray();
				FileDTO file = new FileDTO(fileData, lectCode, projCode);
				fileDAO.insertFile(file);
				jsonResponse = new JsonResponse<FileDTO>(true, HttpServletResponse.SC_OK, "Thành công!", file);
			} catch (Exception e) {
				jsonResponse = new JsonResponse<FileDTO>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"Có lỗi xảy ra.", null);
			}
		} else {
			jsonResponse = new JsonResponse<FileDTO>(false, HttpServletResponse.SC_CONFLICT, "Đã tồn tại file rồi",
					null);
		}
		response.getWriter().write(gson.toJson(jsonResponse));
	}

	private void getLecture(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		String lectCode = request.getParameter("id");

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JsonResponse<Lecturer> jsonResponse = null;
		try {
			Lecturer lect = lecturerDAO.selectLecturerByLectCode(lectCode);
			jsonResponse = new JsonResponse<Lecturer>(true, HttpServletResponse.SC_OK, "Thành công!", lect);
		} catch (Exception e) {
			jsonResponse = new JsonResponse<Lecturer>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Có lỗi xảy ra.", null);
		}
		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
	}

	private void editProfile(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		FormDataReader reader = new FormDataReader(request);
		Map<String, String> formData = reader.getData();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String username = formData.get("adCode");
		String name = formData.get("name");
		LocalDate birthday = LocalDate.parse(formData.get("birthday"));
		String address = formData.get("address");
		String idNum = formData.get("idNum");
		String phoneNum = formData.get("phoneNum");
		String email = formData.get("email");
		String sex = formData.get("sex");
		String falculityCode = formData.get("falculityCode");
		
		JsonResponse<String> jsonResponse = null;
		Lecturer lecturer = lecturerDAO.selectLecturerByLectCode(username);
		try {
			if (lecturer != null) {
				lecturerDAO.updateLecturer(
						new Lecturer(username, name, birthday, address, idNum, phoneNum, email, sex, falculityCode));
				jsonResponse = new JsonResponse<>(true, HttpServletResponse.SC_CREATED, "Thành công!", null);
			} else {
				jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_CONFLICT, "Tài khoản không tồn tại",
						null);
			}

		} catch (Exception e) {
			jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra.",
					null);
			e.printStackTrace();
		}

		String jsonOutput = gson.toJson(jsonResponse);
		response.getWriter().write(jsonOutput);
	}
}
