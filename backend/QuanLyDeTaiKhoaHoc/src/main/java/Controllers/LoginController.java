package Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import DAO.ManagementStaffDAO;
import DAO.AccountDAO;
import DAO.LecturerDAO;
import DAO.ManagementStaffDAO;
import DAO.AdminDAO;
import Models.Account;
import Models.Lecturer;
import Models.ManagementStaff;
import Models.Admin;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountDAO accDAO;
	private LecturerDAO lecturerDAO;

	private ManagementStaffDAO mgtStaffDAO;
	private AdminDAO adminDAO;

	public void init() {
		accDAO = new AccountDAO();
		lecturerDAO = new LecturerDAO();
		mgtStaffDAO = new ManagementStaffDAO();
		adminDAO = new AdminDAO();
	}

	public LoginController() {
		super();
	}
	public static class MessageResponse {
	        private String message;
	        private String messId;
	        
	        public String getMessage() {
	            return message;
	        }

	        public void setMessage(String message) {
	            this.message = message;
	        }
	        public String getMessageId() {
	            return messId;
	        }

	        public void setMessageId(String messId) {
	            this.messId = messId;
	        }
	        
	        
	    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
		
			System.out.print(action);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

	        // Tạo đối tượng dữ liệu (ví dụ: một thông điệp chào mừng)
	        MessageResponse messageResponse = new MessageResponse();
	        messageResponse.setMessage("Hello, world!");
	        messageResponse.setMessageId("mess1");

	        // Chuyển đổi đối tượng dữ liệu thành JSON sử dụng Gson
	        Gson gson = new Gson();
	        String jsonResponse = gson.toJson(messageResponse);

	        PrintWriter out = response.getWriter();
	        out.write(jsonResponse);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		// Đọc dữ liệu từ phần thân của yêu cầu POST
//        BufferedReader reader = request.getReader();
//        StringBuilder requestBody = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            requestBody.append(line);
//        }
//        MessageResponse messageResponse = new MessageResponse();
//        
//        // Xử lý dữ liệu từ phần thân của yêu cầu
//        try {
//            // Giả sử dữ liệu được gửi dưới dạng x-www-form-urlencoded
//            String[] pairs = requestBody.toString().split("&");
//            for (String pair : pairs) {
//                String[] keyValue = pair.split("=");
//                if (keyValue.length == 2) {
//                    String key = URLDecoder.decode(keyValue[0], "UTF-8");
//                    String value = URLDecoder.decode(keyValue[1], "UTF-8");
//                    // Xử lý các cặp key-value ở đây
//                    if (key.equals("messContent")) {
//                    	messageResponse.setMessage(value);
//                    } else if (key.equals("messId")) {
//                    	messageResponse.setMessageId(value);
//                    }
//                    // và tiếp tục với các biến khác nếu cần
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//		
//		//String postId = request.getParameter("postId");
//        response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//		 // Tạo đối tượng dữ liệu (ví dụ: một thông điệp chào mừng)
//        
//
//        // Chuyển đổi đối tượng dữ liệu thành JSON sử dụng Gson
//        Gson gson = new Gson();
//        String jsonResponse = gson.toJson(messageResponse);
//
//        var out = response.getWriter();
//        out.write(jsonResponse);
		authenticate(request, response);
	}

	private void authenticate(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Account loginData = new Account(username, password);
		try {
			Account acc = accDAO.validate(loginData);
			if (acc == null) {
				request.setAttribute("errMsg", "Tên đăng nhập hoặc mật khẩu không đúng!");
				request.setAttribute("loginData", loginData);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Login/Login.jsp");
				dispatcher.forward(request, response);
				return;
			}
			HttpSession session = request.getSession();
			session.setAttribute("userRole", acc.getRole());
			if (acc.getRole().equals("ROLE_ADMIN")) {
				Admin admin = adminDAO.selectByAdCode(username);
				session.setAttribute("user", admin);
				response.sendRedirect(request.getContextPath() + "/Admin/Home/Home.jsp");
			} else if (acc.getRole().equals("ROLE_MGT_STAFF")) {
				ManagementStaff manager = mgtStaffDAO.selectByEmpCode(username);
				session.setAttribute("user", manager);
				response.sendRedirect(request.getContextPath() + "/manager-notification/getNotify");
			} else if (acc.getRole().equals("ROLE_LECT")) {
				Lecturer lecturer = lecturerDAO.selectLecturerByLectCode(username);
				session.setAttribute("user", lecturer);
				response.sendRedirect(request.getContextPath() + "/lecturer-project/list");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
