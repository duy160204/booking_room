package controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import room.Room;
import room.RoomImpl;
//import objects.RoomObject;

@WebServlet("/room")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class PhongController extends HttpServlet {

	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        Room room = new RoomImpl(); 
        ArrayList<ResultSet> roomList = room.getRooms(null, 0, (byte)100);
//        request.setAttribute("listPhong", listPhong);

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            request.setAttribute("showAddModal", true);  // Hiển thị modal thêm phòng
        }

        request.getRequestDispatcher("QuanLyPhong.jsp").forward(request, response);
    }

//    // Xử lý yêu cầu POST
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");
//
//        String action = request.getParameter("action");
//        PhongDAO phongDAO = new PhongDAO();
//
//        switch (action) {
//            case "add":
//                // Lấy thông tin phòng từ form
//                String maPhong = request.getParameter("maPhong");
//                String tenPhong = request.getParameter("tenPhong");
//                String trangThai = request.getParameter("trangThai");
//                int giaTien = Integer.parseInt(request.getParameter("giaTien"));
//                int soLuongNguoi = Integer.parseInt(request.getParameter("soLuongNguoi"));
//                double dienTich = Double.parseDouble(request.getParameter("dienTich")); // Lấy diện tích
//                String moTa = request.getParameter("moTa"); // Lấy mô tả
//
//                // Xử lý tải ảnh lên
//                Part filePart = request.getPart("hinhAnh");
//                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//                String uploadDir = getServletContext().getRealPath("uploads");  // Đường dẫn thư mục lưu hình ảnh
//                File uploadDirFile = new File(uploadDir);
//                if (!uploadDirFile.exists()) {
//                    uploadDirFile.mkdirs();  // Tạo thư mục nếu không tồn tại
//                }
//                File file = new File(uploadDir, fileName);
//                filePart.write(file.getAbsolutePath());  // Lưu tệp vào thư mục
//             // Xử lý tải ảnh hinhAnh2
//                Part filePart2 = request.getPart("hinhAnh2");
//                String fileName2 = Paths.get(filePart2.getSubmittedFileName()).getFileName().toString();
//                String uploadDir2 = getServletContext().getRealPath("uploads"); // Đường dẫn thư mục lưu hình ảnh
//                File uploadDirFile2 = new File(uploadDir2);
//                if (!uploadDirFile2.exists()) {
//                    uploadDirFile2.mkdirs(); // Tạo thư mục nếu không tồn tại
//                }
//                File file2 = new File(uploadDir2, fileName2);
//                filePart2.write(file2.getAbsolutePath()); // Lưu tệp vào thư mục
//
//                // Xử lý tải ảnh hinhAnh3
//                Part filePart3 = request.getPart("hinhAnh3");
//                String fileName3 = Paths.get(filePart3.getSubmittedFileName()).getFileName().toString();
//                String uploadDir3 = getServletContext().getRealPath("uploads"); // Đường dẫn thư mục lưu hình ảnh
//                File uploadDirFile3 = new File(uploadDir3);
//                if (!uploadDirFile3.exists()) {
//                    uploadDirFile3.mkdirs(); // Tạo thư mục nếu không tồn tại
//                }
//                File file3 = new File(uploadDir3, fileName3);
//                filePart3.write(file3.getAbsolutePath()); // Lưu tệp vào thư mục
//
//                // Kiểm tra mã phòng có bị trùng không
//                Phong phong = new Phong(maPhong, tenPhong, trangThai, giaTien, soLuongNguoi, fileName,fileName2,fileName3, dienTich, moTa);
//                int insert = phongDAO.insert(phong);
//                if (insert < 0) {
//                    request.setAttribute("message", "Mã phòng đã tồn tại. Vui lòng nhập lại!");
//                    request.getRequestDispatcher("QuanLyPhong.jsp").forward(request, response);
//                } else {
//                    response.sendRedirect("phong?status=success");
//                }
//                return;
//
//            case "delete":
//                // Xóa phòng
//                String maPhongToDelete = request.getParameter("maPhong");
//                Phong phongToDelete = new Phong();
//                phongToDelete.setMaPhong(maPhongToDelete);
//                phongDAO.delete(phongToDelete);
//                response.sendRedirect("phong?status=success2");
//                return;
//
//            case "update":
//                // Cập nhật thông tin phòng
//                String maPhongToUpdate = request.getParameter("maPhong");
//                String newTenPhong = request.getParameter("tenPhong");
//                String newTrangThai = request.getParameter("trangThai");
//                int newGiaTien = Integer.parseInt(request.getParameter("giaTien"));
//                int newSoLuongNguoi = Integer.parseInt(request.getParameter("soLuongNguoi"));
//                double newDienTich = Double.parseDouble(request.getParameter("dienTich")); // Lấy diện tích mới
//                String newMoTa = request.getParameter("moTa"); // Lấy mô tả mới
//
//                // Lấy thông tin hình ảnh (nếu có thay đổi)
//             // For the first image (hinhAnh)
//                Part filePart4 = request.getPart("hinhAnh");
//                String newHinhAnh = null;
//                if (filePart4 != null && filePart4.getSize() > 0) {
//                    String fileName4 = Paths.get(filePart4.getSubmittedFileName()).getFileName().toString();
//                    String uploadDir4 = getServletContext().getRealPath("uploads");
//                    File uploadDirFile4 = new File(uploadDir4);
//                    if (!uploadDirFile4.exists()) {
//                        uploadDirFile4.mkdirs();
//                    }
//                    File file4 = new File(uploadDir4, fileName4);
//                    filePart4.write(file4.getAbsolutePath());
//                    newHinhAnh = fileName4;
//                } else {
//                    newHinhAnh = request.getParameter("currentHinhAnh");  // Giữ lại ảnh cũ nếu không có ảnh mới
//                }
//
//                // For the second image (hinhAnh2)
//                Part filePart5 = request.getPart("hinhAnh2");
//                String newHinhAnh2 = null;
//                if (filePart5 != null && filePart5.getSize() > 0) {
//                    String fileName5 = Paths.get(filePart5.getSubmittedFileName()).getFileName().toString();
//                    String uploadDir5 = getServletContext().getRealPath("uploads");
//                    File uploadDirFile5 = new File(uploadDir5);
//                    if (!uploadDirFile5.exists()) {
//                        uploadDirFile5.mkdirs();
//                    }
//                    File file5 = new File(uploadDir5, fileName5);
//                    filePart5.write(file5.getAbsolutePath());
//                    newHinhAnh2 = fileName5;
//                } else {
//                    newHinhAnh2 = request.getParameter("currentHinhAnh2");  // Giữ lại ảnh cũ nếu không có ảnh mới
//                }
//
//                // For the third image (hinhAnh3)
//                Part filePart6 = request.getPart("hinhAnh3");
//                String newHinhAnh3 = null;
//                if (filePart6 != null && filePart6.getSize() > 0) {
//                    String fileName6 = Paths.get(filePart6.getSubmittedFileName()).getFileName().toString();
//                    String uploadDir6 = getServletContext().getRealPath("uploads");
//                    File uploadDirFile6 = new File(uploadDir6);
//                    if (!uploadDirFile6.exists()) {
//                        uploadDirFile6.mkdirs();
//                    }
//                    File file6 = new File(uploadDir6, fileName6);
//                    filePart6.write(file6.getAbsolutePath());
//                    newHinhAnh3 = fileName6;
//                } else {
//                    newHinhAnh3 = request.getParameter("currentHinhAnh3");  // Giữ lại ảnh cũ nếu không có ảnh mới
//                }
//
//
//                // Cập nhật phòng
//                Phong phongToUpdate = new Phong(maPhongToUpdate, newTenPhong, newTrangThai, newGiaTien, newSoLuongNguoi, newHinhAnh,newHinhAnh2,newHinhAnh3, newDienTich, newMoTa);
//                phongDAO.update(phongToUpdate);
//                response.sendRedirect("phong?status=success3");
//                return;
//
//            default:
//                response.sendRedirect("phong?status=error&message=Invalid action");
//                return;
//        }
//    }
}

