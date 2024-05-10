<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
	<link rel="stylesheet" type = "text/css" href="<%=request.getContextPath()%>/Admin/EditAccount/EditAccount.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/Admin/EditAccount/EditAccount.css">
</head>
<body>

	<div class="container-fluid">
		<div class="row">
			
			<div class="col-md-10 mx-auto">
	        	<div class = "row">
	        		<h1>Chỉnh sửa tài khoản</h1>
	        	</div>
	        <form action="<%=request.getContextPath()%>/usercontroller/editacc" method="post">
			  <label for="username">Username:</label>
			  <input type="text" readonly id="username" name="username" required value="${account.getuser()}"><br><br>
			  
			  <label for="password">Password:</label>
			  <input type="password" id="password" name="password" value="${account.getpassword()}" required><br><br>
			  
			  <label for="role">Role:</label>
			  <select id="role" name="role" required>
			    <option value="ROLL_ADMIN">Admin</option>
				<option value="ROLE_MGT_STAFF">Quản lí</option>
				<option value="ROLE_LECT">Chủ nhiệm</option>
			  </select><br><br>
			  
			  <div class="col offset-md-5 mb-3 space">
		            <button type="submit" class="btn btn-primary">Lưu</button>
		            <a href="<%=request.getContextPath()%>/usercontroller/listacc" class="btn btn-secondary">Hủy</a>
		    	</div>
			  </form>
        </div>
     </div>
  </div>
</body>
</html>