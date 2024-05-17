package Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.ProjectMemberDAO;
import DAO.StudentDAO;
import DTO.StudentDTO;
import Models.ProjectMember;
import Models.Student;
import Util.CustomGson;
import Util.FormDataReader;
import Util.JsonResponse;

@WebServlet("/api/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studentDAO;
	private ProjectMemberDAO projMemDAO;
	private Gson gson;
	
    public MemberController() {
        super();
    }

	public void init() throws ServletException {
		studentDAO = new StudentDAO();
		projMemDAO = new ProjectMemberDAO();
		gson = new CustomGson().createGson();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String action = request.getPathInfo();
        switch (action) {
        	case "/searchByStudentId":
        		searchByStudentId(request, response);
        		break;
        	case "/listMember":
        		getMemberOfProject(request, response);
        		break;
        	case "/addMember":
        		addMember(request, response);
        		break;
        	case "/removeMember":
        		removeMember(request, response);
        		break;
        	default:
	            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	            response.getWriter().write("Not Found");
	            break;
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void searchByStudentId(HttpServletRequest request, HttpServletResponse response) throws IOException {
		FormDataReader reader = new FormDataReader(request);
	    Map<String, String> formData = reader.getData();
	    
	    if (formData.containsKey("studentId")) {
	        String studentId = formData.get("studentId");
	        JsonResponse<StudentDTO> jsonResponse = null;
			try {
				Student student = studentDAO.selectStudentByStudentCode(studentId);
	            if (student == null) {
	                jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy sinh viên", null);            

	            } else {
	                jsonResponse = new JsonResponse<StudentDTO>(true, HttpServletResponse.SC_OK, "Thành công", new StudentDTO(student));            
	            }
	        } catch (Exception e) {
	        	jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra", null);
	        	e.printStackTrace();
	        }
			response.getWriter().write(gson.toJson(jsonResponse));
	    }
	}
	
	private void getMemberOfProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
		FormDataReader reader = new FormDataReader(request);
	    Map<String, String> formData = reader.getData();
	    
	    if (formData.containsKey("projectId")) {
	        String projectId = formData.get("projectId");
	        JsonResponse<List<StudentDTO>> jsonResponse = null;
			try {
				List<ProjectMember> lstProjMem = projMemDAO.selectProjectMemberByProjectCode(projectId);
				List<StudentDTO> lstStudent = new ArrayList<StudentDTO>();
				for (ProjectMember projMem : lstProjMem) {
					lstStudent.add(new StudentDTO(projMem.getStudent()));
				}
				jsonResponse = new JsonResponse<List<StudentDTO>>(true, HttpServletResponse.SC_OK, "Thành công", lstStudent);
	        } catch (Exception e) {
	        	jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra", null);
	        	e.printStackTrace();
	        }
			response.getWriter().write(gson.toJson(jsonResponse));
	    }
	}
	
	private void addMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
		FormDataReader reader = new FormDataReader(request);
	    Map<String, String> formData = reader.getData();
	    String studentId = formData.get("studentId");
        String projectId = formData.get("projectId");
        JsonResponse<String> jsonResponse = null;
		try {
			ProjectMember projMem = new ProjectMember(studentId, projectId);
			if (isExist(projMem)) {
				jsonResponse = new JsonResponse<>(true, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Sinh viên đã được thêm trước đó", null);
			} else {
				if (projMemDAO.insertProjectMember(projMem))
				{
					jsonResponse = new JsonResponse<>(true, HttpServletResponse.SC_OK, "Thành công", null);
				} else {
					jsonResponse = new JsonResponse<>(true, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Thất bại", null);
				}
			}
        } catch (Exception e) {
        	jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra", null);
        	e.printStackTrace();
        }
		response.getWriter().write(gson.toJson(jsonResponse));
	}
	
	private void removeMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
		FormDataReader reader = new FormDataReader(request);
	    Map<String, String> formData = reader.getData();
	    String studentId = formData.get("studentId");
        String projectId = formData.get("projectId");
        JsonResponse<String> jsonResponse = null;
		try {
			ProjectMember projMem = new ProjectMember(studentId, projectId);
			if (projMemDAO.deleteProjectMember(projMem))
			{
				jsonResponse = new JsonResponse<>(true, HttpServletResponse.SC_OK, "Thành công", null);
			} else {
				jsonResponse = new JsonResponse<>(true, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Thất bại", null);
			}
        } catch (Exception e) {
        	jsonResponse = new JsonResponse<>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra", null);
        	e.printStackTrace();
        }
		response.getWriter().write(gson.toJson(jsonResponse));
	}
	
	private boolean isExist(ProjectMember projMem) {
		List<ProjectMember> lst = projMemDAO.selectProjectMemberByProjectCode(projMem.getProject().getProjectCode());
		for (ProjectMember tmp : lst) {
			if (projMem.getProject().getProjectCode().equals(tmp.getProject().getProjectCode()) &&
				projMem.getStudent().getStudentCode().equals(tmp.getStudent().getStudentCode())) {
				return true;
			}
		}
		return false;
	}
}
