package Controllers;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.time.LocalDate;
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
import Models.Project;
import Models.Registration;
import Models.Topic;
import Util.CustomGson;
import Util.JsonResponse;
import DAO.ProjectDAO;
import DAO.RegistrationDAO;
import DAO.TopicDAO;
import DAO.FileDAO;
import DAO.LecturerDAO;
import Models.FileDTO;

@WebServlet("/api/lecturer-project/*")
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
		
		CustomGson customGson = new CustomGson();
    	gson = customGson.createGson();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getPathInfo();
		try {
			switch (action) {
			case "/detail":
				showInfoProj(request, response);
				break;
			case "/list":
				listProject(request, response);
				break;
			case "/propose":
				ProposeProject(request, response);
				break;
			case "/myproj":
				myProject(request, response);
				break;
			case "/register-project":
				regProj(request, response);
				break;
			case "/load-propose-page":
				loadProposePage(request, response);
				break;
			case "/my-project/detail":
				showMyProject(request, response);
				break;
			case "/submit":
				submitFile(request, response);
				break;
			case "/get-lecture":
				getLecture(request, response);
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
	
	private void showMyProject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String projectCode = request.getParameter("id");
		Project project = projectDAO.selectProjectByProjectCode(projectCode);
		String status = project.getProjectStatusOfLecturer();
		RequestDispatcher dispatcher = null;
		System.out.println(status);
		if (status.equals("Đang thực hiện")) {
			
			dispatcher = request.getRequestDispatcher("/LecturerPage/MyProject/Pending/Pending.jsp");
			request.setAttribute("project", project);
		} else if (status.equals("Đã hoàn thành")) {
			dispatcher = request.getRequestDispatcher("/LecturerPage/MyProject/Done/DoneProject.jsp");
			request.setAttribute("project", project);
		} else {
			dispatcher = request.getRequestDispatcher("/lecturer-project/myproj");
		}
		dispatcher.forward(request, response);
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
        	//List<Project> listProject = projectDAO.selectActiveProjectForLecturer();
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
        	//List<Project> listProject = projectDAO.selectAllProject();
            jsonResponse = new JsonResponse<List<Project>>(true, HttpServletResponse.SC_OK, "Thành công!", listProject);
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
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonResponse<List<Project>> jsonResponse = null;
        try {
        	//List<Project> listProject = projectDAO.selectActiveProjectForLecturer();
        	List<Project> myProject = projectDAO.selectProjectsByLecturerCode(lectCode);
            jsonResponse = new JsonResponse<List<Project>>(true, HttpServletResponse.SC_OK, "Thành công!", myProject);
        } catch (Exception e) {
            jsonResponse = new JsonResponse<List<Project>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
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
	            if(projectDAO.proposeProject(project))
	            {
	            	Registration reg = new Registration(lect.getLecturerCode(), projectCode);
	            	if (regDAO.insertRegestration(reg)) {
	    			} else {
	    				Project projTemp = projectDAO.selectProjectByProjectCode(projectCode);
	    				if (projTemp != null && projTemp.getProjectCode() == projectCode) {
	    					projectDAO.deleteProject(projTemp);
	    				}
	    			}
	                jsonResponse = new JsonResponse<Project>(true, HttpServletResponse.SC_CREATED, "De tai đã được de xuat thành công,", propose_project);
	            }
	            else {
	                jsonResponse = new JsonResponse<Project>(false, HttpServletResponse.SC_CONFLICT, "Đề xuất thất bại.", null);
	            }

	        } catch (Exception e) {
	            jsonResponse = new JsonResponse<Project>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra.", null);
	        }
	    	response.getWriter().write(gson.toJson(jsonResponse)); 
		}
	}
	
	private void regProj(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
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
		
		BufferedReader reader = request.getReader();
	    StringBuilder requestBody = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        requestBody.append(line);
	    }
	    Map<String, String> formData = new HashMap<>();
	    try {
	        String[] pairs = requestBody.toString().split("&");
	        for (String pair : pairs) {
	            String[] keyValue = pair.split("=");
	            if (keyValue.length == 2) {
	                String key = URLDecoder.decode(keyValue[0], "UTF-8");
	                String value = URLDecoder.decode(keyValue[1], "UTF-8");
	                formData.put(key, value);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    String lectCode = formData.get("lectCode");
	    String projCode = formData.get("projCode");
	    Registration reg = new Registration(lectCode, projCode);
	    
	    response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonResponse<Registration> jsonResponse = null;
    	try {
            if(regDAO.insertRegestration(reg))
            {
                jsonResponse = new JsonResponse<Registration>(true, HttpServletResponse.SC_CREATED, "Đang ký thành công", reg);
            }
            else {
                jsonResponse = new JsonResponse<Registration>(false, HttpServletResponse.SC_CONFLICT, "Đăng ký thất bại.", null);
            }

        } catch (Exception e) {
            jsonResponse = new JsonResponse<Registration>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra.", null);
        }
    	response.getWriter().write(gson.toJson(jsonResponse)); 
	    

	}
	
	private void loadProposePage (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List <Topic> listTopic = topicDAO.selectActiveTopic();
        request.setAttribute("listTopic", listTopic);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/LecturerPage/ProposeProject/ProposeProject.jsp");
        dispatcher.forward(request, response);
	}
	
	private void submitFile (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession(false);
		String projCode = request.getParameter("projCode");
		Part filePart = request.getPart("fileInput");
		Lecturer lect = (Lecturer) session.getAttribute("user");
		System.out.println(127);
		byte[] fileData = null;
		if (filePart != null && filePart.getSize() > 0) {
			InputStream fileContent = filePart.getInputStream();
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        byte[] buffer = new byte[1024];
	        int bytesRead;
	        while ((bytesRead = fileContent.read(buffer)) != -1) {
	            byteArrayOutputStream.write(buffer, 0, bytesRead);
	        }
	        fileData = byteArrayOutputStream.toByteArray();
	        FileDTO file = new FileDTO(fileData, lect.getLecturerCode(), projCode);
			fileDAO.insertFile(file);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/lecturer-project/myproj");
		dispatcher.forward(request, response);
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
}
