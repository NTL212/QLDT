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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/ProjectManager/Subject/Subject.css">
	<jsp:include page="../../components/layout/Managerheader/Managerheader.jsp"></jsp:include>
	<jsp:include page="../../components/layout/DiaglogAddTopic/DiaglogAddTopic.jsp"></jsp:include>
</head>
<body>
	
	<div class = "container-fluid">
		<div class = "row">
			<div class = "col-md-2">
				<jsp:include page="../../components/layout/ManagerSidebar/ManagerSidebar.jsp"></jsp:include>
			</div>
			<div class = "col-md-9">
				<div class = "row">
					<div class = "col-md-2 offset-md-8">
						<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
					</div>
					<div class = "col-md-2">
						<button class="btn btn-outline-success" type="button">
			                <i class="fas fa-search"></i>
			            </button>
					</div>
	        	</div>
	        	<div class = "row">
	        		<label for="dsdt" class  = "form-label space-little">Danh sách chủ đề</label>
	        	</div>
	        	<div class = "row">
		        	<div class = "table-container">
		        			<table>
			        			<thead>
			        				<tr>
							            <th width = 100px  class = "text-center">STT</th>
							            <th width = 700px  class = "text-center">Chủ đề</th>
							            <th width = 200px  class = "text-center">Số lượng đề tài</th>
							            <th width = 200px  class = "text-center">Đang mở đăng ký</th>
							        </tr>
			        			</thead>
			        			<tbody>
			        					<c:forEach var="topic" items="${listTopic}">
										    <tr>
									            <td><c:out value="${topic.getTopicCode()}" /></td>
												<td><c:out value="${topic.getName()}" /></td>
												<td><c:out value="${topic.getAmountProject()}" /></td>
												<td><c:out value="${topic.getAmountEnableProject()}" /></td>
									        </tr>
										</c:forEach>
			        			</tbody>
		        			</table>
		        		</div>
	        	</div>
	        	<div class = "row">
	        		<div class = "col">
		        		<label class="form-label">Tổng: <c:out value="${Total}" /></label>
	        		</div>
	        		<div class = "col offset-md-8">
		        		<button class = "btn btn-primary" onclick="openDialog()">
		        			Thêm
		        		</button>
		
	        		</div>
	        	</div>
	        </div>
		</div>
	</div>
	
	<script>
	    function openDialog() {
	        document.getElementById('overlay').style.display = 'flex';
	    }
	</script>
</body>
</html>