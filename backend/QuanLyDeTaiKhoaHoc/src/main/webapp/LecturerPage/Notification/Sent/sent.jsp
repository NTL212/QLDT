<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đề tài nghiên cứu khoa học</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/ProjectManager/Home/Home.css">
<jsp:include page="../../../components/layout/header/header.jsp"></jsp:include>
</head>
<body>

	<div class="container-fluid manager-home">
		<div class="row">
			<div class="col-md-2">
				<jsp:include
					page="../../../components/layout/LecturerSidebar/LecturerSidebar.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<div class="row text-center">
					<h1>${title}</h1>
				</div>
				<div class="row">
					<div class="table-container">
						<table>
							<thead>
								<tr>
									<c:choose>
									    <c:when test="${recieve != null}">
									        <th width="300px" scope="col">Người gửi</th>
									    </c:when>
									    <c:otherwise>
									        <th width="300px" scope="col">Người nhận</th>
									    </c:otherwise>
									</c:choose>
									<th width=500px scope="col">Tiêu đề</th>
									<th width=300px scope="col">Thời gian</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${listNotification != null}">
									<c:forEach var="noti" items="${listNotification}">
										<tr onclick=" toggleHideBox(${noti.getId()}) ">
											<c:choose>
											    <c:when test="${recieve != null}">
											        <td><c:out value="${noti.getSender().getUsername()}" /></td>
											    </c:when>
											    <c:otherwise>
											        <td><c:out value="${noti.getReceiver().getUsername()}" /></td>
											    </c:otherwise>
											</c:choose>
											<td><c:out value="${noti.getTitle()}" /></td>
											<td><c:out value="${noti.getSentTime()}" /></td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row form-label">
					<c:forEach var="noti" items="${listNotification}">
							<div id="${noti.getId()}" class="frame-content">
								<h3>Nội dung thông báo</h3>
								<div class="info-content">
									<p>${noti.getContent()}</p>
								</div>
							</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
  function toggleHideBox(notiId) {
	  	var remove = document.querySelectorAll(".frame-content");
	  	console.log(remove)
	  	for (var i = 0; i < remove.length; i++) {
	  		remove[i].style.display = "none";
	  	}
		var box = document.getElementById(notiId);
		box.style.display = "block";
	}
  </script>
</html>