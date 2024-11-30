<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Đăng nhập</title>
<meta content="" name="description">
<meta content="" name="keywords">

<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">

<link href="assets/css/style.css" rel="stylesheet">
</head>

<body>




	<div class="container-fluid min-vh-100 d-flex justify-content-center">
		<div class="row align-items-center justify-content-center">
			<div class="col-12 col-sm-10 col-md-8 col-lg-7 col-xxl-6"
				style="min-width: 576px;">

				<div class="d-flex justify-content-center py-4">
					<a href="index.html" class="logo d-flex align-items-center w-auto">
						<img src="assets/img/logo.png" alt=""> <span>Logo</span>
					</a>
				</div>
				<!-- End Logo -->

				<div class="card card-body">

					<div class="pt-4 pb-2">
						<h5 class="card-title text-center pb-0 fs-4">Đăng nhập</h5>
					</div>

					<form class="row g-3 needs-validation"
						action="${pageContext.request.contextPath}/login" method="POST"
						novalidate>

						<div class="col-12">
							<label for="username" class="form-label">Tài khoản</label>
							<div class="input-group has-validation">
								<span class="input-group-text" id="inputGroupPrepend">@</span> <input
									type="text" name="username" class="form-control" id="username"
									required autofocus>
								<div class="invalid-feedback">Không được để trống tài
									khoản.</div>
							</div>
						</div>

						<div class="col-12">
							<label for="password" class="form-label">Mật khẩu</label> <input
								type="password" name="password" class="form-control"
								id="password" required>
							<div class="invalid-feedback">Không được để trống mật khẩu.</div>
						</div>

						<%
						Object error = request.getAttribute("error");
						if (error != null) {
						%>
						<div class="col-12">
						<div class="alert alert-danger alert-dismissible fade show"
							 role="alert">
							<i class="bi bi-exclamation-octagon me-1"></i>
							<%=error.toString()%>
							<button type="button" class="btn-close" data-bs-dismiss="alert"
								aria-label="Close"></button>
						</div>
						</div>
						<%
						}
						%>



						<!-- 
            <div class="col-12">
              <div class="form-check">
                <input class="form-check-input" type="checkbox" name="remember" value="true" id="rememberMe">
                <label class="form-check-label" for="rememberMe">Lưu thông tin đăng nhập</label>
              </div>
            </div>
             -->
						<div class="col-12"></div>
						<div class="col-12">
							<button class="btn btn-primary w-100" type="submit">Đăng
								nhập</button>
						</div>
						<!-- 
            <div class="col-12">
              <p class="small mb-0">Chưa có tài khoản? <a href="/register">Đăng ký</a></p>
            </div>
             -->
					</form>

				</div>
			</div>
		</div>

	</div>

	<script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendor/chart.js/chart.umd.js"></script>
	<script src="assets/vendor/echarts/echarts.min.js"></script>
	<script src="assets/vendor/quill/quill.js"></script>
	<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
	<script src="assets/vendor/tinymce/tinymce.min.js"></script>
	<script src="assets/js/main.js"></script>
</body>

</html>