<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Pages / Register - NiceAdmin Bootstrap Template</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">
</head>

<body>
  <div class="container-fluid min-vh-100 d-flex justify-content-center">
    <div class="row align-items-center justify-content-center">
      <div class="col-12 col-sm-10 col-md-12 col-lg-10">

        <div class="d-flex justify-content-center py-4">
          <a href="index.html" class="logo d-flex align-items-center w-auto">
            <img src="assets/img/logo.png" alt="">
            <span>Logo</span>
          </a>
        </div><!-- End Logo -->

        <div class="card mb-3">

          <div class="card-body">

            <div class="pt-4 pb-2">
              <h5 class="card-title text-center pb-0 fs-4">Đăng ký</h5>
              <p class="text-center small">Nhập tên tài khoản và mật khẩu mong muốn.</p>
            </div>

            <form class="row g-3 needs-validation" novalidate>

              <div class="col-12 col-md-6">
                <div class="col-12">
                  <label for="yourName" class="form-label">Tên</label>
                  <input type="text" name="name" class="form-control" id="yourName" required>
                  <div class="invalid-feedback">Hãy nhập tên.</div>
                </div>
  
                <div class="col-12">
                  <label for="yourEmail" class="form-label">Email</label>
                  <input type="email" name="email" class="form-control" id="yourEmail" required>
                  <div class="invalid-feedback">Hãy nhập email.</div>
                </div>
  
                <div class="col-12">
                  <label for="yourEmail" class="form-label">Điện thoại</label>
                  <input type="email" name="email" class="form-control" id="yourEmail" required>
                  <div class="invalid-feedback">Hãy nhập email.</div>
                </div>
              </div>

              <div class="col-12 col-md-6">
                <div class="col-12">
                  <label for="yourEmail" class="form-label">Ngày sinh</label>
                  <input type="email" name="email" class="form-control" id="yourEmail" required>
                  <div class="invalid-feedback">Hãy nhập email.</div>
                </div>
  
                <div class="col-12">
                  <label for="yourUsername" class="form-label">Tên tài khoản</label>
                  <div class="input-group has-validation">
                    <span class="input-group-text" id="inputGroupPrepend">@</span>
                    <input type="text" name="username" class="form-control" id="yourUsername" required>
                    <div class="invalid-feedback">Hãy nhập tên tài khoản.</div>
                  </div>
                </div>
  
                <div class="col-12">
                  <label for="yourPassword" class="form-label">Mật khẩu</label>
                  <input type="password" name="password" class="form-control" id="yourPassword" required>
                  <div class="invalid-feedback">Hãy nhập mật khẩu.</div>
                </div>
  
                
              </div>

              <div class="col-12">
                <div class="form-check">
                  <input class="form-check-input" name="terms" type="checkbox" value="" id="acceptTerms" required>
                  <label class="form-check-label" for="acceptTerms">Tôi đồng ý với các <a href="#">điều khoản và dịch vụ.</a></label>
                  <div class="invalid-feedback">Bạn phải đồng ý.</div>
                </div>
              </div>
              
              <div class="col-12">
                <button class="btn btn-primary w-100" type="submit">Đăng ký</button>
              </div>
              <div class="col-12">
                <p class="small mb-0">Đã có tài khoản? <a href="1. dang nhap.html">Đăng nhập</a></p>
              </div>
            </form>

          </div>
        </div>

        <!-- <div class="credits">
          Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
        </div> -->

      </div>
    </div>

  </div>
</body>

</html>