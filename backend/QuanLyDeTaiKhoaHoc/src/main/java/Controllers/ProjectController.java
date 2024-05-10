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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import DAO.ProjectDAO;
import DAO.TopicDAO;
import Util.CustomGson;
import Util.JsonResponse;
import Models.ManagementStaff;
import Models.Project;
import Models.Topic;
import DAO.RegistrationDAO;
import Models.Registration;

@WebServlet("/api/project/*")
public class ProjectController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ProjectDAO projectDAO;
    private TopicDAO topicDAO;
   
    private Gson gson;
    RegistrationDAO registrationDAO = new RegistrationDAO();

    public void init() {
    	projectDAO = new ProjectDAO();
    	topicDAO = new TopicDAO();
    	CustomGson customGson = new CustomGson();
    	gson = customGson.createGson();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        String action = request.getPathInfo();
        try {
        	if (action == null || action.isEmpty()) {
        	    getAll(request, response);
        	}
        	else {
        		switch (action) {
	        		default:
			            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			            response.getWriter().write("Not Found");
			            break;
        		}
        	}
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
            
        /*
        try {
            switch (action) {
                case "/insertproject":
                	insertProjectManager(request, response);
                    break;
                case "/editproject":
                    showEditForm(request, response);
                    break;
                case "/shownewform":
                	shownewProjectForm(request, response);
                	break;
                case "/showdetailform":
                	ShowDetailForm(request, response);
                	break;
                case "/updateproject":
                    updateProject(request, response);
                    break;
                case "/listproject":
                	listProject(request, response);
                    break;
                case "/listapproveproject":
                	listApproveProject(request, response);
                    break;
                case "/approveproject":
                	approveProject(request, response);
                    break;
                case "/disagreeproject":
                	disagreeProject(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
        */
    }

    /*
    private void listProject(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Project > listProject = projectDAO.selectAllProject();
        List <Topic> listTopic = topicDAO.selectAllTopic();
        request.setAttribute("listProject", listProject);
        request.setAttribute("listTopic", listTopic);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ProjectManager/ProjectManagement/ProjectManagement.jsp");
        dispatcher.forward(request, response);
    }
    */
    
    private void getAll(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonResponse<List<Project>> jsonResponse = null;
        try {
        	List <Project> listProject = projectDAO.selectAllProject();
            jsonResponse = new JsonResponse<List<Project>>(true, HttpServletResponse.SC_OK, "Thành công!", listProject);
        } catch (Exception e) {
            jsonResponse = new JsonResponse<List<Project>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            								"Có lỗi xảy ra.", null);
        }
        String jsonOutput = gson.toJson(jsonResponse);
        response.getWriter().write(jsonOutput);
    }
    
    private void shownewProjectForm(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	List < Project > listProject = projectDAO.selectAllProject();
    	String id = request.getParameter("id");
        Project existingProject = projectDAO.selectProjectByProjectCode(id);
        List <Topic> listTopic = topicDAO.selectAllTopic();
        request.setAttribute("listTopic", listTopic);
        request.setAttribute("project", existingProject);
        request.setAttribute("listProject", listProject);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ProjectManager/AddProject/AddProject.jsp");
        dispatcher.forward(request, response);
    }
    
    private void ShowDetailForm(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	String id = request.getParameter("id");
        Project existingProject = projectDAO.selectProjectByProjectCode(id);
        request.setAttribute("project", existingProject);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ProjectManager/DetailProject/DetailProject.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listApproveProject(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
        List < Registration > listRe = registrationDAO.selectApproveRegis();
        request.setAttribute("listRe", listRe);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ProjectManager/ApproveProject/ApproveProject.jsp");
        dispatcher.forward(request, response);
    }
    
    private void approveProject(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
		session.getAttribute("user");
		ManagementStaff manager =  (ManagementStaff)session.getAttribute("user");
    	String lec = request.getParameter("idLec");
    	String pro = request.getParameter("pro");
    	registrationDAO.approveProject(lec, pro, manager.getEmpCode());
		projectDAO.delegateToLecturer(lec, pro);
		request.setAttribute("title", "Bạn đã được QLDTKH phê duyệt cho đề tài " + pro);
		request.setAttribute("recievePerson",lec);
		request.setAttribute("content","Chúc mừng bạn đã được QLDTKH phê duyệt. Bây giờ bạn có thể thực hiện đề tài của mình");
		request.setAttribute("type","Thông báo chúc mừng");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ProjectManager/ExpressMgs/ExpressMgs.jsp");
        dispatcher.forward(request, response);
    }
    
    private void disagreeProject(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
		session.getAttribute("user");
		ManagementStaff manager =  (ManagementStaff)session.getAttribute("user");
    	String lec = request.getParameter("idLec");
    	String pro = request.getParameter("pro");
    	registrationDAO.disagreeProject(lec, pro, manager.getEmpCode());
		request.setAttribute("title", "Kết quả phê duyệt đề tài " + pro);
		request.setAttribute("recievePerson",lec);
		request.setAttribute("content","Xin lỗi bạn, yếu cầu của bạn chưa được chấp thuận");
		request.setAttribute("type","Thư từ chối");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ProjectManager/ExpressMgs/ExpressMgs.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");
        Project existingProject = projectDAO.selectProjectByProjectCode(id);
        List <Topic> listTopic = topicDAO.selectAllTopic();
        request.setAttribute("listTopic", listTopic);
        request.setAttribute("project", existingProject);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ProjectManager/UpdateProject/UpdateProject.jsp");
        dispatcher.forward(request, response);
    }

    private void insertProjectManager(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
    	System.out.println(13);
        String projectCode = request.getParameter("projectCode");
        String projectTopic = request.getParameter("projectTopic");
        String projectTitle = request.getParameter("projectTitle");
        LocalDate registrationStartDate = LocalDate.parse(request.getParameter("registrationStartDate"));
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate registrationEndDate = LocalDate.parse(request.getParameter("registrationEndDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
        float expectedBudget = Float.parseFloat(request.getParameter("expectedBudget"));
        int teamMembers = Integer.parseInt(request.getParameter("teamMembers"));
        String attachmentFile = request.getParameter("attachmentFile");
        String projectDescription = request.getParameter("projectDescription");
        Project project = new Project(projectCode, projectTitle, LocalDate.now(), projectDescription, teamMembers,
                registrationStartDate, registrationEndDate, startDate, endDate, null, expectedBudget,
                "", "", projectTopic, "", "");
        Boolean result = projectDAO.insertProject(project);
        if (result)
        	request.setAttribute("success", "Thêm project thành công");
        else request.setAttribute("fail", "Thêm project thất bại");
        List < Project > listProject = projectDAO.selectAllProject();
        List <Topic> listTopic = topicDAO.selectAllTopic();
        request.setAttribute("listProject", listProject);
        request.setAttribute("listTopic", listTopic);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ProjectManager/ProjectManagement/ProjectManagement.jsp");
        dispatcher.forward(request, response);
    }

    private void updateProject(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	 String projectCode = request.getParameter("projectCode");
         String projectTopic = request.getParameter("projectTopic");
         String projectTitle = request.getParameter("projectTitle");
         LocalDate registrationStartDate = LocalDate.parse(request.getParameter("registrationStartDate"));
         LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
         LocalDate evaluationDate = request.getParameter("evaluationDate") != "" ? LocalDate.parse(request.getParameter("evaluationDate")): null;
         LocalDate registrationEndDate = LocalDate.parse(request.getParameter("registrationEndDate"));
         LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
         float expectedBudget = Float.parseFloat(request.getParameter("expectedBudget"));
         int teamMembers = Integer.parseInt(request.getParameter("teamMembers"));
         //String attachmentFile = request.getParameter("attachmentFile");
         String projectDescription = request.getParameter("projectDescription");
         String result = request.getParameter("result");
         String comments = request.getParameter("comments");
         String lecturer = request.getParameter("lecture");
         String evaluationBoard = request.getParameter("evaluationBoard");
         Project project = new Project(projectCode, projectTitle, LocalDate.now(), projectDescription, teamMembers,
                 registrationStartDate, registrationEndDate, startDate, endDate, evaluationDate, expectedBudget,
                 result, comments , projectTopic, lecturer, evaluationBoard);
         boolean results = projectDAO.updateProject(project);
         if (results)
         	request.setAttribute("success", "Chỉnh sửa project thành công");
         else request.setAttribute("fail", "Chỉnh sửa project thất bại");
         List < Project > listProject = projectDAO.selectAllProject();
         List <Topic> listTopic = topicDAO.selectAllTopic();
         request.setAttribute("listProject", listProject);
         request.setAttribute("listTopic", listTopic);
         RequestDispatcher dispatcher = request.getRequestDispatcher("/ProjectManager/ProjectManagement/ProjectManagement.jsp");
         dispatcher.forward(request, response);
    }

}