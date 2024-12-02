package controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import basicUtil.BasicImpl;
import objects.RoomObject;
import room.RoomImpl;
import services.Authenticate;
@WebServlet("/room")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class RoomController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        if(!Authenticate.isLoggedInAsAdmin(request)) {
        	response.sendRedirect(request.getContextPath() + "/login");
        	return;
        }
        
        // message gửi từ các phương thức post, put, delete
        HttpSession session = request.getSession();
        String successMessage = (String) session.getAttribute("success");
        String failureMessage = (String) session.getAttribute("failure");
        session.removeAttribute("success");
        session.removeAttribute("failure");
        if (successMessage != null) {
            request.setAttribute("success", successMessage);
        }
        if (failureMessage != null) {
            request.setAttribute("failure", failureMessage);
        }
        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM tblroom WHERE admin_id = " + Authenticate.getAdminId(request) + ";");
        sql.append("SELECT COUNT(room_id) AS all_total FROM tblroom;");
        
        BasicImpl basicImpl = new BasicImpl("room controller");
        List<ResultSet> listResultSet = basicImpl.gets(sql.toString());
        
        ResultSet roomResultSet = listResultSet.get(0);
        
        try {
        	List<Map<String, Object>> roomList = new ArrayList<>();
        	while (roomResultSet.next()) {
        	    Map<String, Object> row = new HashMap<>();
        	    for (int i = 1; i <= roomResultSet.getMetaData().getColumnCount(); i++) {
        	        String columnName = roomResultSet.getMetaData().getColumnName(i);

        	        // Handle the room_image column
        	        if ("room_image".equalsIgnoreCase(columnName)) {
        	            byte[] imageBytes = roomResultSet.getBytes("room_image");
        	            if (imageBytes != null) {
        	                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        	                row.put("room_image", base64Image);
        	            } else {
        	                row.put("room_image", null);
        	            }
        	        } else {
        	            row.put(columnName, roomResultSet.getObject(i));
        	        }
        	    }
        	    roomList.add(row);
        	}
        	System.out.println(roomList.size());
        	request.setAttribute("roomList", roomList);
        	request.setAttribute("local_total", roomList.size());
        	roomResultSet.close();
        	
        	ResultSet totalResultSet = listResultSet.get(1);
        	totalResultSet.next();
            request.setAttribute("all_total", totalResultSet.getInt("all_total"));
            totalResultSet.close();
        } catch(SQLException e) {
        	e.printStackTrace();
        }
        basicImpl.releaseConnection();
        request.getRequestDispatcher("quản lý phòng.jsp").forward(request, response);
        return;
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
    	request.setCharacterEncoding("UTF-8");
    	
    	if(!Authenticate.isLoggedInAsAdmin(request)) {
        	response.sendRedirect(request.getContextPath() + "/login");
        	return;
        }
         
        String action = request.getParameter("action");
        switch(action) {
        case "add":
        	System.out.println("in the add");
        	handleCreate(request, response);
        	return;
		case "delete":
			System.out.println("in the delete");
			handleDelete(request, response);
			return;
		case "update":
			System.out.println("in the update");
			handleUpdate(request, response);
			return;
         default:
        	 // TODO return 400 bad request
        	 break;	 
         }
    }
    
    private void handleCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// Lấy thông tin phòng từ form
     	String room_name = request.getParameter("room_name");
     	Integer admin_id = Authenticate.getAdminId(request);
     	byte[] room_image = request.getPart("room_image").getInputStream().readAllBytes();
     	Double room_size = Double.parseDouble(request.getParameter("room_size"));
     	Integer room_bed_count = Integer.parseInt(request.getParameter("room_bed_count"));
     	Integer room_star_count = Integer.parseInt(request.getParameter("room_star_count"));
     	Double room_price_per_hour_vnd = Double.parseDouble(request.getParameter("room_price_per_hour_vnd"));
     	Boolean room_is_available = Boolean.parseBoolean(request.getParameter("room_is_available"));
     	String room_note = request.getParameter("room_note");
     	
     	// fill object
     	RoomObject roomObject = new RoomObject();
     	roomObject.setRoomName(room_name);
     	roomObject.setAdminId(admin_id);
     	roomObject.setRoomImage(room_image);
     	roomObject.setRoomSize(room_size);
     	roomObject.setRoomBedCount(room_bed_count);
     	roomObject.setRoomStarCount(room_star_count);
     	roomObject.setRoomPricePerHourVnd(room_price_per_hour_vnd);
     	roomObject.setRoomIsAvailable(room_is_available);
     	roomObject.setRoomNote(room_note);
     	
     	// add vào db
     	RoomImpl roomImpl = new RoomImpl();
     	boolean success = roomImpl.addRoom(roomObject);
     	roomImpl.releaseConnection();
     	
     	// return page

    	if(success) request.getSession().setAttribute("success", "Thêm thành công.");
    	else request.getSession().setAttribute("failure", "Thêm không thành công.");
     	response.sendRedirect(request.getContextPath() + "/room");
     	return;
    }
    
    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// Lấy thông tin phòng từ form
    	Integer room_id = Integer.parseInt(request.getParameter("room_id"));
     	String room_name = request.getParameter("room_name");
     	Integer admin_id = Authenticate.getAdminId(request);
     	Part image = request.getPart("room_image");
     	byte[] room_image = (image != null) ? image.getInputStream().readAllBytes() : null;
     	Double room_size = Double.parseDouble(request.getParameter("room_size"));
     	Integer room_bed_count = Integer.parseInt(request.getParameter("room_bed_count"));
     	Integer room_star_count = Integer.parseInt(request.getParameter("room_star_count"));
     	Double room_price_per_hour_vnd = Double.parseDouble(request.getParameter("room_price_per_hour_vnd"));
     	Boolean room_is_available = Boolean.parseBoolean(request.getParameter("room_is_available"));
     	String room_note = request.getParameter("room_note");
     	
     	// fill object
     	RoomObject roomObject = new RoomObject();
     	roomObject.setRoomId(room_id);
     	roomObject.setRoomName(room_name);
     	roomObject.setAdminId(admin_id);
     	roomObject.setRoomImage(room_image);
     	roomObject.setRoomSize(room_size);
     	roomObject.setRoomBedCount(room_bed_count);
     	roomObject.setRoomStarCount(room_star_count);
     	roomObject.setRoomPricePerHourVnd(room_price_per_hour_vnd);
     	roomObject.setRoomIsAvailable(room_is_available);
     	roomObject.setRoomNote(room_note);
     	
     	// update vào db
     	RoomImpl roomImpl = new RoomImpl();
     	boolean success = false;
     	String isUpdateRoomImage = request.getParameter("is_update_room_image");
//     	System.out.println(isUpdateRoomImage);
     	if(isUpdateRoomImage != null && isUpdateRoomImage.equals("on")) {
         	success = roomImpl.editRoom(roomObject);
     	}
     	else {
     		success = roomImpl.editRoomWithoutImage(roomObject);
     	}
     	roomImpl.releaseConnection();
     	
     	// return page
    	if(success) request.getSession().setAttribute("success", "Sửa thành công.");
    	else request.getSession().setAttribute("failure", "Sửa không thành công.");
     	response.sendRedirect(request.getContextPath() + "/room");
     	return;
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	// lấy thông tin từ form
        Integer room_id = Integer.parseInt(request.getParameter("room_id"));
        
        // delete trong db
        RoomImpl roomImpl = new RoomImpl();
    	boolean success = roomImpl.delRoom(room_id);
    	roomImpl.releaseConnection();
    	
    	// return page
    	if(success) request.getSession().setAttribute("success", "Xoá thành công.");
    	else request.getSession().setAttribute("failure", "Xoá không thành công.");
     	response.sendRedirect(request.getContextPath() + "/room");
     	return;
    }
}

