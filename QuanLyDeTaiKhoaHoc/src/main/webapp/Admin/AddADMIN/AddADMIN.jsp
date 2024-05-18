<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Thêm ADMIN</title>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	<link rel="stylesheet" type = "text/css" href="<%=request.getContextPath()%>/Admin/AddADMIN/AddADMIN.css">
	<script >
    document.getElementById('insertadmin').addEventListener('submit', function(event) {
        event.preventDefault(); 

        var magv = document.getElementById('adCode').value;
        var pass = document.getElementById('password').value;
        var role = document.getElementById('role').value;
        var name = document.getElementById('name').value;
        var birthday = document.getElementById('birthday').value;
        var idNum = document.getElementById('idNum').value;
        var phoneNum = document.getElementById('phoneNum').value;
        var email = document.getElementById('email').value;
        var sex = document.getElementById('sex').value;
        var address = document.getElementById('address').value;
    });
    </script>
</head>
<body>
	<div class="container">
		<h2>Thêm tài khoản</h2>
		<form id="insertadmin" action="<%=request.getContextPath()%>/admincontroller/insertadmin" method="POST">
			<div class="form-group">
				<label for="adCode">Mã Admin:</label>
				<input type="text" class="form-control" id="adCode" name="adCode" required>
			</div>
			
			<div class="form-group">
                <label for="password">Mật khẩu:</label>
                <input type="text" class="form-control" id="pass" name="password" required>
            </div>

			<div class="form-group">
				<label for="name">Họ tên:</label>
				<input type="text" class="form-control" id="name" name="name" required>
			</div>

			<div class="form-group">
				<label for="birthday">Ngày sinh:</label>
				<input type="date" class="form-control" id="birthday" name="birthday" required>
			</div>

			<div class="form-group">
				<label for="address">Địa chỉ:</label>
				<input type="text" class="form-control" id="address" name="address" required>
			</div>

			<div class="form-group">
				<label for="idNum">CCCD:</label>
				<input type="text" class="form-control" id="idNum" name="idNum" required>
			</div>

			<div class="form-group">
				<label for="phoneNum">SĐT:</label>
				<input type="text" class="form-control" id="phoneNum" name="phoneNum" required>
			</div>

			<div class="form-group">
				<label for="email">Email:</label>
				<input type="email" class="form-control" id="email" name="email" required>
			</div>

			<div class="form-group">
				<label for="sex">Giới tính:</label>
				<select class="form-control" id="sex" name="sex" required>
					<option value="Male">Male</option>
					<option value="Female">Female</option>
				</select>
			</div>

			<button type="submit" class="btn btn-primary">Thêm</button>
		</form>
	</div>

	
</body>
</html>