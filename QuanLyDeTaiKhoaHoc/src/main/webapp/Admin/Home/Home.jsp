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
	<link rel="stylesheet" type = "text/css" href="<%=request.getContextPath()%>/Admin/Home/Home.css">
	<link rel="stylesheet" href="Home.css">
	<jsp:include page="../../components/layout/header/header.jsp"></jsp:include>
</head>
<body>
	
	<div class = "container-fluid">
		<div class = "row">
			<div class = "col-md-2">
				<jsp:include page="../../components/layout/AdminSidebar/AdminSidebar.jsp"></jsp:include>
			</div>
			<div class = "col-md-9">
	        	<div class = "row text-center">
	        		<h1>Thông báo</h1>
	        	</div>
	        	<div class = "row">
		        	<div class = "table-container">
		        			<table>
			        			<thead>
			        				<tr>
							            <th width = 200px scope="col">Người gửi</th>
							            <th width = 500px scope="col">Tiêu đề</th>
							            <th width = 200px scope="col">Thời gian</th>
							        </tr>
			        			</thead>
			        			<tbody>
			        				
			        			</tbody>
		        			</table>
		        		</div>
	        	</div>
	        	<div class = "row form-label">
	        		<p>Nội dung</p>
	        	</div>
	        	<div class = "row">
		        		<textarea id="Textareacontent" name ="Textareacontent" class = "form-control" rows =  "5" cols = "5" placeholder="Nội dung">
		        		</textarea>
	        	</div>
	        </div>
		</div>
	</div>
</body>
</html>