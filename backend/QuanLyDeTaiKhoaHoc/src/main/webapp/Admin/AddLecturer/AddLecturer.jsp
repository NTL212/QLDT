<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>Thêm tài khoản</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" type = "text/css" href="<%=request.getContextPath()%>/Admin/AddLecturer/AddLecturer.css">
    <script >
    document.getElementById('insertlecturer').addEventListener('submit', function(event) {
        event.preventDefault(); 

        var magv = document.getElementById('lecturerCode').value;
        var pass = document.getElementById('password').value;
        var role = document.getElementById('role').value;
        var falculityCode = document.getElementById('falculityCode').value;
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
        <form id="insertlecturer" action="<%=request.getContextPath()%>/admincontroller/insertlecturer" method="POST">
            <div class="form-group">
                <label for="magv">Mã giảng viên:</label>
                <input type="text" class="form-control" id="magv" name="magv" required>
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu:</label>
                <input type="text" class="form-control" id="pass" name="password" required>
            </div>
            <div class="form-group">
                <label for="falculityCode">Mã khoa:</label>
                <input type="text" class="form-control" id="falculityCode" name="falculityCode" required>
            </div>
            <div class="form-group">
                <label for="name">Họ và tên:</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="birthday">Ngày sinh:</label>
                <input type="date" class="form-control" id="birthday" name="birthday" required>
            </div>
            <div class="form-group">
                <label for="idNum">Số CMND:</label>
                <input type="text" class="form-control" id="idNum" name="idNum" required>
            </div>
            <div class="form-group">
                <label for="phoneNum">Số điện thoại:</label>
                <input type="text" class="form-control" id="phoneNum" name="phoneNum" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="sex">Giới tính:</label>
                <select class="form-control" id="sex" name="sex" required>
                    <option value="Nam">Nam</option>
                    <option value="Nữ">Nữ</option>
                </select>
            </div>
            <div class="form-group">
                <label for="address">Địa chỉ:</label>
                <input type="text" class="form-control" id="address" name="address" required>
            </div>
            <button type="submit" class="btn btn-primary">Thêm</button>
        </form>
    </div>
</body>
</html>