package Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import DAO.ProjectDAO;
import DAO.TopicDAO;
import Models.Project;
import Models.Registration;
import DAO.ManagementStaffDAO;
import Models.Topic;
import Util.CustomTimeGson;
import Util.JsonResponse;
import DAO.NotificationDAO;
import Models.ManagementStaff;
import Models.Notification;
import Models.ManagementStaff;
import Models.Account;
import Models.Lecturer;
import DAO.AccountDAO;
import DAO.LecturerDAO;
@WebServlet("/lecturer-notification/*")
public class LecturerNotifycationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private NotificationDAO notificationDAO = new NotificationDAO();
    private AccountDAO accountDAO = new AccountDAO();
    private Gson gson;

    public void init() {
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
    	String action = request.getPathInfo();
        try {
            switch (action) {
                case "/api/getSendMessage":
                	getSendMessage(request, response);
                    break;
                case "/api/getNotify":
                	getRecieveMessage(request, response);
                	break;
                case "/api/sendMessage":
                	sendMessage(request, response);
                	break;
                case "/api/getAllAcc":
                	getAllAcc(request, response);
                	break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void getSendMessage(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
//    	HttpSession session = request.getSession();
//		session.getAttribute("user");
//		Lecturer lec =  (Lecturer)session.getAttribute("user");
//		request.setAttribute("title", "Đã gửi");
//        List < Notification > listNtf = notificationDAO.selectSentNotificationsByUserId(lec.getLecturerCode());
//        request.setAttribute("listNotification", listNtf);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/LecturerPage/Notification/Receive/receive.jsp");
//        dispatcher.forward(request, response);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String lectCode = request.getParameter("id");
        JsonResponse<List < Notification >> jsonResponse = null;
        try {
        	//List<Project> listProject = projectDAO.selectActiveProjectForLecturer();
        	List < Notification > listNtf = notificationDAO.selectSentNotificationsByUserId(lectCode);
            jsonResponse = new JsonResponse<List < Notification >>(true, HttpServletResponse.SC_OK, "Thành công!", listNtf);
        } catch (Exception e) {
            jsonResponse = new JsonResponse<List < Notification >>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            								"Có lỗi xảy ra.", null);
        }
        String jsonOutput = gson.toJson(jsonResponse);
        response.getWriter().write(jsonOutput);
    }

    private void getRecieveMessage(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
//    	HttpSession session = request.getSession();
//    	Lecturer lec = (Lecturer) session.getAttribute("user");
//		if (lec != null) {
//			List <Notification> lstNoti = notificationDAO.selectNotificationsByUserId(lec.getLecturerCode());
//	        request.setAttribute("listNotification", lstNoti);
//	        request.setAttribute("title", "Đã nhận");
//	        request.setAttribute("recieve", "Người nhận");
//	        RequestDispatcher dispatcher = request.getRequestDispatcher("/LecturerPage/Notification/Sent/sent.jsp");
//	        dispatcher.forward(request, response);
//		}
		 response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        String lectCode = request.getParameter("id");
	        JsonResponse<List < Notification >> jsonResponse = null;
	        try {
	        	//List<Project> listProject = projectDAO.selectActiveProjectForLecturer();
	        	List <Notification> lstNoti = notificationDAO.selectNotificationsByUserId(lectCode);
	            jsonResponse = new JsonResponse<List < Notification >>(true, HttpServletResponse.SC_OK, "Thành công!", lstNoti);
	        } catch (Exception e) {
	            jsonResponse = new JsonResponse<List < Notification >>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
	            								"Có lỗi xảy ra.", null);
	        }
	        String jsonOutput = gson.toJson(jsonResponse);
	        response.getWriter().write(jsonOutput);
    }
    
    private void sendMessage(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
//    	HttpSession session = request.getSession();
//    	ManagementStaff sendPerson = (ManagementStaff)session.getAttribute("user");
//    	String recievePerson = String.valueOf(request.getParameter("recieveperson"));
//    	String messageTitle =  String.valueOf(request.getParameter("messagetitle"));
//    	String messageContent =  String.valueOf(request.getParameter("messagecontent"));
//		notificationDAO.insertNotification(new Notification(sendPerson.getEmpCode(),recievePerson, messageTitle, messageContent,LocalDateTime.now()));
//		response.sendRedirect(request.getContextPath() + "lecturer-notification/getSendMessage");
		
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
	    String recievePerson = formData.get("recieveperson");
    	String messageTitle =  formData.get("messagetitle");
    	String messageContent =  formData.get("messagecontent");
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonResponse<Notification> jsonResponse = null;
    	try {
    		Notification noti = new Notification(lectCode,recievePerson, messageTitle, messageContent,LocalDateTime.now());
    		notificationDAO.insertNotification(noti);
    		jsonResponse = new JsonResponse<Notification>(true, HttpServletResponse.SC_CREATED, "Thông báo đã gửi thành công", noti);

        } catch (Exception e) {
            jsonResponse = new JsonResponse<Notification>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra.", null);
        }
    	response.getWriter().write(gson.toJson(jsonResponse)); 
		
		
    }
    
    private void getAllAcc(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {

		 response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        String lectCode = request.getParameter("id");
	        JsonResponse<List < Account >> jsonResponse = null;
	        try {
	        
	        	List <Account> accs = accountDAO.selectAllAccount();
	            jsonResponse = new JsonResponse<List < Account >>(true, HttpServletResponse.SC_OK, "Thành công!", accs);
	        } catch (Exception e) {
	            jsonResponse = new JsonResponse<List < Account >>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
	            								"Có lỗi xảy ra.", null);
	        }
	        String jsonOutput = gson.toJson(jsonResponse);
	        response.getWriter().write(jsonOutput);
    }
    
}
