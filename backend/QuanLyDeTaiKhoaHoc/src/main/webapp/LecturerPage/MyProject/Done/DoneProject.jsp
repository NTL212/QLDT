<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Chi tiết đề tài đã làm</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="DoneProject.css"
			href="<%=request.getContextPath()%>/LectuerPage/MyProject/DoneProject.css">
</head>
<body>
	<div class="col-md-15">
		<jsp:include page="../../../components/layout/header/header.jsp"></jsp:include>
	</div>
  <div class="container-fluid">
  	<div class = "row">
        <h1> Chi tiết đề tài đã làm</h1>
    </div>
    <div class="row">
      <div class="col">
        <label for="idsub" class="form-label">Mã đề tài:</label>
        <input type="text" readonly class="form-control" id="idsub" value="DT001">
      </div>
      <div class="col">
        <label for="sub" class="form-label">Tên đề tài:</label>
        <input type="text" readonly class="form-control" id="sub" value="IOT Trong lĩnh vực nông nghiệp">
      </div>
    </div>
    
    <div class="row">
      <div class="col">
        <label for="poster" class="form-label">Người đăng:</label>
        <input type="text" readonly class="form-control" id="poster" value="Nguyễn Văn A">
      </div>
      <div class="col">
        <label for="topic" class="form-label">Chủ đề:</label>
        <input type="text" readonly class="form-control" id="topic" value="IOT">
      </div>
      <br><br>
      
      <div class="row">
      <div class="col">
        <label for="datepost" class="form-label">Ngày đăng:</label>
        <input type="date" readonly class="form-control" id="datepost">
      </div>
      <div class="col">
        <label for="registeropendate" class="form-label">Ngày mở đăng kí:</label>
        <input type="date" readonly class="form-control" id="registerdate">
      </div>
      <div class="col">
        <label for="registerclosedate" class="form-label">Ngày kết thúc đăng kí:</label>
        <input type="date" readonly class="form-control" id="registerclosedate">
      </div>
      
      <div class="row">
      <div class="col">
        <label for="startdate" class="form-label">Ngày bắt đầu làm:</label>
        <input type="date" readonly class="form-control" id="startdate">
      </div>
      <div class="col">
        <label for="deadline" class="form-label">Ngày hết hạn:</label>
        <input type="date" readonly class="form-control" id="deadline">
      </div>
      <div class="col">
        <label for="submitdate" class="form-label">Ngày nghiệm thu:</label>
        <input type="date" readonly class="form-control" id="submitdate">
      </div>
      
      <div class="row">
      	<label for="describe" class="form-lable">Mô tả:</label>
      	<textarea rows="4" cols="50" id="describe" class="form-control" readonly value="Sử dụng công nghệ trong nông nghiệp"></textarea><br><br>
      </div>
      
      <div class="row">
      	<div class="col">
      	 <label for="filedinhkem" class ="form-lable" >File đính kèm: </label>
      	 <label><u><i>thongbao.zip</i></u></label>
      	 <br><br>
      	 </div>
      	<div class ="col">
      	 <lable for="filebaocao" class="form-label">File báo cáo: </lable>
      	 <label><u><i>baocao.zip</i></u></label>
      	 </div>
      </div>

      <div class="row">
      	<div class="col">
      	 <label for="maxofmem" class="form-lable">Số thành viên tối đa:</label>
      	 <input type="text" readonly class="form-control-sm" id="maxofmem" value="5">
      	 </div>
      	<div class ="col">
      	 <lable for="result" class="form-label">Kết quả</lable>
      	 <input type="text" readonly class="form-control-sm" id="result" value="Tốt">
      	 <br><br>
      	 </div>
      </div>
      
      <div class="row">
      	<div class="col">
      	 <label for="expectcost" class="form-lable">Kinh phí:</label>
      	 <input type="text" readonly class="form-control-sm" id="expectcost" value="5000000">
      	 </div>
      	 <div class="col">
      	<lable for="acceptancecommittee" class="form-label">Hội đồng nghiệm thu</lable>
      	 <input type="text" readonly class="form-control-sm" id="acceptancecommittee" value="ABC">
      	 <br><br>
      	 </div>
      </div>
      
      <div class="row">
      	<div class="col">
      	 <label for="member" class="form-lable">Thành viên tham gia:</label>
      	 <div class = "table-container">
	        			<table>
		        			<thead>
		        				<tr>	
						            <th width = 100px>MSSV</th>
						            <th width = 150px>Tên </th>
						            <th width = 100px>Lớp</th>
						            <th width = 250px>Khoa</th>
						            <th width = 100px>Ngày tham gia</th>
						        </tr>
		        			</thead>
		        			<tbody>
				                    <tr>         	
				                        <td>21110881</td>
				                        <td>Nguyễn Văn A</td>
				                        <td>211103B</td>
				                        <td>Công nghệ thông tin</td>
				                        <td>12/05/2023</td>
				                    </tr>
				                    <tr>
				                        <td>21110882</td>
				                        <td>Nguyễn Văn B</td>
				                        <td>211103C</td>
				                        <td>Công nghệ thông tin</td>
				                        <td>12/05/2023</td>
				                    </tr>
			        			</tbody>
	        			</table>
	      </div>
      	 </div>
      	<div class ="col">
      	 <lable for="cmt" class="form-label">Nhận xét: </lable>
      	 <textarea rows="4" cols="50" id="cmt" class="form-control" readonly value="Hoàn thành tốt"></textarea><br><br>
      	 <br><br>
      	 </div>
      </div>	
      <div class="row">
      <div class="col-md-12">
        <div class="fixed-bottom text-end">
          <button class="btn btn-secondary">Trở về</button>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
