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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/ProjectManager/ApproveProject/ApproveProject.css">
	<jsp:include page="../../components/layout/Managerheader/Managerheader.jsp"></jsp:include>
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
	        	<div class = "row">
	        		<label for="dsdt" class  = "form-label space-little">Danh sách đề tài:</label>
	        	</div>
	        	<div class = "row">
		        	<div class = "table-container">
		        			<table>
			        			<thead>
			        				<tr>
							            <th width = 100px  class = "text-center">Mã đề tài</th>
							            <th width = 700px  class = "text-center">Tên đề tài</th>
							            <th width = 100px  class = "text-center">Chủ đề</th>
							            <th width = 100px  class = "text-center">Chủ nhiệm</th>
							            <th width = 200px  class = "text-center"></th>
							        </tr>
			        			</thead>
			        			<tbody>
			        				<c:if test="${listRe != null}">
				        				<c:forEach var="re" items="${listRe}">
										    <tr>
									            <td><c:out value="${re.getProj().getProjectCode()}" /></td>
												<td><c:out value="${re.getProj().getName()}" /></td>
												<td><c:out value="${re.getProj().getTopic().getName()}" /></td>
												<td><c:out value="${re.getLect().getName()}" /></td>
												<td>
  													<a href="<%=request.getContextPath()%>/ProjectController/approveproject?idLec=<c:out value='${re.getLect().getLecturerCode()}'/>&pro=<c:out value='${re.getProj().getProjectCode()}'/>" class="btn btn-warning"><i class="fas fa-check"></i> </a>
  													<a href="<%=request.getContextPath()%>/ProjectController/disagreeproject?idLec=<c:out value='${re.getLect().getLecturerCode()}'/>&pro=<c:out value='${re.getProj().getProjectCode()}'/>" class="btn btn-info"><i class="fas fa-times"></i></a>
  													<a href="<%=request.getContextPath()%>/ProjectController/showdetailform?id=<c:out value='${re.getProj().getProjectCode()}'/>" class="btn btn-info"><i class="fas fa-eye"></i> </a>
  												</td>
									        </tr>
										</c:forEach>
									</c:if>
			        			</tbody>
		        			</table>
		        		</div>
	        	</div>
	        </div>
		</div>
	</div>
</body>
</html>