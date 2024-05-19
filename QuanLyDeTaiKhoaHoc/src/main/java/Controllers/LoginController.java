package Controllers;

import java.io.IOException;

import java.io.BufferedReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.FormDataReader;
import Util.JsonResponse;
import DAO.AccountDAO;
import Models.Account;

@WebServlet("/api/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AccountDAO accDAO;
	
    public void init() {
        accDAO = new AccountDAO();
    }
	
    public LoginController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    FormDataReader reader = new FormDataReader(request);
	    Map<String, String> formData = reader.getData();
	    
	    if (formData.containsKey("username") && formData.containsKey("password")) {
	        String username = formData.get("username");
	        String password = formData.get("password");
	        authenticate(request, response, username, password);
	    } else {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        response.getWriter().write("Invalid request data");
	    }
	}

    private void authenticate(HttpServletRequest request, HttpServletResponse response, String username, String password) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Account loginData = new Account(username, password);

        JsonResponse<Account> jsonResponse = new JsonResponse<>();
        
        try {
            Account acc = accDAO.validate(loginData);

            if (acc == null) {
                jsonResponse.setSuccess(false);
                jsonResponse.setStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
                jsonResponse.setMessage("Tên đăng nhập hoặc mật khẩu không đúng!");

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                jsonResponse.setSuccess(true);
                jsonResponse.setStatusCode(HttpServletResponse.SC_OK);
                jsonResponse.setMessage("Đăng nhập thành công!");
                jsonResponse.setResult(acc);
            }

            Gson gson = new Gson();
            String jsonOutput = gson.toJson(jsonResponse);
            response.getWriter().write(jsonOutput);

        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.setSuccess(false);
            jsonResponse.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            jsonResponse.setMessage("Đã xảy ra lỗi trong quá trình xác thực.");

            Gson gson = new Gson();
            String jsonOutput = gson.toJson(jsonResponse);
            response.getWriter().write(jsonOutput);
        }
    }
}