package Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ProjectDAO;
import DAO.TopicDAO;
import Models.Project;
import DAO.ManagementStaffDAO;
import Models.Topic;
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

    public void init() {
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
                case "/getSendMessage":
                	getSendMessage(request, response);
                    break;
                case "/getNotify":
                	getRecieveMessage(request, response);
                	break;
                case "/sendMessage":
                	sendMessage(request, response);
                	break;
                case "/sendApproveProjectMessage":
                	sendApproveProjectMessage(request, response);
                	break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void getSendMessage(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
		session.getAttribute("user");
		ManagementStaff manager =  (ManagementStaff)session.getAttribute("user");
		request.setAttribute("title", "Đã gửi");
        List < Notification > listNtf = notificationDAO.selectSentNotificationsByUserId(manager.getEmpCode());
        request.setAttribute("listNotification", listNtf);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ProjectManager/Home/Home.jsp");
        dispatcher.forward(request, response);
    }

    private void getRecieveMessage(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
		ManagementStaff emp = (ManagementStaff) session.getAttribute("user");
		if (emp != null) {
			List <Notification> lstNoti = notificationDAO.selectNotificationsByUserId(emp.getEmpCode());
	        request.setAttribute("listNotification", lstNoti);
	        request.setAttribute("title", "Đã nhận");
	        request.setAttribute("recieve", "Người nhận");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/ProjectManager/Home/Home.jsp");
	        dispatcher.forward(request, response);
		}
    }
    
    private void sendMessage(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
    	ManagementStaff sendPerson = (ManagementStaff)session.getAttribute("user");
    	String recievePerson = String.valueOf(request.getParameter("recieveperson"));
    	String messageTitle =  String.valueOf(request.getParameter("messagetitle"));
    	String messageContent =  String.valueOf(request.getParameter("messagecontent"));
    	System.out.print(messageTitle + recievePerson + messageContent );
		notificationDAO.insertNotification(new Notification(sendPerson.getEmpCode(),recievePerson, messageTitle, messageContent,LocalDateTime.now()));
		response.sendRedirect(request.getContextPath() + "/manager-notification/getSendMessage");
    }
    
    private void sendApproveProjectMessage(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession();
    	ManagementStaff sendPerson = (ManagementStaff)session.getAttribute("user");
    	String recievePerson = String.valueOf(request.getParameter("recieveperson"));
    	String messageTitle =  String.valueOf(request.getParameter("messagetitle"));;
    	String messageContent =  String.valueOf(request.getParameter("messagecontent"));;
		notificationDAO.insertNotification(new Notification(sendPerson.getEmpCode(),recievePerson, messageTitle, messageContent,LocalDateTime.now()));
		response.sendRedirect(request.getContextPath() + "/ProjectController/listapproveproject");
    }
}
