package Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import DTO.NoticeDTO;
import Models.Project;
import DAO.ManagementStaffDAO;
import Models.Topic;
import Util.CustomTimeGson;
import Util.FormDataReader;
import Util.JsonResponse;
import DAO.NotificationDAO;
import Models.ManagementStaff;
import Models.Notification;
import Models.ManagementStaff;

@WebServlet("/manager-notification/*")
public class ManagerNotifycationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String ApproveTitle = "Kết quả phê duyệt đề tài";
	private final String HappyMessage = "Chúc mừng, đề tài của bạn đã được phê duyệt";
	private final String WorriedMessage = "Xin lỗi, đề tài của bạn đã được phê duyệt. Vì các lý do sau:";

	private NotificationDAO notificationDAO = new NotificationDAO();
	private ManagementStaffDAO managementStaffDAO = new ManagementStaffDAO();
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
			case "/api/sendApproveProjectMessage":
				sendApproveProjectMessage(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void getSendMessage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
//		HttpSession session = request.getSession();
//		session.getAttribute("user");
//		ManagementStaff manager = (ManagementStaff) session.getAttribute("user");
//		request.setAttribute("title", "Đã gửi");
//		List<Notification> listNtf = notificationDAO.selectSentNotificationsByUserId(manager.getEmpCode());
//		request.setAttribute("listNotification", listNtf);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/ProjectManager/Home/Home.jsp");
//		dispatcher.forward(request, response);
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String managerId = request.getParameter("managerId");
        JsonResponse<List < NoticeDTO >> jsonResponse = null;
        try {
        	List < Notification > listNtf = notificationDAO.selectSentNotificationsByUserId(managerId);
        	List<NoticeDTO> listNDto = new ArrayList<>();
        	for (Notification ntf : listNtf) {
        		listNDto.add(new NoticeDTO(ntf));
			}
            jsonResponse = new JsonResponse<List < NoticeDTO >>(true, HttpServletResponse.SC_OK, "Thành công!", listNDto);
        } catch (Exception e) {
            jsonResponse = new JsonResponse<List < NoticeDTO >>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            								"Có lỗi xảy ra.", null);
        }
        String jsonOutput = gson.toJson(jsonResponse);
        response.getWriter().write(jsonOutput);
	}

	private void getRecieveMessage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
//		HttpSession session = request.getSession();
//		ManagementStaff emp = (ManagementStaff) session.getAttribute("user");
//		if (emp != null) {
//			List<Notification> lstNoti = notificationDAO.selectNotificationsByUserId(emp.getEmpCode());
//			request.setAttribute("listNotification", lstNoti);
//			request.setAttribute("title", "Đã nhận");
//			request.setAttribute("recieve", "Người nhận");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/ProjectManager/Home/Home.jsp");
//			dispatcher.forward(request, response);
//		}
//		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String managerId = request.getParameter("managerId");
        JsonResponse<List < NoticeDTO >> jsonResponse = null;
        try {
        	List <Notification> lstNoti = notificationDAO.selectNotificationsByUserId(managerId);
        	List<NoticeDTO> listNDto = new ArrayList<>();
        	for (Notification ntf : lstNoti) {
        		listNDto.add(new NoticeDTO(ntf));
			}
            jsonResponse = new JsonResponse<List < NoticeDTO >>(true, HttpServletResponse.SC_OK, "Thành công!", listNDto);
        } catch (Exception e) {
            jsonResponse = new JsonResponse<List < NoticeDTO >>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            								"Có lỗi xảy ra.", null);
        }
        String jsonOutput = gson.toJson(jsonResponse);
        response.getWriter().write(jsonOutput);
	}

	private void sendMessage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
//		HttpSession session = request.getSession();
//		ManagementStaff sendPerson = (ManagementStaff) session.getAttribute("user");
//		String recievePerson = String.valueOf(request.getParameter("recieveperson"));
//		String messageTitle = String.valueOf(request.getParameter("messagetitle"));
//		String messageContent = String.valueOf(request.getParameter("messagecontent"));
//		System.out.print(messageTitle + recievePerson + messageContent);
//		notificationDAO.insertNotification(new Notification(sendPerson.getEmpCode(), recievePerson, messageTitle,
//				messageContent, LocalDateTime.now()));
//		response.sendRedirect(request.getContextPath() + "/manager-notification/getSendMessage");
		
		FormDataReader reader = new FormDataReader(request);
	    Map<String, String> formData = reader.getData();
	    response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
		
		String managerId = formData.get("managerId");
	    String recievePerson = formData.get("recieveId");
    	String messageTitle =  formData.get("messagetitle");
    	String messageContent =  formData.get("messagecontent");

        JsonResponse<NoticeDTO> jsonResponse = null;
    	try {
    		Notification noti = new Notification(managerId,recievePerson, messageTitle, messageContent,LocalDateTime.now());
    		notificationDAO.insertNotification(noti);
    		NoticeDTO noticeDTO = new NoticeDTO(noti);
    		jsonResponse = new JsonResponse<NoticeDTO>(true, HttpServletResponse.SC_CREATED, "Thông báo đã gửi thành công", noticeDTO);

        } catch (Exception e) {
            jsonResponse = new JsonResponse<NoticeDTO>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra.", null);
        }
    	response.getWriter().write(gson.toJson(jsonResponse)); 
	}

	private void sendApproveProjectMessage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
//		HttpSession session = request.getSession();
//		ManagementStaff sendPerson = (ManagementStaff) session.getAttribute("user");
//		String recievePerson = String.valueOf(request.getParameter("recieveperson"));
//		String messageTitle = String.valueOf(request.getParameter("messagetitle"));
//		;
//		String messageContent = String.valueOf(request.getParameter("messagecontent"));
//		;
//		notificationDAO.insertNotification(new Notification(sendPerson.getEmpCode(), recievePerson, messageTitle,
//				messageContent, LocalDateTime.now()));
//		response.sendRedirect(request.getContextPath() + "/ProjectController/listapproveproject");
		
		FormDataReader reader = new FormDataReader(request);
	    Map<String, String> formData = reader.getData();
	    response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
		
		String managerId = formData.get("managerId");
	    String recievePerson = formData.get("recieveId");
    	String messageTitle =  formData.get("messagetitle");
    	String messageContent =  formData.get("messagecontent");

        JsonResponse<NoticeDTO> jsonResponse = null;
    	try {
    		Notification noti = new Notification(managerId,recievePerson, messageTitle, messageContent,LocalDateTime.now());
    		notificationDAO.insertNotification(noti);
    		NoticeDTO noticeDTO = new NoticeDTO(noti);
    		jsonResponse = new JsonResponse<NoticeDTO>(true, HttpServletResponse.SC_CREATED, "Thông báo đã gửi thành công", noticeDTO);

        } catch (Exception e) {
            jsonResponse = new JsonResponse<NoticeDTO>(false, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra.", null);
        }
    	response.getWriter().write(gson.toJson(jsonResponse)); 
	}
}
