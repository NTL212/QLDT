<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<nav id="sidebar" class="bg-light sidebar">
	    <div class="position-sticky">
	        <ul class="nav flex-column">
	            <li class="nav-item">
					<a class="nav-link" href="<%=request.getContextPath()%>/lecturer-project/list">
					    Danh sách đề tài
					</a>
	            </li>
	            <li class="nav-item">
	                <a class="nav-link" href="<%=request.getContextPath()%>/lecturer-project/load-propose-page">
	                    Đề xuất đề tài
	                </a>
	            </li>
	            <li class="nav-item">
	                <a class="nav-link" href="<%=request.getContextPath()%>/lecturer-project/myproj">
	                    Đề tài của tôi
	                </a>
	            </li>
	            <li class="nav-item">
	                <a class="nav-link" href="<%=request.getContextPath()%>/LecturerPage/Member/ListMember/ListMember.jsp">
	                    Thành viên tham gia
	                </a>
	            </li>
	            <li>
	                <div class="notification-dropdown">
						<a class="notification-dropdown-button">
							Thông báo
						</a>
						<div class="notification-dropdown-content">
							<a class="nav-link" href="<%=request.getContextPath()%>/lecturer-notification/getSendMessage">Đã gửi</a> 
							<a class="nav-link" href="<%=request.getContextPath()%>/lecturer-notification/getNotify">Đã nhận</a>
							<a class="nav-link" href="<%=request.getContextPath()%>/LecturerPage/Notification/Send/Send.jsp">Gửi lời nhắn</a>
						</div>
					</div>
	            </li>
	        </ul>
	    </div>
	</nav>
</body>
</html>
