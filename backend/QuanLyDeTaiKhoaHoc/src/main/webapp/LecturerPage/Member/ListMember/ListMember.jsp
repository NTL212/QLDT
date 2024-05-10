<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thành viên tham gia</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
	<link rel="stylesheet" href="ListMember.css">
</head>
<body>
	<div class="col-md-15">
		<jsp:include page="../../../components/layout/header/header.jsp"></jsp:include>
	</div>
	<div class="container-fluid">
		<div class="row">
			
			<div class="col-md-2">
				<jsp:include page="../../../components/layout/LecturerSidebar/LecturerSidebar.jsp"></jsp:include>
			</div>
			<br><br>
			<div class="col-md-9">
	        	<div class = "row">
	        		
	        		<h1>Thành viên tham gia</h1>
	        	</div>
	        	<div class = "row">
		        	<div class = "table-container">
		        			<table>
			        			<thead>
			        				<tr>
							            <th width = 50px>Stt</th>
							            <th width = 200px>Họ và tên</th>
							            <th width = 100px>MSSV</th>
							            <th width = 300px>Ngày sinh</th>
							            <th width = 100px>Lớp</th>
							            <th width = 500px>Khoa</th>
							        </tr>
			        			</thead>
			        			<tbody>
				                    <tr>         	
				                        <td>1</td>
				                        <td>Nguyễn Văn A</td>
				                        <td>21110881</td>
				                        <td>10-05-2003</td>
				                        <td>21103C1</td>
				                        <td>Công nghệ thông tin</td>
				                        
				                    </tr>
				                    <tr>
				                        <td>2</td>
				                        <td>Trần Thị B</td>
				                        <td>21110882</td>
				                        <td>05-10-2002</td>
				                        <td>200003S</td>
				                        <td>Công nghệ thông tin</td>
				                    </tr>
			        			</tbody>
		        			</table>
		        		</div>
		        		<div class="row">
						 <div class="col-md-offset-3">
						      <div class="centered-button">
						          	<button class="btn btn-primary">Thêm </button>
						          	<button class="btn btn-primary">Xóa</button>
						          	<button class="btn btn-primary">Sửa</button>
						          	<button class="btn btn-primary">Thêm từ file excel</button>
						          	<br><br>
						      </div>
					     </div>
					 </div>
					 <br><br>
		        	</div>
		        	
	        	</div>
        	</div>
        </div>
</body>
</html>