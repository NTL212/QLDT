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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/ProjectManager/DetailProject/DetailProject.css">
</head>
<body>
<div class="container mt-5">
	<div class="row text-center">
		 <h1>Sửa đề tài</h1>
	</div>
    <form>
    	<div class = "row">
	    	<div class = "col-md-8 frame-content">
	    		<div class="row text-center">
					 <h2>Thông tin chi đề tài</h2>
				</div>
		    	<div class="row">
		            <div class="col-md-6 mb-3">
		                <label for="projectCode" class="form-label">Mã đề tài:</label>
		            	<input type="text" class="form-control border-2" id="projectCode" name="projectCode" value="${project.getProjectCode()}" required readonly>
		            </div>
		            <div class="col-md-6 mb-3">
		            	<label for="projectTopic" class="form-label">Chủ đề:</label>
		                <select class="form-select border-2" id="projectTopic" name="projectTopic"  required>
					        <c:forEach var="topic" items="${listTopic}">
						        <option value="${topic.getTopicCode()}">${topic.getName()}</option>
						    </c:forEach>
					    </select>
		            </div>
		        </div>
		        <div class="mb-3">
		            <label for="projectTitle" class="form-label">Tên đề tài:</label>
		            <input type="text" class="form-control border-2" id="projectTitle" name="projectTitle" value = "${project.getName()}" required readonly>
		        </div>
		        <div class="row">
		            <div class="col-md-4 mb-3">
		                <label for="registrationStartDate" class="form-label">Ngày mở đăng kí:</label>
		                <input type="date" class="form-control border-2" id="registrationStartDate" name="registrationStartDate" value = "${project.getOpenRegDate()}" required readonly>
		            </div>
		            <div class="col-md-4 mb-3">
		                <label for="startDate" class="form-label">Ngày bắt đầu:</label>
		                <input type="date" class="form-control border-2" id="startDate" value = "${project.getStartDate()}" name="startDate" required readonly>
		            </div>
		        </div>
		        <div class="row">
		        	<div class="col-md-4 mb-3">
		                <label for="registrationEndDate" class="form-label">Ngày kết thúc đăng kí:</label>
		                <input type="date" class="form-control border-2" id="registrationEndDate" name="registrationEndDate" value = "${project.getCloseRegDate()}" required readonly>
		            </div>
		            <div class="col-md-4 mb-3">
		                <label for="endDate" class="form-label">Ngày hết hạn:</label>
		                <input type="date" class="form-control border-2" id="endDate" name="endDate" value = "${project.getEndDate()}" required readonly>
		            </div>
		        </div>
		        <div class="row">
		            <div class="col-md-3 mb-3">
		                <label for="expectedBudget" class="form-label">Kinh phí dự kiến:</label>
		                <input type="text" class="form-control border-2" id="expectedBudget" name="expectedBudget" value = "${project.getEstBudget()}" required readonly>
		            </div>
		            <div class="col-md-3 mb-3">
			            <label for="teamMembers" class="form-label">Số lượng thành viên:</label>
			            <input type="number" class="form-control border-2" id="teamMembers" name="teamMembers" min="1" max="10" value = "${project.getMaxMember()}" required readonly>
		            </div>
		        </div>
		        
		        <div class="col mb-3">
			            <label for="attachmentFile" class="form-label">File đính kèm:</label>
			            <input type="file" class="form-control border-2" id="attachmentFile" name="attachmentFile" readonly>
			        </div>
		        
		        <div class="mb-3">
		            <label for="projectDescription" class="form-label">Mô tả:</label>
		            <textarea class="form-control border-2" id="projectDescription" name="projectDescription" rows="4" value = "<c:out value='${project.getDescription()}' />" readonly></textarea>
		        </div>
	    	</div>
	    	
	    	<div class = "col-md-4">
	    		<div class = "row frame-content">
		    		<div class="row text-center">
						 <h2>Kết quả đánh giá</h2>
					</div>
		    		<div class="col-md-4 mb-3">
			                <label for="evaluationDate" class="form-label">Ngày nghiệm thu:</label>
			                <input type="date" class="form-control border-2" id="evaluationDate" name="evaluationDate" value = "${project.getAcceptanceDate()}" readonly>
			            </div>
			        <div class="mb-3">
			            <label for="result" class="form-label">Kết quả:</label>
			            <input type="text" class="form-control border-2" id="result" name="result" value = "${project.getResult()}" readonly>
			        </div>
			
			        <div class="mb-3">
			            <label for="evaluationBoard" class="form-label">Hội đồng nghiệm thu:</label>
			            <input type="text" class="form-control border-2" id="evaluationBoard" name="evaluationBoard" value = "${project.getaCouncil()}" readonly>
			        </div>
			
			        <div class="mb-3">
			            <label for="comments" class="form-label">Nhận xét:</label>
			            <textarea class="form-control border-2" id="comments" name="comments" rows="4" readonly value = "duy"></textarea>
			        </div>
		        </div>
		        <div class="col offset-md-5 mb-3 space">
		            <a href="<%=request.getContextPath()%>/ProjectController/listproject" class="btn btn-secondary" >Hủy</a>
		    	</div>
	        </div>
	        
  		</div>
    </form>
</div>
</body>
</html>