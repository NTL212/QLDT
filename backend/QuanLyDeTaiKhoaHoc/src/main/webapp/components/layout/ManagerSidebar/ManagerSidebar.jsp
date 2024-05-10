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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/components/layout/ManagerSidebar/ManagerSidebar.css">

</head>
<body>
	<nav id="sidebar" class="bg-light sidebar">
	    <div class="position-sticky">
	        <ul class="nav flex-column">
	            <li class="nav-item">
	                <a class="nav-link active" href="<%=request.getContextPath()%>/manager-notification/getNotify"">
	                    Trang của bạn
	                </a>
	            </li>
	            <li class="nav-item">
	                <a class="nav-link" href="<%=request.getContextPath()%>/ProjectController/listproject">
	                    Quản lý đề tài
	                </a>
	            </li>
	            <li class="nav-item">
	                <a class="nav-link" href="<%=request.getContextPath()%>/ProjectController/listapproveproject">
	                    Phê duyệt đề tài
	                </a>
	            </li>
	            <li class="nav-item">
					<a class="nav-link" href="<%=request.getContextPath()%>/SubjectController/listSubject">
					    Danh sách chủ đề
					</a>
	            </li>
	            <li class="nav-item">
	                <a class="nav-link" href="<%=request.getContextPath()%>/ProjectManager/Message/Message.jsp">
	                    Gửi thông báo
	                </a>
	            </li>
	        </ul>
	    </div>
	</nav>
</body>
</html>
