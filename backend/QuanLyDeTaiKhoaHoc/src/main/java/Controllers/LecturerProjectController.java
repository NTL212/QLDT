package Controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Models.Lecturer;
import Models.Project;
import Models.Registration;
import Models.Topic;
import DAO.ProjectDAO;
import DAO.RegistrationDAO;
import DAO.TopicDAO;
import DAO.FileDAO;
import Models.FileDTO;

@WebServlet("/lecturer-project/*")
public class LecturerProjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectDAO projectDAO;
	private RegistrationDAO regDAO;
	private TopicDAO topicDAO;
	private FileDAO fileDAO;
	
	public LecturerProjectController() {
		super();
	}

	public void init() {
		projectDAO = new ProjectDAO();
		regDAO = new RegistrationDAO();
		topicDAO = new TopicDAO();
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
		Project project = projectDAO.selectProjectByProjectCode(projectCode);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/LecturerPage/RegisterProject/Detail/Detail.jsp");
		request.setAttribute("project", project);
		dispatcher.forward(request, response);
	}

	private void listProject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Project> listProject = projectDAO.selectActiveProjectForLecturer();
		request.setAttribute("listProject", listProject);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/LecturerPage/RegisterProject/List/List.jsp");
		dispatcher.forward(request, response);
	}

	private void myProject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession(false);
		Lecturer lect = (Lecturer) session.getAttribute("user");
		if (lect != null) {
			List<Project> myProject = projectDAO.selectProjectsByLecturerCode(lect.getLecturerCode());
			request.setAttribute("myProject", myProject);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/LecturerPage/MyProject/List/ListProject.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void ProposeProject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession(false);
		Lecturer lect = (Lecturer) session.getAttribute("user");
		if (lect != null) {
		String projectCode = request.getParameter("id");
		String name = request.getParameter("name");
		String topic = request.getParameter("projectTopic");
		LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
		LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
		float expectedBudget = Float.parseFloat(request.getParameter("expectcost"));
		int teamMembers = Integer.parseInt(request.getParameter("maxofmem"));
		String projectDescription = request.getParameter("describe");
		Project project = new Project(projectCode, name, null, projectDescription, teamMembers, null, null,
				startDate, endDate, null, expectedBudget, null, null, topic, lect.getLecturerCode(), null, true);

		if (projectDAO.proposeProject(project)) {
			Registration reg = new Registration(lect.getLecturerCode(), projectCode);
			if (regDAO.insertRegestration(reg)) {
				request.setAttribute("successMsg", "Đề xuất thành công");
			} else {
				Project projTemp = projectDAO.selectProjectByProjectCode(projectCode);
				if (projTemp != null && projTemp.getProjectCode() == projectCode) {
					projectDAO.deleteProject(projTemp);
				}
				request.setAttribute("errMsg", "Đề xuất thất bại");
			}
		} else {
			request.setAttribute("errMsg", "Đề xuất thất bại");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/lecturer-project/list");
		dispatcher.forward(request, response);
		}
	}
	
	private void regProj(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String projCode = request.getParameter("projCode");
		HttpSession session = request.getSession(false);
		Lecturer lect = (Lecturer) session.getAttribute("user");
		Registration reg = new Registration(lect.getLecturerCode(), projCode);
		if(regDAO.insertRegestration(reg)) {
			request.setAttribute("successMsg", "Đăng ký thành công");
		} else {
			request.setAttribute("errMsg", "Đăng ký thất bại");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/lecturer-project/list");
		dispatcher.forward(request, response);
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
}
