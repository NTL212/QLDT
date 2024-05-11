<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/ProjectManager/Message/Message.css">
	<jsp:include page="../../components/layout/Managerheader/Managerheader.jsp"></jsp:include>
</head>
<body>
	<div class = "container-fluid">
		<div class = "row">
			<div class = "col-md-2">
				<jsp:include page="../../components/layout/ManagerSidebar/ManagerSidebar.jsp"></jsp:include>
			</div>
			<div class = "col-md-9">
				<div class = "row text-center">
		        		<h1>Gửi thông báo</h1>
		        </div>
		        <form action = "<%=request.getContextPath()%>/manager-notification/sendMessage" method = "post">
		        <div class = "row">
					<div class = "col offset-md-2">
						<div class = "row">
							<div class = "col-md-4">
				        		<label for="recieveperson" class = "lable-form">Người nhận:</label>
						        <input type="text" class="form-control" id="recieveperson" name = "recieveperson" >
							</div>
			        	</div>
			        	<div class = "row">
			        		<div class = "col-md-8">
				        		<label for="messagetitle" class = "lable-form">Tiêu đề:</label>
						        <input type="text" class="form-control" id="messagetitle" name="messagetitle">
							</div>
			        	</div>
			        	<div class="row">
				            <div class="col-md-9">
				                <div class="form-group">
				                    <label for="messagecontent" class="form-label">Nội dung:</label>
				                    <textarea class="form-control" id="messagecontent" name="messagecontent" rows = "7" placeholder="Type something..."></textarea>
				                </div>
				            </div>
				        </div>
			        	<div class = "row space">
			        		<div class = "col-md-2">
				        		<a href = "<%=request.getContextPath()%>/manager-notification/getSendMessage" class = "btn btn-primary">
				        			<i class="fas fa-history"></i>
				        		</a>
			        		</div>
			        		<div class = "col offset-md-6">
				        		<button type = "submit" class = "btn btn-primary">
				        			Gửi
				        		</button>
			        		</div>
			        	</div>
			        </div>
			     </div>
		     </form>
		     </div>
		</div>
	</div>
</body>
</html>