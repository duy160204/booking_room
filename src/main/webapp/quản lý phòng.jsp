<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Quản lý phòng</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
    rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">
  
  <script>
	    function toggleInput(checkboxId, inputId) {
	        const checkbox = document.getElementById(checkboxId);
	        const input = document.getElementById(inputId);
	        input.disabled = !checkbox.checked;
	    }
	</script>
	
</head>

<body>

  <%@ include file="fragments/header.jsp" %>

  <%@ include file="fragments/sidebar.jsp" %>

  <main id="main" class="main">

    <div class="pagetitle">
      <h1>Quản lý phòng</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="index.html">Trang chủ</a></li>
          <li class="breadcrumb-item">Quản lý</li>
          <li class="breadcrumb-item active">Phòng</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section">
      <div class="row">


        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Bảng các phòng</h5>
              
              <button type="button" class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#addPhongModal">Thêm
				Phòng</button>

              <!-- Table with stripped rows -->
              <table class="table table-striped">
                <thead>
                  <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Tên phòng</th>
                    <th scope="col">Ảnh</th>
                    <th scope="col">Diện tích (m<sup>2</sup>)</th>
                    <th scope="col">Số giường</th>
                    <th scope="col">Số sao</th>
                    <th scope="col">Giá tiền (₫/đêm)</th>
                    <th scope="col">Khả dụng</th>
                    <th scope="col">Ghi chú</th>
                    <th scope="col">Hành động</th>
                  </tr>
                </thead>
                <tbody>
                	<c:forEach var="row" items="${roomList}">
			            <tr>
			                <td>${row['room_id']}</td>
			                <td>${row['room_name']}</td>
			                <td>
					            <c:if test="${not empty row['room_image']}">
					                <img src="data:image/jpeg;base64,${row['room_image']}" alt="Room Image" style="width:100px;height:100px;">
					            </c:if>
					            <c:if test="${empty row['room_image']}">
					                No Image Available
					            </c:if>
					        </td>
			                <td>${row['room_size']}</td>
			                <td>${row['room_bed_count']}</td>
			                <td>${row['room_star_count']}</td>
			                <td>${row['room_price_per_hour_vnd']}</td>
			                <td>${row['room_is_available'] == true ? 'Có' : 'Không'}</td>
			                <td>${row['room_note']}</td>
			                <td>
			                <form action="${pageContext.request.contextPath}/room" method="post" style="display: inline;">
								<input type="hidden" name="action" value="delete"> 
								<input type="hidden" name="room_id" value="${row['room_id']}">
								<button type="submit" class="btn btn-danger btn-action">
									<i class="bi bi-trash"></i> Xóa
								</button>
							</form> 
							<button type="button" class="btn btn-warning btn-action"
								data-bs-toggle="modal"
								data-bs-target="#editPhongModal${row['room_id']}">
								<i class="bi bi-pencil-square"></i> Sửa
							</button> 
							<!-- Modal Sửa Phòng -->
							<div class="modal fade"
									id="editPhongModal${row['room_id']}"
									tabindex="-1" aria-labelledby="editPhongModalLabel"
									aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="editPhongModalLabel">
													Sửa Phòng:
													${row['room_name']}
												</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<form action="${pageContext.request.contextPath}/room" method="post" enctype="multipart/form-data">
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="room_id" value="${row['room_id']}">

						<!-- Tên Phòng -->
						<div class="mb-3">
							<label for="room_name" class="form-label">Tên</label> <input
								type="text" class="form-control" id="room_name" name="room_name" value="${row['room_name']}"
								required>
						</div>
						
						
						
						<!-- Hình Ảnh -->
						<div class="mb-3">
						    <span class="form-switch me-${row['room_id']}">
						        <input class="form-check-input" type="checkbox" id="is_update_room_image_${row['room_id']}" name="is_update_room_image" 
						               onchange="toggleInput('is_update_room_image_${row['room_id']}', 'room_image_${row['room_id']}')" checked>
						    </span>
						
						    <label for="room_image_${row['room_id']}" class="form-label">Ảnh minh hoạ</label>
						    <input type="file" class="form-control" id="room_image_${row['room_id']}" name="room_image" />
						</div>
						
						<!-- diện tích -->
						<div class="mb-3">
							<label for="room_size" class="form-label">	Diện tích (m<sup>2</sup>)</label> <input
								type="number" class="form-control" id="room_size" name="room_size"  value="${row['room_size']}"
								required>
						</div>
						
						<!-- số giường -->
						<div class="mb-3">
						  <label for="room_bed_count" class="form-label">Số giường</label>
	                      <select class="form-select" id="room_bed_count" name="room_bed_count" aria-label="Floating label select example">
	                        <option value="1" ${row['room_bed_count'] == 1 ? "selected" : ""}>1 giường</option>
	                        <option value="2" ${row['room_bed_count'] == 2 ? "selected" : ""}>2 giường</option>
	                        <option value="3" ${row['room_bed_count'] == 3 ? "selected" : ""}>3 giường</option>
	                        <option value="4" ${row['room_bed_count'] == 4 ? "selected" : ""}>4 giường</option>
	                        <option value="5" ${row['room_bed_count'] == 5 ? "selected" : ""}>5 giường</option>
	                      </select>
	                    </div>
						
						<!-- số sao -->
						<div class="mb-3">
						  <label for="room_star_count" class="form-label">Số sao</label>
	                      <select class="form-select" id="room_star_count" name="room_star_count" aria-label="Floating label select example">
	                        <option value="1" ${row['room_star_count'] == 1 ? "selected" : ""}>1 sao</option>
	                        <option value="2" ${row['room_star_count'] == 2 ? "selected" : ""}>2 sao</option>
	                        <option value="3" ${row['room_star_count'] == 3 ? "selected" : ""}>3 sao</option>
	                        <option value="4" ${row['room_star_count'] == 4 ? "selected" : ""}>4 sao</option>
	                        <option value="5" ${row['room_star_count'] == 5 ? "selected" : ""}>5 sao</option>
	                      </select>
	                    </div>
	                    
	                    <!-- giá tiền -->
						<div class="mb-3">
							<label for="room_price_per_hour_vnd" class="form-label">Giá tiền (₫/đêm)</label> <input
								type="number" class="form-control" id="room_price_per_hour_vnd" name="room_price_per_hour_vnd" value="${row['room_price_per_hour_vnd']}"
								required>
						</div>
						
						<!-- khả dụng -->
						<div class="mb-3">
						  <label for="room_is_available" class="form-label">Khả dụng</label>
	                      <select class="form-select" id="room_is_available" name="room_is_available" aria-label="Floating label select example">
	                        <option value="1" ${row['room_is_available'] == true ? "selected" : ""}>Có</option>
	                        <option value="0" ${row['room_is_available'] == false ? "selected" : ""}>Không</option>
	                      </select>
	                    </div>
						
						<!-- mô tả -->
						<div class="mb-3">
							<label for="room_note" class="form-label">Mô tả</label>
							<textarea class="form-control" id="room_note" name="room_note" rows="3"
								required>${row['room_note']}</textarea>
						</div>
						
						<button type="submit" class="btn btn-warning">Sửa</button>
					</form>
											</div>
										</div>
									</div>
								</div>
			                </td>
			                
			                <!-- Add more columns as needed -->
			            </tr>
			        </c:forEach>
                </tbody>
              </table>
              <!-- End Table with stripped rows -->

            </div>
          </div>



        </div>
      </div>
    </section>

  </main><!-- End #main -->

	<div class="modal fade" id="addPhongModal" tabindex="-1"
		aria-labelledby="addPhongModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="addPhongModalLabel">Thêm Phòng</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form action="${pageContext.request.contextPath}/room" method="post" enctype="multipart/form-data">
						<input type="hidden" name="action" value="add">

						<!-- Tên Phòng -->
						<div class="mb-3">
							<label for="room_name" class="form-label">Tên</label> <input
								type="text" class="form-control" id="room_name" name="room_name"
								required>
						</div>
						
						<!-- Hình Ảnh -->
						<div class="mb-3">
							<label for="room_image" class="form-label">Ảnh minh hoạ</label> <input
								type="file" class="form-control" id="room_image" name="room_image">
						</div>
						
						<!-- diện tích -->
						<div class="mb-3">
							<label for="room_size" class="form-label">	Diện tích (m<sup>2</sup>)</label> <input
								type="number" class="form-control" id="room_size" name="room_size"
								required>
						</div>
						
						<!-- số giường -->
						<div class="mb-3">
						  <label for="room_bed_count" class="form-label">Số giường</label>
	                      <select class="form-select" id="room_bed_count" name="room_bed_count" aria-label="Floating label select example">
	                        <option value="1" selected>1 giường</option>
	                        <option value="2">2 giường</option>
	                        <option value="3">3 giường</option>
	                        <option value="4">4 giường</option>
	                        <option value="5">5 giường</option>
	                      </select>
	                    </div>
						
						<!-- số sao -->
						<div class="mb-3">
						  <label for="room_star_count" class="form-label">Số sao</label>
	                      <select class="form-select" id="room_star_count" name="room_star_count" aria-label="Floating label select example">
	                        <option value="1">1 sao</option>
	                        <option value="2">2 sao</option>
	                        <option value="3" selected>3 sao</option>
	                        <option value="4">4 sao</option>
	                        <option value="5">5 sao</option>
	                      </select>
	                    </div>
	                    
	                    <!-- giá tiền -->
						<div class="mb-3">
							<label for="room_price_per_hour_vnd" class="form-label">Giá tiền (₫/đêm)</label> <input
								type="number" class="form-control" id="room_price_per_hour_vnd" name="room_price_per_hour_vnd"
								required>
						</div>
						
						<!-- khả dụng -->
						<div class="mb-3">
						  <label for="room_is_available" class="form-label">Khả dụng</label>
	                      <select class="form-select" id="room_is_available" name="room_is_available" aria-label="Floating label select example">
	                        <option value="1" selected>Có</option>
	                        <option value="0">Không</option>
	                      </select>
	                    </div>
						
						<!-- mô tả -->
						<div class="mb-3">
							<label for="room_note" class="form-label">Mô tả</label>
							<textarea class="form-control" id="room_note" name="room_note" rows="3"
								required></textarea>
						</div>
						
						<button type="submit" class="btn btn-primary">Thêm Phòng</button>
					</form>
				</div>
			</div>
		</div>
	</div>
  <!-- ======= Footer ======= -->
  <footer id="footer" class="footer">
    <!-- <div class="copyright">
      &copy; Copyright <strong><span>NiceAdmin</span></strong>. All Rights Reserved
    </div>
    <div class="credits">
      Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
    </div> -->
  </footer>
  <!-- End Footer -->

  <!-- Vendor JS Files -->
  <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/chart.js/chart.umd.js"></script>
  <script src="assets/vendor/echarts/echarts.min.js"></script>
  <script src="assets/vendor/quill/quill.js"></script>
  <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="assets/vendor/tinymce/tinymce.min.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>

</body>

</html>