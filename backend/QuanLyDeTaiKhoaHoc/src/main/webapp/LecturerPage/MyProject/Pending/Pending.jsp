<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Models.Project"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết đề tài đang làm</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/LecturerPage/MyProject/Pending/Pending.css">
</head>
<body>
	<div class="col-md-15">
		<jsp:include page="/components/layout/header/header.jsp"></jsp:include>
	</div>
	<form class="container-fluid fw-bold" action="<%=request.getContextPath()%>/lecturer-project/submit" method="POST" enctype="multipart/form-data" >
		<div class="row">
			<h1>Chi tiết đề tài đang làm</h1>
		</div>
		<div class="row">
			<div class="col">
				<label for="idsub" class="form-label">Mã đề tài:</label> <input
					type="text" readonly class="form-control w-50" id="projCode"
					name="projCode" value="${project.getProjectCode()}">
			</div>
			<div class="col">
				<label for="name" class="form-label">Tên đề tài:</label> <input
					type="text" readonly class="form-control w-75" id="name"
					name="name" value="${project.getName()}">
			</div>
		</div>
		<div class="row">
			<div class="col">
				<label for="topic" class="form-label">Chủ đề:</label> <input
					type="text" readonly class="form-control w-25" id="topic"
					name="topic" value="${project.getTopic().getName()}">
			</div>
			<br> <br>
			<div class="row">
				<div class="col">
					<label for="startDate" class="form-label">Ngày bắt đầu làm:</label>
					<input type="date" readonly class="form-control" id="startDate"
						name="startDate" value="${project.getStartDate()}">
				</div>
				<div class="col">
					<label for="deadline" class="form-label">Ngày hết hạn:</label> <input
						type="date" readonly class="form-control" id="endDate"
						name="endDate" value="${project.getEndDate()}">
				</div>
				<div class="col">
					<label for="submitdate" class="form-label">Ngày nghiệm thu:</label>
					<input type="date" readonly class="form-control" id="submitDate"
						name="submitDate" value="${project.getAcceptanceDate()}">
				</div>
			</div>
			<div class="row">
				<label for="describe" class="form-lable">Mô tả:</label>
				<textarea rows="4" cols="50" id="describe" class="form-control ms-2"
					readonly>${project.getDescription()}</textarea>
				<br>
			</div>
			<div class="row">
				<div class="col">
					<label for="filedinhkem" class="form-lable">File đính kèm:
					</label> <a href="/path/to/file.pdf" download ><i>thongbao.zip</i></a> <br>
					<br>
				</div>
				<div class="col">
					<label for="filebaocao" class="form-label">File báo cáo</label>
					<input type="file" id="fileInput" name="fileInput"> <br> <br>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="maxofmem" class="form-lable">Số thành viên tối
						đa:</label> <input type="text" readonly class="form-control-sm"
						id="maxofmem" value="${project.getMaxMember()}">
				</div>
			</div>
			<div class="row mt-3">
				<div class="col">
					<label for="expectcost" class="form-lable">Kinh phí:</label> <input
						type="text" readonly class="form-control-sm" id="expectcost"
						value="${project.getEstBudget()}">
				</div>
			</div>
			<div class="row">
				<div class="col mt-2">
					<label for="member" class="form-lable">Thành viên tham gia:</label>
					<div class="table-container">
						<table>
							<thead>
								<tr>
									<th width=100px>MSSV</th>
									<th width=150px>Tên</th>
									<th width=100px>Lớp</th>
									<th width=250px>Khoa</th>
									<th width=100px>Ngày tham gia</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>21110881</td>
									<td>Nguyễn Văn A</td>
									<td>211103B</td>
									<td>Công nghệ thông tin</td>
									<td>12/05/2023</td>
								</tr>
								<tr>
									<td>21110882</td>
									<td>Nguyễn Văn B</td>
									<td>211103C</td>
									<td>Công nghệ thông tin</td>
									<td>12/05/2023</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-6">
			<!-- 
				<label for="fileInput" class="btn btn-primary"> <i
					class="fas fa-file-excel"></i> Import từ Excel <input type="file"
					id="fileInput" style="display: none;" accept=".xls, .xlsx">
				</label> -->
			<button type="button" class="btn btn-primary">Quản lý thành viên</button>
			<button type="submit" class="btn btn-primary">Nộp báo cáo</button>
			</div>
			<div class="col-6">
				<a class="btn btn-primary"
					href="<%=request.getContextPath()%>/lecturer-project/myproj">
					Trở về </a>
			</div>
		</div>
	</form>
</body>
</html>
