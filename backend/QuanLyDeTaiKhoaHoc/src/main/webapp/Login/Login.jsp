<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Models.Account"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
    <script src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
    <script>
        window.onload = function() {
            var errMsg = "<%= request.getAttribute("errMsg") %>";
            if (errMsg && errMsg !== "null") {
                Toastify({
                    text: errMsg,
                    duration: 3000, // 3 seconds
                    close: true,
                    gravity: "top", // "top" or "bottom"
                    style: {
                    	background: "red",
                    	color: "white",
                      }
                }).showToast();
            }
        };
    </script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Login/Login.css">
</head>
<body>
	<div class="login-page">	
		<div class="header-title">TRANG QUẢN LÝ, ĐĂNG KÝ ĐỀ TÀI KHOA HỌC</div>
		<img class="login-logo" src="<%=request.getContextPath()%>/assets/images/ute_logo.png">
		<form action="<%=request.getContextPath()%>/login" method="post" class="login-form">
			<h1 class="login-form-title">Đăng nhập</h1>
			<input type="text" id="username" name="username"
				placeholder="Tên đăng nhập"/>
			<input type="password" id="password"
				name="password" placeholder="Mật khẩu"/>
			<button>Đăng nhập</button>
		</form>
	</div>
</body>
</html>