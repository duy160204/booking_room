package controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import booking.BookingModel;
import objects.BookingObject;
import objects.RoomObject;
import room.RoomModel;
import services.Util;

@WebServlet("/bookingforclient")
public class BookingForClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookingForClientController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Util.setDefaultEncoding(request, response);
        
        String roomIdParam = request.getParameter("room_id");
        if(roomIdParam == null || roomIdParam.isEmpty()) {
        	response.sendRedirect(request.getContextPath() + "404.jsp");
        	return;
        }
        
        int id = 0;
        try {
        	id = Integer.parseInt(roomIdParam);
        } catch (NumberFormatException e) {
        	response.sendRedirect(request.getContextPath() + "404.jsp");
        	return;
        }
        
        
        // Tìm tham số báo lỗi nếu có
 		String error = request.getParameter("err");
 		String flag = "";
 		String mes = "";
 		String uuid = request.getParameter("uuid");
 		
 		if (error != null) {
 			switch (error) {
 			case "fail":
 				flag = "fail";
 				mes = "Có lỗi khi đặt phòng. Vui lòng thử lại sau.";
 				break;
 			case "success":
 				flag = "ok";
 				mes = "Đặt phòng thành công.";
 				break;
 			case "param":
 				flag = "fail";
 				mes = "Lỗi các tham số đầu vào.";
 				break;
 			default:
 				flag = "";
 				mes = "";
 			}
 		}
 		request.setAttribute("flag", flag);
 		request.setAttribute("message", mes);
 		request.setAttribute("uuid", uuid);
 		
        RoomModel roomModel = new RoomModel();
        RoomObject roomObject = roomModel.getRoomObject(id);

        request.setAttribute("room", roomObject);
        request.setAttribute("somekey", "somevalue");
        request.getRequestDispatcher("khách hàng đặt phòng.jsp").include(request, response);
        return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Util.setDefaultEncoding(request, response);
		
//    	System.out.println("POST parameters:");
//    	request.getParameterMap().forEach((key, values) -> {
//    	    System.out.println(key + " = " + String.join(", ", values));
//    	});
		
		System.out.println("POST parameters:");
    	request.getParameterMap().forEach((key, value) -> {
    	    System.out.println(key + " = " + value.toString());
    	});
    	
    	String roomIdParam = request.getParameter("room_id");
    	String bookingStartDateParam = request.getParameter("booking_start_date");
    	String bookingEndDateParam = request.getParameter("booking_end_date");
    	String customerContactParam = request.getParameter("customer_contact");
    	String bookingPeopleCountParam = request.getParameter("booking_people_count");
    	String bookingNote = request.getParameter("booking_note");
    	String customerContact1Param = request.getParameter("customer_contact1");
    	
    	System.out.println(roomIdParam);
    	System.out.println(bookingStartDateParam);
    	System.out.println(bookingEndDateParam);
    	System.out.println(customerContactParam);
    	System.out.println(bookingPeopleCountParam);
    	System.out.println(bookingNote);
    	
    	int roomId = 0;
    	try {
    		roomId = Integer.parseInt(roomIdParam);
    	} catch(NumberFormatException e) {
    		response.sendRedirect(request.getContextPath() + "/bookingforclient?room_id=" + roomIdParam + "&err=param");
    		return;
    	}
    	
    	Date bookingStartDate;
    	try {
    		bookingStartDate = Date.valueOf(bookingStartDateParam);
    	} catch(IllegalArgumentException e) {
    		response.sendRedirect(request.getContextPath() + "/bookingforclient?room_id=" + roomIdParam + "&err=param");
    		return;
    	}
    	
    	Date bookingEndDate;
    	try {
    		bookingEndDate = Date.valueOf(bookingEndDateParam);
    	} catch(IllegalArgumentException e) {
    		response.sendRedirect(request.getContextPath() + "/bookingforclient?room_id=" + roomIdParam + "&err=param");
    		return;
    	}
    	
    	if (bookingStartDate.compareTo(bookingEndDate) > 0) {
    		response.sendRedirect(request.getContextPath() + "/bookingforclient?room_id=" + roomIdParam + "&err=param");
    		return;
    	}
    	
    	String customerContact = customerContactParam;
    	if (customerContact == null) {
    		response.sendRedirect(request.getContextPath() + "/bookingforclient?room_id=" + roomIdParam + "&err=param");
    		return;
    	}
    	
    	int bookingPeopleCount = 0;
    	try {
    		bookingPeopleCount = Integer.parseInt(bookingPeopleCountParam);
    	} catch(NumberFormatException e) {
    		response.sendRedirect(request.getContextPath() + "/bookingforclient?room_id=" + roomIdParam + "&err=param");
    		return;
    	}
    	
    	String bookingUUID = UUID.randomUUID().toString();
    	
    	BookingObject bookingObject = new BookingObject();
    	bookingObject.setRoomId(roomId);
    	bookingObject.setBookingStartDate(bookingStartDate);
    	bookingObject.setBookingEndDate(bookingEndDate);
    	bookingObject.setCustomerContact(customerContact);
    
		bookingObject.setCustomerContact1(customerContact1Param);
    	bookingObject.setBookingPeopleCount(bookingPeopleCount);
    	bookingObject.setBookingNote(bookingNote);
    	bookingObject.setBookingUuid(bookingUUID);
    	bookingObject.setBookingState(0);
    	bookingObject.setBookingRate(-1);
    	
    	BookingModel bookingModel = new BookingModel();
    	boolean success = bookingModel.addBooking(bookingObject);
    	bookingModel.releaseConnection();
    	
    	if(success) {
    		response.sendRedirect(request.getContextPath() + "/bookingforclient?room_id=" + roomIdParam + "&err=success&uuid=" + bookingUUID);
    		return;
    	} else {
    		response.sendRedirect(request.getContextPath() + "/bookingforclient?room_id=" + roomIdParam + "&err=fail");
    		return;
    	}
    	
    	
	}

}
