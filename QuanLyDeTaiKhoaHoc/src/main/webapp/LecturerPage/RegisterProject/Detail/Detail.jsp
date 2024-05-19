<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Models.Project"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết đề tài</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/LecturerPage/RegisterProject/Detail/Detail.css">
<link rel="stylesheet" href="Detail.css">
</head>
<body>
	<div class="col-md-15">
		<jsp:include page="/components/layout/header/header.jsp"></jsp:include>
	</div>
	<div class="container-fluid">
		<div class="row">
			<h1>Chi tiết đề tài</h1>
		</div>

		<form
			action="<%=request.getContextPath()%>/lecturer-project/register-project"
			method="post">
			<div class="row">
				<div class="col">
					<label for="id" class="form-label">Mã đề tài:</label> <input
						type="text" name="projCode" id="projCode" value="${project.getProjectCode()}" />
					<br>
				</div>
				<div class="col">
					<label for="namepro" class="form-label">Tên đề tài:</label> <input
						type="text" readonly class="form-control" id="namepro"
						value="${project.getName()}" /> <br>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<label for="subject" class="form-label">Chủ đề:</label> <input
						type="text" readonly class="form-control" id="subject"
						value="${project.getTopic().getName()}" /> <br>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<label for="datepost" class="form-label">Ngày đăng:</label> <input
						type="date" readonly class="form-control" id="datepost"
						value="${project.getCreateDate()}" /> <br>
				</div>
				<div class="col">
					<label for="registeropendate" class="form-label">Ngày mở
						đăng kí:</label> <input type="date" readonly class="form-control"
						id="registerdate" value="${project.getOpenRegDate()}" /> <br>
				</div>
				<div class="col">
					<label for="registerclosedate" class="form-label">Ngày kết
						thúc đăng kí:</label> <input type="date" readonly class="form-control"
						id="registerclosedate" value="${project.getCloseRegDate()}" /> <br>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<label for="startday" class="form-label">Ngày bắt đầu làm:</label>
					<input type="date" readonly class="form-control" id="startday"
						value="${project.getStartDate()}" /> <br>
				</div>
				<div class="col">
					<label for="deadline" class="form-label">Ngày hết hạn:</label> <input
						type="date" readonly class="form-control" id="deadline"
						value="${project.getEndDate()}" /> <br>
				</div>
				<div class="col">
					<label for="submitdate" class="form-label">Ngày nghiệm thu:</label>
					<input type="date" readonly class="form-control" id="submitdate"
						value="${project.getAcceptanceDate()}" /> <br>
				</div>
			</div>

			<div class="row">
				<label for="describe" class="form-lable">Mô tả:</label>
				<textarea rows="4" readonly cols="50" id="describe"
					class="form-control">${project.getDescription()}</textarea>
				<br> <br>
			</div>

			<div class="row">
				<div class="col">
					<br> <br> <label for="expectcost" class="form-lable">Kinh
						phí dự kiến:</label> <input type="text" readonly class="form-control-sm"
						id="expectcost" value="${project.getEstBudget()}" />
				</div>
				<div class="col">
					<br> <br> <label for="maxofmem" class="form-lable">Số
						thành viên tối đa:</label> <input type="text" readonly
						class="form-control-sm" id="maxofmem"
						value="${project.getMaxMember()}" />
				</div>
			</div>
	
			<div class="row">
				<div class="col-md-12">
					<div class="fixed-bottom text-end">
						<button class="btn btn-primary">Đăng kí</button>
						<a class="btn btn-primary"
							href="<%=request.getContextPath()%>/lecturer-project/list">
							Trở về </a>
					</div>
				</div>
			</div>
		</form>
	</div>
	</div>
	</div>
</body>
</html>