package Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.TopicDAO;
import Util.JsonResponse;
import Models.Topic;

@WebServlet("/api/topic/*")
public class SubjectController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    private TopicDAO topicDAO = new TopicDAO();
	private Gson gson = new Gson();
	
    public void init() {}
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String action = request.getPathInfo();
        try {
            if (action == null || action.isEmpty()) {
                getAll(request, response);
            } else {
            	switch (action) {
            	case "/active":
            		getActive(request, response);
            		break;
            	default:
		            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		            response.getWriter().write("Not Found");
		            break;
            	}
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String action = request.getPathInfo();
        try {
            if (action == null || action.isEmpty()) {
            	insertTopic(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Not Found");
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
    	String action = request.getPathInfo();
        try {
            if (action == null || action.isEmpty()) {
            	updateTopic(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Not Found");
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}
    
    private void getAll(HttpServletRequest request, HttpServletResponse response)
    	throws SQLException, IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonResponse<List<Topic>> jsonResponse = new JsonResponse<>();
        try {
        	List <Topic> listTopic = topicDAO.selectAllTopic();
            jsonResponse = new JsonResponse<List<Topic>>(true, HttpServletResponse.SC_OK, "Thành công!", listTopic);
        } catch (Exception e) {
            jsonResponse = new JsonResponse<List<Topic>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            		"Có lỗi xảy ra.", null);
        }
        String jsonOutput = gson.toJson(jsonResponse);
        response.getWriter().write(jsonOutput);
    }
    
    private void getActive(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonResponse<List<Topic>> jsonResponse = null;
        try {
        	List <Topic> listTopic = topicDAO.selectActiveTopic();
            jsonResponse = new JsonResponse<List<Topic>>(true, HttpServletResponse.SC_OK, "Thành công!", listTopic);
        } catch (Exception e) {
            jsonResponse = new JsonResponse<List<Topic>>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            								"Có lỗi xảy ra.", null);
        }
        String jsonOutput = gson.toJson(jsonResponse);
        response.getWriter().write(jsonOutput);
    }

    private void insertTopic(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
    	BufferedReader reader = request.getReader();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonResponse<Topic> jsonResponse = null;
    	try {
            Topic newTopic = gson.fromJson(reader, Topic.class);
            if (topicDAO.selectTopicByTopicCode(newTopic.getTopicCode()) == null)
            {
                topicDAO.insertTopic(newTopic);
                jsonResponse = new JsonResponse<Topic>(true, HttpServletResponse.SC_CREATED, "Chủ đề đã được tạo thành công,", newTopic);
            }
            else {
                jsonResponse = new JsonResponse<Topic>(false, HttpServletResponse.SC_CONFLICT, "Mã chủ đề đã tồn tại.", null);
            }

        } catch (Exception e) {
            jsonResponse = new JsonResponse<Topic>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra.", null);
        }
    	response.getWriter().write(gson.toJson(jsonResponse)); 
    }
    
    private void updateTopic(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
    	BufferedReader reader = request.getReader();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonResponse<Topic> jsonResponse = null;
    	try {
    		Topic topic = gson.fromJson(reader, Topic.class);
    		if (topicDAO.selectTopicByTopicCode(topic.getTopicCode()) == null) {
    			jsonResponse = new JsonResponse<Topic>(false, HttpServletResponse.SC_NOT_FOUND, "Mã chủ đề không tồn tại.", null);
    		}
    		else {
                topicDAO.updateTopic(topic);
                jsonResponse = new JsonResponse<Topic>(true, HttpServletResponse.SC_OK, "Cập nhật thành công.", topic);
    		}
        } catch (Exception e) {
            jsonResponse = new JsonResponse<Topic>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra.", null);
        }
    	response.getWriter().write(gson.toJson(jsonResponse)); 
    }
}
