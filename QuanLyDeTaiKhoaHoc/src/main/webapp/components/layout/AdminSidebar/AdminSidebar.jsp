<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
	<link rel="stylesheet" href="AdminSidebar.css">
</head>
<body>
	<nav id="sidebar" class="bg-light sidebar">
	    <div class="position-sticky">
	        <ul class="nav flex-column">
	            <li class="nav-item">
	                <a class="nav-link active" href="<%=request.getContextPath()%>/Admin/Home/Home.jsp">
	                    Trang của bạn
	                </a>
	            </li>
	            <li class="nav-item">
	                <a class="nav-link" href="<%=request.getContextPath()%>/admincontroller/listadmin">
	                    Quản lý ADMIN
	                </a>
	                
	            </li>
	            <li class="nav-item">
	                <a class="nav-link" href="<%=request.getContextPath()%>/admincontroller/listmanager">
	                    Quản lý Nhân viên 
	                </a>
	            </li>
	            <li class="nav-item">
	                <a class="nav-link" href="<%=request.getContextPath()%>/admincontroller/listlecturer">
	                    Quản lý Giảng viên
	                </a>
	            </li>
	            
	        </ul>
	    </div>
	</nav>
</body>
</html>
