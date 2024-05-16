<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Đề tài nghiên cứu khoa học</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
	<link rel="stylesheet" type = "text/css" href="<%=request.getContextPath()%>/ProjectManager/ProjectManagement/ProjectManagement.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
    <script src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
	<script>
        window.onload = function() {
            var fail = "<%= request.getAttribute("fail") %>";
            var success = "<%= request.getAttribute("success") %>";
            if (fail && fail !== "null") {
                Toastify({
                    text: fail,
                    duration: 3000, // 3 seconds
                    close: true,
                    gravity: "top", // "top" or "bottom"
                    style: {
                    	background: "red",
                    	color: "white",
                      }
                }).showToast();
            }
            if (success && success !== "null") {
                Toastify({
                    text: success,
                    duration: 3000, // 3 seconds
                    close: true,
                    gravity: "top", // "top" or "bottom"
                    style: {
                    	background: "green",
                    	color: "white",
                      }
                }).showToast();
            }
        };
    </script>
    <jsp:include page="../../components/layout/Managerheader/Managerheader.jsp"></jsp:include>
</head>
<body>
	
	<div class = "container-fluid">
		<div class = "row">
			<div class = "col-md-2">
				<jsp:include page="../../components/layout/ManagerSidebar/ManagerSidebar.jsp"></jsp:include>
			</div>
			<div class = "col-md-9">
				<div class = "row">
	        		<div class = "col-md-1 text-end">
		        		<label for="subject" class  = "form-label">Chủ đề:</label>
					</div>
					<div class = "col-md-2">
			    		<select id="subject" name="subject" class="form-select">
			    			<c:forEach var="topic" items="${listTopic}">
						        <option value="${topic.getTopicCode()}">${topic.getName()}</option>
						    </c:forEach>
						</select>
					</div>
					<div class = "col-md-2 text-end">
						<label for="status" class  = "form-label">Tình trạng:</label>
					</div>
					<div class = "col-md-2">
			    		<select id="status" name="status" class="form-select">
						</select>
					</div>
					<div class = "col-md-2 offset-md-1">
						<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
					</div>
					<div class = "col-md-2">
						<button class="btn btn-outline-success" type="button">
			                <i class="fas fa-search"></i>
			            </button>
					</div>
	        	</div>
	        	<div class = "row space-little">
		        	<div class = "table-container">
		        			<table>
			        			<thead>
			        				<tr>
							            <th width = 100px class = "text-center">Mã đề tài</th>
							            <th width = 700px class = "text-center">Tên đề tài</th>
							            <th width = 100px class = "text-center">Chủ đề</th>
							            <th width = 100px class = "text-center">Tình trạng</th>
							            <th width = 150px class = "text-center"></th>
							        </tr>
			        			</thead>
			        			<tbody>
			        				<c:if test="${listProject != null}">
				        				<c:forEach var="project" items="${listProject}">
										    <tr>
										    	<td><c:out value="${project.getProjectCode()}" /></td>
												<td><c:out value="${project.getName()}" /></td>
												<td><c:out value="${project.getTopic().getName()}" /></td>
												<td><c:out value="${project.getStatus()}" /></td>
												<td>
													<a href="<%=request.getContextPath()%>/ProjectController/editproject?id=<c:out value='${project.getProjectCode()}'/>" class="btn btn-warning"><i class="fas fa-edit"></i> </a>
  													<a href="<%=request.getContextPath()%>/ProjectController/showdetailform?id=<c:out value='${project.getProjectCode()}'/>&action=" class="btn btn-info"><i class="fas fa-search"></i> </a>
  												</td>
									        </tr>
										</c:forEach>
									</c:if>
			        			</tbody>
		        			</table>
		        		</div>
	        	</div>
	        	<div class = "row">
	        		<div class = "col-lg-8">
		        		<label for="fileInput" class="btn btn-primary">
						    <i class="fas fa-file-excel"></i> Import từ Excel
						    <input type="file" id="fileInput" style="display: none;" accept=".xls, .xlsx">
						</label>
	        		</div>
	        		<div class = "col">
		        		<a href="<%=request.getContextPath()%>/ProjectController/shownewform" class="btn btn-primary"><i class="fas fa-plus"></i> Thêm</a>
	        		</div>
	        	</div>
	        </div>
		</div>
	</div>
</body>
</html>