<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<title>Đề tài của tôi</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/LecturerPage/MyProject/List/ListProject.css">
</head>
<body>
	<div class="col-md-15">
		<jsp:include page="/components/layout/header/header.jsp"></jsp:include>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<jsp:include
					page="/components/layout/LecturerSidebar/LecturerSidebar.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<div class="row">
					<h1>Danh sách đề tài của tôi</h1>
				</div>
				<div class="row">
					<div class="table-container">
						<table>
							<thead>
								<tr>
									<th width=100px>Mã đề tài</th>
									<th width=800px>Tên đề tài</th>
									<th width=300px>Tình trạng</th>
									<th width=50px></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<c:if test="${myProject != null}">
										<c:forEach var="project" items="${myProject}">
											<tr>
												<td><c:out value="${project.getProjectCode()}" /></td>
												<td><c:out value="${project.getName()}" /></td>
												<td><c:out
														value="${project.getProjectStatusOfLecturer()}" /></td>
												<td><a href="<%=request.getContextPath()%>/lecturer-project/my-project/detail?id=<c:out value='${project.getProjectCode()}'/>" class="btn btn-info"><i
														class="fas fa-eye"></i> </a></td>
											</tr>
										</c:forEach>
									</c:if>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>