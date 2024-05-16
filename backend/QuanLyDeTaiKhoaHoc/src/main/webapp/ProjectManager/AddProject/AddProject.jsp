<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="DAO.ProjectDAO, Models.Project, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<title>Đề tài nghiên cứu khoa học</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" ></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
	<link rel="stylesheet" href="AddProject.css">
	<script src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
    
</head>
<body>
<div class="container mt-5">
    <form id="insertProjectManager" action="<%=request.getContextPath()%>/ProjectController/insertproject" method="post">
    	<div class="row text-center">
            <h1>Thêm đề tài</h1>
        </div>
    	<div class="row">
            <div class="col-md-4 mb-3">
                <label for="projectCode" class="form-label">Mã đề tài:</label>
            	<input type="text" class="form-control border-2" id="projectCode" name="projectCode" required>
            </div>
            <div class="col-md-4 mb-3">
            	<label for="projectTopic" class="form-label">Chủ đề:</label>
                <select class="form-select border-2" id="projectTopic" name="projectTopic" required>
	    			<c:forEach var="topic" items="${listTopic}">
				        <option value="${topic.getTopicCode()}">${topic.getName()}</option>
				    </c:forEach>
			    </select>
            </div>
        </div>
        <div class="mb-3">
            <label for="projectTitle" class="form-label">Tên đề tài:</label>
            <input type="text" class="form-control border-2" id="projectTitle" name="projectTitle" required>
        </div>
        <div class="row">
            <div class="col-md-4 mb-3">
                <label for="registrationStartDate" class="form-label">Ngày mở đăng kí:</label>
                <input type="date" class="form-control border-2" id="registrationStartDate" name="registrationStartDate" required>
            </div>
            <div class="col-md-4 mb-3">
                <label for="startDate" class="form-label">Ngày bắt đầu:</label>
                <input type="date" class="form-control border-2" id="startDate" name="startDate" required>
            </div>
        </div>
        <div class="row">
        	<div class="col-md-4 mb-3">
                <label for="registrationEndDate" class="form-label">Ngày kết thúc đăng kí:</label>
                <input type="date" class="form-control border-2" id="registrationEndDate" name="registrationEndDate" required>
            </div>
            <div class="col-md-4 mb-3">
                <label for="endDate" class="form-label">Ngày hết hạn:</label>
                <input type="date" class="form-control border-2" id="endDate" name="endDate" required>
            </div>
        </div>
        <div class="row">
            <div class="col-md-3 mb-3">
                <label for="expectedBudget" class="form-label">Kinh phí dự kiến:</label>
                <input type="number" class="form-control border-2" id="expectedBudget" min = 1000000 max = 200000000 name="expectedBudget" required>
            </div>
            <div class="col-md-3 mb-3">
	            <label for="teamMembers" class="form-label">Số lượng thành viên tối đa:</label>
	            <input type="number" class="form-control border-2" id="teamMembers" name="teamMembers" min="2" max="6" required>
            </div>
            <div class="col mb-3">
	            <label for="attachmentFile" class="form-label">File đính kèm:</label>
	            <input type="file" class="form-control border-2" id="attachmentFile" name="attachmentFile">
	        </div>
        </div>
        <div class="mb-3">
            <label for="projectDescription" class="form-label">Mô tả:</label>
            <textarea class="form-control border-2" id="projectDescription" name="projectDescription" rows="4" required></textarea>
        </div>
        
        <div class="col offset-md-10 mb-3">
            <button type="submit" class="btn btn-primary">Lưu</button>
            <a href="<%=request.getContextPath()%>/ProjectForManager/listproject" class="btn btn-secondary">Hủy</a>
        </div>
    </form>
    <script >
        document.getElementById('insertProjectManager').addEventListener('submit', function (event) {
            event.preventDefault();
            function showToast(message) {
                Toastify({
                    text: message,
                    duration: 3000,
                    close: true,
                    gravity: "top",
                    style: {
                        background: "red",
                        color: "white",
                    }
                }).showToast();
            }
            
            var inputElement = document.getElementById("projectCode");
            var inputValue = inputElement.value; // Get the value of the input element
            var b = 1;

            <%
                List<Project> ls = (List<Project>) request.getAttribute("listProject");
                for (Project pr : ls) {
                    %>
                    // Use JavaScript to compare with the value of the input element
                    if ("<%= pr.getProjectCode() %>" === inputValue) {
                        // Your logic here
                        b = 2;
                    }
                    <%
                }
            %>

            var openRegDate = document.getElementById("registrationStartDate").value;
            var closeRegDate = document.getElementById("registrationEndDate").value;
            var startDate = document.getElementById("startDate").value;
            var endDate = document.getElementById("endDate").value;

            if (openRegDate > closeRegDate) {
                showToast('Ngày đăng ký mở không thể lớn hơn ngày kết thúc đăng ký');
            } else if (closeRegDate > startDate) {
                showToast('Ngày đăng kết thúc đăng ký không thể lớn hơn ngày bắt đầu');
            } else if (startDate > endDate) {
                showToast('Ngày bắt đầu không thể lớn hơn ngày kết thúc');
            } else if (b === 2) {
                showToast('Mã này đã được sủ dụng');
            } else {
            	$(this).submit();
            }
        });
    </script>
</div>
</body>
</html>