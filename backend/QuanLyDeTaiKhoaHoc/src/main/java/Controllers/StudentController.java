package Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import DAO.StudentDAO;
import DTO.StudentDTO;
import Models.Student;
import Util.CustomGson;
import Util.FormDataReader;
import Util.JsonResponse;

@WebServlet("/api/student/*")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studentDAO;
	private Gson gson;

    public StudentController() {
        super();
    }
    
	public void init() throws ServletException {
		studentDAO = new StudentDAO();
		gson = new CustomGson().createGson();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String action = request.getPathInfo();
        try {
        	switch (action) {
        	case "/getById":
        		searchByStudentId(request, response);
        		break;
        	case "/getDetailById":
        		getDetailByStudentId(request, response);
        		break;
        	case "/getAll":
				getAll(request, response);
        		break;
        	case "/createStudent":
        		insertStudent(request, response);
        		break;
        	case "/updateStudent":
        		updateStudent(request, response);
        		break;
        	default:
	            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	            response.getWriter().write("Not Found");
	            break;
	        }
        } catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
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
	
	private void getDetailByStudentId(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

	private void getAll(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {        
        JsonResponse<List<StudentDTO>> jsonResponse = null;
        try {
        	List <Student> lstStudent = studentDAO.selectAll();
        	List<StudentDTO> lstStudentDTO = new ArrayList<StudentDTO>();
        	for (Student student :lstStudent) {
        		lstStudentDTO.add(new StudentDTO(student));
        	}
            jsonResponse = new JsonResponse<List<StudentDTO>>(true, HttpServletResponse.SC_OK, "Thành công!", lstStudentDTO);
        } catch (Exception e) {
            jsonResponse = new JsonResponse<List<StudentDTO>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            								"Có lỗi xảy ra.", null);
            e.printStackTrace();
        }
        String jsonOutput = gson.toJson(jsonResponse);
        response.getWriter().write(jsonOutput);
    }
	
	private void insertStudent(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
    	BufferedReader reader = request.getReader();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonResponse<String> jsonResponse = null;
    	try {
            Student newStudent = gson.fromJson(reader, Student.class);
            if (studentDAO.selectStudentByStudentCode(newStudent.getStudentCode()) == null)
            {
                studentDAO.insertStudent(newStudent);
                jsonResponse = new JsonResponse<String>(true, HttpServletResponse.SC_CREATED, "Thêm sinh viên thành công.", null);
            }
            else {
                jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_CONFLICT, "Mã sinh viên đã tồn tại.", null);
            }

        } catch (Exception e) {
            jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra.", null);
        }
    	response.getWriter().write(gson.toJson(jsonResponse)); 
    }
	
	private void updateStudent(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
    	BufferedReader reader = request.getReader();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonResponse<String> jsonResponse = null;
    	try {
            Student student = gson.fromJson(reader, Student.class);
            if (studentDAO.selectStudentByStudentCode(student.getStudentCode()) != null)
            {
                studentDAO.updateStudent(student);
                jsonResponse = new JsonResponse<String>(true, HttpServletResponse.SC_OK, "Cập nhật thành công.", null);
            }
            else {
                jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_CONFLICT, "Mã sinh viên không tồn tại.", null);
            }
        } catch (Exception e) {
            jsonResponse = new JsonResponse<String>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra.", null);
        }
    	response.getWriter().write(gson.toJson(jsonResponse)); 
    }
}
