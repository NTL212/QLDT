<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/components/layout/DiaglogAddTopic/DiaglogAddTopic.css">
</head>
<body>
<div class="overlay" id="overlay">
    <div class="dialog">
    <form  action = "<%=request.getContextPath()%>/SubjectController/insertSubject" method = "post">
        <label for="nameInput">Tên:</label>
        <input class = "form-control" type="text" id="nameInput" name = "nameInput">
        <br>
        <button type = "submit" class = "btn btn-primary">Lưu</button>
        <button onclick="closeDialog()" class = "btn btn-primary">Hủy</button>
    </form>
    </div>
    
    <script>
	    function closeDialog() {
	        document.getElementById('overlay').style.display = 'none';
	    }
	</script>
</div>
</body>
</html>