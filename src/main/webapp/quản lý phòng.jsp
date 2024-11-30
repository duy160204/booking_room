<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

              <!-- Table with stripped rows -->
              <table class="table table-striped">
                <thead>
                  <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Tên phòng</th>
                    <th scope="col">Người sở hữu phòng</th>
                    <th scope="col">Vị trí phòng</th>
                    <th scope="col">Hành động</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <th scope="row">1</th>
                    <td>Phòng 101</td>
                    <td>Bùi Hải Đăng</td>
                    <td>Nhà A1</td>
                    <td>
                      <a class="btn btn-success" href="5. xem phong">Xem</a> 
                      <a class="btn btn-primary" href="6. sua phong">Sửa</a> 
                      <a class="btn btn-danger" href="7. xoa phong">Xóa</a> 
                    </td>
                  </tr>
                  <tr>
                    <th scope="row">1</th>
                    <td>Phòng 101</td>
                    <td>Bùi Hải Đăng</td>
                    <td>Nhà A1</td>
                    <td>
                      <a class="btn btn-success" href="5. xem phong">Xem</a> 
                      <a class="btn btn-primary" href="6. sua phong">Sửa</a> 
                      <a class="btn btn-danger" href="7. xoa phong">Xóa</a> 
                    </td>
                  </tr>
                  <tr>
                    <th scope="row">2</th>
                    <td>Phòng 306</td>
                    <td>Vũ Minh Ngọc</td>
                    <td>Nhà A5</td>
                    <td>
                      <a class="btn btn-success" href="5. xem phong">Xem</a> 
                      <a class="btn btn-primary" href="6. sua phong">Sửa</a> 
                      <a class="btn btn-danger" href="7. xoa phong">Xóa</a> 
                    </td>
                  </tr>
                  <tr>
                    <th scope="row">3</th>
                    <td>Phòng 901</td>
                    <td>Bùi Hải Đăng</td>
                    <td>Nhà B8</td>
                    <td>
                      <a class="btn btn-success" href="5. xem phong">Xem</a> 
                      <a class="btn btn-primary" href="6. sua phong">Sửa</a> 
                      <a class="btn btn-danger" href="7. xoa phong">Xóa</a> 
                    </td>
                  </tr>
                  <tr>
                    <th scope="row">4</th>
                    <td>Phòng 1101</td>
                    <td>Lê Trung Nguyên</td>
                    <td>Nhà C1</td>
                    <td>
                      <a class="btn btn-success" href="5. xem phong">Xem</a> 
                      <a class="btn btn-primary" href="6. sua phong">Sửa</a> 
                      <a class="btn btn-danger" href="7. xoa phong">Xóa</a> 
                    </td>
                  </tr>
                </tbody>
              </table>
              <!-- End Table with stripped rows -->

            </div>
          </div>



        </div>
      </div>
    </section>

  </main><!-- End #main -->

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