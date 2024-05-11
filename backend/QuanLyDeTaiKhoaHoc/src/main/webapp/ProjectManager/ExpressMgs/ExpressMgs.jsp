<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
	<link rel="stylesheet" href="AdminSidebar.css">
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-9">
            <div class="row text-center">
                <h1>${type}</h1>
            </div>
            <form action="<%=request.getContextPath()%>/manager-notification/sendApproveProjectMessage" method="post">
                <div class="row">
                    <div class="col offset-md-2">
                        <div class="row">
                            <div class="col-md-4">
                                <label for="recieveperson" class="label-form">Người nhận:</label>
                                <input type="text" class="form-control" id="recieveperson" name="recieveperson" value = "${recievePerson}" readonly>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <label for="messagetitle" class="label-form">Tiêu đề:</label>
                                <input type="text" class="form-control" id="messagetitle" name="messagetitle" value = "${title}" >
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-9">
                                <div class="form-group">
                                    <label for="messagecontent" class="form-label">Nội dung:</label>
                                    <textarea class="form-control" id="messagecontent" name="messagecontent" rows="7"
                                        placeholder="Type something...">${content}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row space">
                            <div class="col offset-md-7">
                                <button type="submit" class="btn btn-primary">
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
