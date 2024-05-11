<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách đề tài</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/LecturerPage/RegisterProject/List/List.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
    <script src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
    <script>
        window.onload = function() {
            var errMsg = "<%= request.getAttribute("errMsg") %>";
            var successMsg = "<%= request.getAttribute("successMsg") %>";
            if (errMsg && errMsg !== "null") {
                Toastify({
                    text: errMsg,
                    duration: 3000, 
                    close: true,
                    gravity: "top",
                    style: {
                    	background: "red",
                    	color: "white",
                      }
                }).showToast();
            }
            if (successMsg && successMsg !== "null") {
                Toastify({
                    text: successMsg,
                    duration: 3000, 
                    close: true,
                    gravity: "top",
                    style: {
                    	background: "green",
                    	color: "white",
                      }
                }).showToast();
            }
        };
    </script>
</head>
<body>
	<div class="col-md-15">
		<jsp:include page="/components/layout/header/header.jsp"></jsp:include>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<jsp:include page="/components/layout/LecturerSidebar/LecturerSidebar.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
	        	<div class = "row">
	        		<h1>Danh sách đề tài</h1>
	        	</div>
	        	<div class = "row">
		        	<div class = "table-container">
		        			<table>
			        			<thead>
		        				<tr>	
						            <th width = 150px>Mã đề tài</th>
						            <th width = 900px>Tên đề tài</th>
						            <th width = 100px>Chủ đề</th>
						            <th width = 300px>Tình trạng</th>
						            <th width = 150px> </th>
						        </tr>
		        			</thead>
			        			<tbody>
				                    <tr>         	
				                        <c:if test="${listProject != null}">
				        				<c:forEach var="project" items="${listProject}">
										    <tr>
									            <td><c:out value="${project.getProjectCode()}" /></td>
												<td><c:out value="${project.getName()}" /></td>
												<td><c:out value="${project.getTopic().getName()}" /></td>
												<td><c:out value="${project.getTopic().getName()}" /></td>
												<td>
													<a href="<%=request.getContextPath()%>/lecturer-project/detail?id=<c:out value='${project.getProjectCode()}'/>" class="btn btn-info"><i class="fas fa-eye"></i> </a>
  												</td>
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








