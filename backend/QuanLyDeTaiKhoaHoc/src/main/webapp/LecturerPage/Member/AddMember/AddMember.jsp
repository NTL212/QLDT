<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm thành viên</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
	<link rel="stylesheet" href="AddMember.css">
</head>
<body>
	<div class="col-md-15">
		<jsp:include page="../../../components/layout/header/header.jsp"></jsp:include>
	</div>
	<div class="container-fluid">
		<div class="row">
			
			<div class="col-md-10 mx-auto">
	        	<div class = "row">
	        		<h1>Thêm thành viên</h1>
	        	</div>
	        <div class = "row">
	        	<div class = "table-container">
	        			<table>
		        			<thead>
		        				<tr>	
		        					<div class="row">
									    <div class="col">
									        <label for="mssv" class="form-label">MSSV:</label>
									    </div>
									    <div class="col">
									    	<input type="text" class="form-control" id="mssv" value="Nhập mssv">
									    </div>
									        <button class="btn btn-primary">Tìm kiếm</button>
									</div>
									<div class="row">
										<label for="name" class="form-label">Họ và tên:</label>
										<input type="text" readonly class="form-control" id="name" value="Nguyễn Văn A">
										<label for="class" class="form-label">Lớp:</label>
										<input type="text" readonly class="form-control" id="class" value="211103C">
										<label for="department" class="form-label">Khoa: </label>
										<input type="text" readonly class="form-control" id="deparment" value="Công nghê thông tin">
									</div>
									<div class="row">
									    <div class="col">
									    <br><br>
									        <button class="btn btn-primary">Xác nhận</button>
									    </div>
									    <div class="col">
									    <br><br>
									    	<button class="btn btn-primary">Trở về</button>
									    </div>
									</div>
						        </tr>
		        			</thead>
	        			</table>
	        		</div>
        		</div>	        	
        	</div>
        </div>
</body>
</html>


