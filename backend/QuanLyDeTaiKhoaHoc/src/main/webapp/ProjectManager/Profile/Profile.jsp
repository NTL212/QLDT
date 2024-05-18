<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
	<link rel="stylesheet" href="Profile.css">
	<jsp:include page="../../components/layout/Managerheader/Managerheader.jsp"></jsp:include>
</head>
<body>
	
	<div class = "container-fluid">
		<div class = "row">
			<div class = "col-md-2">
				<jsp:include page="../../components/layout/ManagerSidebar/ManagerSidebar.jsp"></jsp:include>
			</div>
			<div class = "col-md-9 content">
				<div class = "row text-center">
	        		<h1>Thông tin cá nhân</h1>
	        	</div>
	        	<div class = "row">
			        <div class="offset-md-2 col-md-4">
				        <div class="row">
				            <label for="fullName">Họ và tên:</label>
				            <input type="text" class="form-control" id="fullName" value ="${user.getName()}" disabled>
						</div>
				        <!-- Mã số -->
				        <div class="row">
				            <label for="maSo">Mã số:</label>
				            <input type="text" class="form-control" id="maSo" value ="${user.getEmpCode()}" disabled >
				        </div>
				
				        <!-- Giới tính -->
				        <div class="row">
				            <label for="gender">Giới tính:</label>
				            <select class="form-control" id="gender">
				                <option value="male">Nam</option>
				                <option value="female">Nữ</option>
				                <option value="other">Khác</option>
				            </select>
				        </div>
				
				        <!-- CCCD -->
				        <div class="row">
				            <label for="cccd">CCCD:</label>
				            <input type="text" class="form-control" id="cccd" value ="${user.getIdNum()}" disabled>
				        </div>
					</div>
			        <!-- Ngày sinh -->
			        <div class="offset-md-1 col-md-4">
			        	<div class = row>
				            <label for="dob">Ngày sinh:</label>
				            <input type="date" class="form-control" id="dob" value ="${user.getBirthday()}" disabled>
			        	</div>
			
				        <!-- SDT -->
				        <div class="row">
				            <label for="phone">SDT:</label>
				            <input type="tel" class="form-control" id="phone" value ="${user.getPhoneNum()}" disabled>
				        </div>
				
				        <!-- Địa chỉ -->
				        <div class="row">
				            <label for="address">Địa chỉ:</label>
				            <input type="text" class="form-control" id="address" value ="${user.getAddress()}" disabled>
				        </div>
				
				        <!-- Email -->
				        <div class="row">
				            <label for="email">Email:</label>
				            <input type="email" class="form-control" id="email" value ="${user.getEmail()}" disabled>
				        </div>
					</div>
	        	</div>
	        	<div class = row>
	        		<div class = "col offset-md-8 space">
	        			<button type ="button" class = "btn btn-primary">Sửa</button>
	        			<button type ="button" class = "btn btn-primary">Hủy</button>
	        		</div>
	        	</div>
	        </div>
		</div>
	</div>
</body>
</html>