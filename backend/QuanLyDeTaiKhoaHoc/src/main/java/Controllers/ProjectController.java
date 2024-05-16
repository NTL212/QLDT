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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import DAO.ProjectDAO;
import DAO.TopicDAO;
import DTO.NoticeDTO;
import DTO.PendingApprovalProjectDTO;
import DTO.ProjectDTO;
import DTO.ProjectDetailDTO;
import DTO.ShowLecturerDTO;
import Util.CustomTimeGson;
import Util.FormDataReader;
import Util.JsonResponse;
import Models.Lecturer;
import Models.ManagementStaff;
import Models.Notification;
import Models.Project;
import Models.Topic;
import DAO.RegistrationDAO;
import Models.Registration;

@WebServlet("/project/*")
public class ProjectController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ProjectDAO projectDAO;
    private TopicDAO topicDAO;
    
    private Gson gson;
    RegistrationDAO registrationDAO = new RegistrationDAO();

    public void init() {
    	projectDAO = new ProjectDAO();
    	topicDAO = new TopicDAO();
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
    	response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String action = request.getPathInfo();
        try {
        	if (action.equals("/api/getAll")) {
        	    getAll(request, response);
        	}
        	else if (action.equals("/api/getPendingApproval")) {
        		getPendingApproval(request, response);
        	}
        	else if (action.equals("/api/getById/")) {
        		getById(request, response);
        	} else if (action.equals("/api/createProject")) {
        		insertProjectManagerAPI(request, response);
        	} else if (action.equals("/api/updateProject")) {
        		updateProjectAPI(request, response);
        	}else if (action.equals("/api/approveproject")) {
        		approveProjectAPI(request, response);
        	}else if (action.equals("/api/disagreeproject")) {
        		disagreeProjectAPI(request, response);
        	}
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
            
        /*
        try {
            switch (action) {
                case "/insertproject":   	done
                	insertProjectManager(request, response);
                    break;
                case "/editproject":    	-
                    showEditForm(request, response);
                    break;
                case "/shownewform":    	-
                	shownewProjectForm(request, response);
                	break;
                case "/showdetailform":  	done
                	ShowDetailForm(request, response);
                	break;
                case "/updateproject":		done
                    updateProject(request, response);
                    break;
                case "/listproject":		done
                	listProject(request, response);
                    break;
                case "/listapproveproject": done
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
        JsonResponse<List<Project>> jsonResponse = null;
        
        try {
        	List <Project> listProject = projectDAO.selectAllProject();
        	List<Project> listProjectDTO = new ArrayList<Project>();
        	for (int i = 0; i < listProject.size(); i++) {
        		listProjectDTO.add(listProject.get(i)) ;
        	}
            jsonResponse = new JsonResponse<List<Project>>(true, HttpServletResponse.SC_OK, "Thành công!", listProjectDTO);
        } catch (Exception e) {
            jsonResponse = new JsonResponse<List<Project>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            								"Có lỗi xảy ra.", null);
            e.printStackTrace();
        }
        String jsonOutput = gson.toJson(jsonResponse);
        response.getWriter().write(jsonOutput);
    }
    
    /*
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
    */
    
    private void getById(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
	    FormDataReader reader = new FormDataReader(request);
	    Map<String, String> formData = reader.getData();
	    System.out.println("getById1");
	    if (formData.containsKey("projectId")) {
	        String projectId = formData.get("projectId");
	        JsonResponse<ProjectDetailDTO> jsonResponse = null;
			try {
	            Project project = projectDAO.selectProjectByProjectCode(projectId);
		        System.out.println("getbyid");

	            if (project == null) {
	                jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_NOT_FOUND, "Đề tài không tồn tại", null);            

	            } else {
	                jsonResponse = new JsonResponse<>(true, HttpServletResponse.SC_OK, "Thành công", new ProjectDetailDTO(project));            
	            }
	        } catch (Exception e) {
	        	jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra", null);
	        	e.printStackTrace();
	        }
			response.getWriter().write(gson.toJson(jsonResponse));
	    }
    }
    
    /*
    private void listApproveProject(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
        List < Registration > listRe = registrationDAO.selectApproveRegis();
        request.setAttribute("listRe", listRe);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ProjectManager/ApproveProject/ApproveProject.jsp");
        dispatcher.forward(request, response);
    }
    */
    
    private void getPendingApproval(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {        
        JsonResponse<List<PendingApprovalProjectDTO>> jsonResponse = null;
        try {
        	List <Registration> listReg = registrationDAO.selectApproveRegis();
        	List<PendingApprovalProjectDTO> listProject = new ArrayList<PendingApprovalProjectDTO>();
        	for (int i = 0; i < listReg.size(); i++) {
        		listProject.add(new PendingApprovalProjectDTO(listReg.get(i)));
        	}
            jsonResponse = new JsonResponse<List<PendingApprovalProjectDTO>>(true, HttpServletResponse.SC_OK, "Thành công!", listProject);
        } catch (Exception e) {
            jsonResponse = new JsonResponse<List<PendingApprovalProjectDTO>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            								"Có lỗi xảy ra.", null);
            e.printStackTrace();
        }
        String jsonOutput = gson.toJson(jsonResponse);
        response.getWriter().write(jsonOutput);
    }
    
    private void approveProjectAPI(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	FormDataReader reader = new FormDataReader(request);
	    Map<String, String> formData = reader.getData();
	    response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
//    	HttpSession session = request.getSession();
//		session.getAttribute("user");
		String managerCode =  formData.get("managerCode");
    	String lec = formData.get("idLec");
    	String pro = formData.get("pro");
    	
		
//		request.setAttribute("title", "Bạn đã được QLDTKH phê duyệt cho đề tài " + pro);
//		request.setAttribute("recievePerson",lec);
//		request.setAttribute("content","Chúc mừng bạn đã được QLDTKH phê duyệt. Bây giờ bạn có thể thực hiện đề tài của mình");
//		request.setAttribute("type","Thông báo chúc mừng");
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/ProjectManager/ExpressMgs/ExpressMgs.jsp");
//        dispatcher.forward(request, response);
		
		JsonResponse<NoticeDTO> jsonResponse = null;
        try {
        	NoticeDTO noti = new NoticeDTO();
        	noti.setSenderId(managerCode);
        	noti.setReceiveId(lec);
        	noti.setContent("Chúc mừng bạn đã được QLDTKH phê duyệt. Bây giờ bạn có thể thực hiện đề tài của mình");
        	noti.setTitle("Bạn đã được QLDTKH phê duyệt cho đề tài " + pro);
        	registrationDAO.approveProject(lec, pro, managerCode);
    		projectDAO.delegateToLecturer(lec, pro);
            jsonResponse = new JsonResponse<NoticeDTO>(true, HttpServletResponse.SC_OK, "Thành công!", noti);
        } catch (Exception e) {
            jsonResponse = new JsonResponse<NoticeDTO>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            								"Có lỗi xảy ra.", null);
            e.printStackTrace();
        }
        String jsonOutput = gson.toJson(jsonResponse);
        response.getWriter().write(jsonOutput);
    }
    
    private void disagreeProjectAPI(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	FormDataReader reader = new FormDataReader(request);
	    Map<String, String> formData = reader.getData();
	    response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
//    	HttpSession session = request.getSession();
//		session.getAttribute("user");
		String managerCode =  formData.get("managerCode");
    	String lec = formData.get("idLec");
    	String pro = formData.get("pro");
    	
    	
//		request.setAttribute("title", "Kết quả phê duyệt đề tài " + pro);
//		request.setAttribute("recievePerson",lec);
//		request.setAttribute("content","Xin lỗi bạn, yếu cầu của bạn chưa được chấp thuận");
//		request.setAttribute("type","Thư từ chối");
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/ProjectManager/ExpressMgs/ExpressMgs.jsp");
//        dispatcher.forward(request, response);
        
        JsonResponse<NoticeDTO> jsonResponse = null;
        try {
        	NoticeDTO noti = new NoticeDTO();
        	noti.setSenderId(managerCode);
        	noti.setReceiveId(lec);
        	noti.setContent("Xin lỗi bạn, yếu cầu của bạn chưa được chấp thuận");
        	noti.setTitle("Kết quả phê duyệt đề tài " + pro);
        	registrationDAO.disagreeProject(lec, pro, managerCode);
            jsonResponse = new JsonResponse<NoticeDTO>(true, HttpServletResponse.SC_OK, "Thành công!", noti);
        } catch (Exception e) {
            jsonResponse = new JsonResponse<NoticeDTO>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            								"Có lỗi xảy ra.", null);
            e.printStackTrace();
        }
        String jsonOutput = gson.toJson(jsonResponse);
        response.getWriter().write(jsonOutput);
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
    
    private void insertProjectManagerAPI(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
    	BufferedReader reader = request.getReader();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonResponse<Project> jsonResponse = null;
    	try {
            Project project = gson.fromJson(reader, Project.class);
            if (projectDAO.selectProjectByProjectCode(project.getProjectCode()) == null)
            {
            	boolean succ = projectDAO.insertProject(project);
            	if (succ) {
            		jsonResponse = new JsonResponse<Project>(true, HttpServletResponse.SC_CREATED, "Đề tài đã được tạo thành công,", project);
            	} else {
                    jsonResponse = new JsonResponse<Project>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra.", null);
            	}
            }
            else {
                jsonResponse = new JsonResponse<Project>(false, HttpServletResponse.SC_CONFLICT, "Mã đề tài đã tồn tại.", null);
            }
        } catch (Exception e) {
            jsonResponse = new JsonResponse<Project>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra.", null);
        }
    	response.getWriter().write(gson.toJson(jsonResponse)); 
    }
    /*
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
	*/
    private void updateProjectAPI(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	BufferedReader reader = request.getReader();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonResponse<Project> jsonResponse = null;
    	try {
            System.out.println("test update project");

            Project project = gson.fromJson(reader, Project.class);
            System.out.println("test update project");
            if (projectDAO.selectProjectByProjectCode(project.getProjectCode()) != null)
            {
            	boolean succ = projectDAO.updateProject(project);
            	if (succ) {
            		jsonResponse = new JsonResponse<Project>(true, HttpServletResponse.SC_CREATED, "Cập nhật thành công,", project);
            	} else {
                    jsonResponse = new JsonResponse<Project>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra.", null);
            	}
            }
            else {
                jsonResponse = new JsonResponse<Project>(false, HttpServletResponse.SC_CONFLICT, "Mã đề tài không tồn tại.", null);
            }
        } catch (Exception e) {
            jsonResponse = new JsonResponse<Project>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra.", null);
        }
    	response.getWriter().write(gson.toJson(jsonResponse)); 
   }
}