<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<title>Đề tài nghiên cứu khoa học</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
	<link rel="stylesheet" href="UserManggement.css">
	<jsp:include page="../../components/layout/header/header.jsp"></jsp:include>
</head>
<body>
	
	<div class = "container-fluid">
		<div class = "row">
			<div class = "col-md-2">
				<jsp:include page="../../components/layout/AdminSidebar/AdminSidebar.jsp"></jsp:include>
			</div>
			<div class = "col-md-9">
	        	<div class = "row space-little">
		        	<div class = "table-container">
		        			<table>
			        			<thead>
			        				<tr>
							            <th width = 100px class = "text-center">Username</th>
							            <th width = 200px class = "text-center">Email</th>
							            <th width = 200px class = "text-center">Họ tên</th>
							            <th width = 150px class = "text-center"></th>
							        </tr>
			        			</thead>
			        			<tbody>
			        				<tr>
			        					<c:if test="${listManager != null}">
				        				<c:forEach var="account" items="${listManager}">
										    <tr>
									            <td><c:out value="${account.getUsername()}" /></td>
												<td><c:out value="${account.getPassword()}" /></td>
												<td><c:out value="${account.getRole()}" /></td>
												<td>
													<a href="<%=request.getContextPath()%>/admincontroller?id=<c:out value='${account.getUsername()}'/>&action=editacc" class="btn btn-warning"><i class="fas fa-edit"></i> </a>
  													<a href="<%=request.getContextPath()%>/admincontroller?id=<c:out value='${account.getUsername()}'/>&action=deleteacc" class="btn btn-info"><i class="fas fa-trash"></i> </a>
  												</td>
									        </tr>
										</c:forEach>
									</c:if>
			        				</tr>
			        				
			        			</tbody>
		        			</table>
		        		</div>
	        	</div>
	        	<div class = "row">
	        		<div class = "col">
		        		<a href="<%=request.getContextPath()%>/Admin/AddManager/AddManager.jsp" class="btn btn-primary"><i class="fas fa-plus"></i> Thêm</a>
	        		</div>
	        	</div>
	        </div>
		</div>
	</div>
</body>
</html>