<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/components/layout/header/header.css">
</head>
<body>
	<div class="header">
		<a class="active" href="#home">NGHIÊN CỨU KHOA HỌC</a>
		<div class="dropdown">
			<button class="drop-down-button">
				<img class="icon"
					src="<%=request.getContextPath()%>/assets/images/userIcon.svg" />
					<span class="triangle">&#9662;</span>
			</button>
			<div class="dropdown-content">
				<a href="#">Xem thông tin cá nhân</a>
				<a href="<%=request.getContextPath()%>/LogoutServlet">Đăng xuất</a>
			</div>
		</div>
	</div>
</body>
</html>