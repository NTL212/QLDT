package Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class FormDataReader {
	HttpServletRequest request;

	public FormDataReader(HttpServletRequest request) {
		super();
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public Map<String, String> getData() throws IOException {
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
	        return formData;
	            	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return null;
	}
}
