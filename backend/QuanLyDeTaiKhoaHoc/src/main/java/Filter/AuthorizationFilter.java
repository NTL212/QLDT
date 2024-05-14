package Filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizationFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);
		String loginURI = httpRequest.getContextPath() + "/login";
		String userRole = null;
		boolean loggedIn = false;
		boolean loginRequest = httpRequest.getRequestURI().equals(loginURI)
				|| httpRequest.getRequestURI().equals(httpRequest.getContextPath() + "/LoginServlet");
		boolean logoutRequest = httpRequest.getRequestURI().equals(httpRequest.getContextPath() + "/LogoutServlet");

		if (isStaticResource(httpRequest.getRequestURI())) {
			chain.doFilter(request, response);
			return;
		}

		if (session != null) {
			userRole = (String) session.getAttribute("userRole");
			if (userRole != null) {
				loggedIn = true;
			}
		}

		if (loggedIn || loginRequest || logoutRequest) {
			if (loggedIn && !logoutRequest) {
				String userHomePage = determineHomePage(userRole);
				if (!httpRequest.getRequestURI().contains(userHomePage)) {
					httpResponse.sendRedirect(httpRequest.getContextPath() + userHomePage);
					return;
				}

				if (!hasAccessToPage(httpRequest.getRequestURI(), userRole)) {
					httpResponse.sendRedirect(httpRequest.getContextPath() + userHomePage);
					return;
				}
			}
			chain.doFilter(request, response);
		} else {
			httpResponse.sendRedirect(loginURI);
		}
	}

	private String determineHomePage(String userRole) {
		switch (userRole) {
		case "ROLE_LECT":
			return "/lecturer";
		case "ROLE_MGT_STAFF":
			return "/manager";
		case "ROLE_ADMIN":
			return "/admin";
		default:
			return "/login";
		}
	}

	private boolean hasAccessToPage(String requestURI, String userRole) {
		switch (userRole) {
		case "ROLE_LECT":
			return requestURI.contains("/lecturer");
		case "ROLE_MGT_STAFF":
			return requestURI.contains("/manager");
		case "ROLE_ADMIN":
			return requestURI.contains("/admin");
		default:
			return false;
		}
	}

	private boolean isStaticResource(String uri) {
		return uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".jpg") || uri.endsWith(".png");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
