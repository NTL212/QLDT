<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đề xuất đề tài</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/LecturerPage/ProposeProject/ProposeProject.css">
</head>
<body>
	<div class="col-md-15">
		<jsp:include page="/components/layout/header/header.jsp"></jsp:include>
	</div>
	<div class="container-fluid fw-bold">
		<div class="row">
			<h1 class="text-center">Đề xuất đề tài</h1>
		</div>
		<form action="<%=request.getContextPath()%>/lecturer-project/propose"
			method="post" class="border border-dark propose-project">
			<div class="row pt-3">
				<div class="col ms-3">
					<label for="id" class="form-label">Mã đề tài:</label> <input
						type="text" class="form-control w-50" id="id" name="id" required>
				</div>
				<div class="col">
					<label for="name" class="form-label">Tên đề tài:</label> <input
						type="text" class="form-control w-75" id="name" name="name"
						required>
				</div>
			</div>
			<div class="col-md-4 mb-3 ms-3 mt-3">
				<label for="projectTopic" class="form-label">Chủ đề:</label> <select
					class="form-select border-2" id="projectTopic" name="projectTopic"
					required>
					<c:forEach var="topic" items="${listTopic}">
						<option value="${topic.getTopicCode()}">${topic.getName()}</option>
					</c:forEach>
				</select>
			</div>
			<div class="row">
				<div class="col ms-3">
					<label for="startDate" class="form-label">Ngày bắt đầu làm:</label>
					<input type="date" class="form-control w-50" id="startDate"
						name="startDate" required> <br>
				</div>
				<div class="col">
					<label for="endDate" class="form-label">Ngày hết hạn:</label> <input
						type="date" class="form-control w-50" id="endDate" name="endDate"
						required> <br>
				</div>

				<div class="row ms-3">
					<label for="describe" class="form-lable">Mô tả:</label>
					<textarea rows="4" cols="50" id="describe" name="describe"
						class="form-control w-75" required></textarea>
					<br> <br>
				</div>
				<div class="row">
					<div class="col ms-3">
						<br> <br> <label for="expectcost" class="form-lable">Kinh
							phí dự kiến:</label> <input type="number" class="form-control-sm"
							id="expectcost" name="expectcost" min="0" max="500000000"
							required>
					</div>
					<div class="col">
						<br> <br> <label for="maxofmem" class="form-lable">Số
							thành viên tối đa:</label> <input type="number" class="form-control-sm"
							id="maxofmem" name="maxofmem" min="1" max="6" required>
					</div>
				</div>
			</div>
			<div class="row">
				<button type="submit" class="btn btn-primary btn-propose">Đề
					xuất</button>
				<a class="btn btn-primary btn-return"
					href="<%=request.getContextPath()%>/lecturer-project/list"> Trở
					về </a>
			</div>
		</form>
	</div>
</body>
</html>