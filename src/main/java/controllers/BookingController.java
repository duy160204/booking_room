package controllers;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import org.javatuples.Pair;

import objects.BookingDetailObject;
import objects.UserObject;
import booking.BookingModel;
import services.Util;

@WebServlet("/booking")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class BookingController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Util.setDefaultEncoding(request, response);
        
        HttpSession session = request.getSession();
		UserObject user = (UserObject)session.getAttribute("userLogined");
		if(user==null) {
			handleAnonymousUserViewBooking(request, response);
		} else {
			handleUserViewBooking(request, response);
		}
		return;
    }
    
    private void handleUserViewBooking(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	
    	//Tìm tham số báo lỗi nếu có
		String error = request.getParameter("err");
		String flag = "";
		String mes = "";
		if (error != null) {
			switch (error) {
			case "auth":
				flag = "fail";
				mes = "Không đủ quyền thực hiện.";
				break;
			case "actionnotdefined":
				flag = "fail";
				mes = "Chưa chỉ ra action.";
				break;
			case "fail":
				flag = "fail";
				mes = "Thao tác thất bại.";
				break;
			case "ok":
				flag = "ok";
				mes = "Thao tác thành công.";
				break;
			default:
				flag = "";
				mes = "";
			}
		}
		request.setAttribute("flag", flag);
		request.setAttribute("message", mes);
        
        String pageParamater = request.getParameter("page");
        short currentPage = (pageParamater != null && !pageParamater.isEmpty()) 
                ? Short.parseShort(pageParamater) 
                : 1;
        if (currentPage < (short) 1) currentPage = 1;
        
        byte totalPerPage = 30;
          
        BookingModel bookingModel = new BookingModel();
        Pair<ArrayList<BookingDetailObject>, Integer> returnSet = 
        		bookingModel.getBookingDetailObjects(new BookingDetailObject(), currentPage, totalPerPage);
        ArrayList<BookingDetailObject> itemsOfCurrentPage = returnSet.getValue0();
        Integer totalItems = returnSet.getValue1();
        int totalPages = (int) Math.ceil((double) totalItems / totalPerPage);
        
        bookingModel.releaseConnection();
        
        request.setAttribute("items", itemsOfCurrentPage);
        request.setAttribute("currentPage", currentPage); 
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalItems", totalItems); 
        
        request.getRequestDispatcher("quản lý đặt phòng.jsp").include(request, response);
        return;
	}

	private void handleAnonymousUserViewBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/login");
	}

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Util.setDefaultEncoding(request, response);
    	
//    	System.out.println("Request Parameters:");
//		request.getParameterMap().forEach((key, value) -> {
//		    System.out.println(key + ": " + String.join(", ", value));
//		});
    	
    	HttpSession session = request.getSession();
		UserObject user = (UserObject)session.getAttribute("userLogined");
		if(user==null) {
			response.sendRedirect(request.getContextPath() + "/booking?err=auth");
			// response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
         
        String action = request.getParameter("action");
        if(action == null) {
        	response.sendRedirect(request.getContextPath() + "/booking?err=actionnotdefined");
        }
        switch(action) {
        case "accept":
        	System.out.println("in the accept");
        	handleAccept(request, response);
        	return;
		case "reject":
			System.out.println("in the reject");
			handleReject(request, response);
			return;
         default:
        	 response.sendRedirect(request.getContextPath() + "/booking?err=actionnotdefined");
        	 break;	 
         }
    }
    
    private void handleAccept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get booking details from the form
      	Integer booking_id = Integer.parseInt(request.getParameter("booking_id"));
      	
    	BookingModel model = new BookingModel();
      	boolean success = model.setAccept(booking_id);
      	model.releaseConnection();
      	
      	if(success) response.sendRedirect(request.getContextPath() + "/booking?err=ok");
     	else response.sendRedirect(request.getContextPath() + "/booking?err=fail");
     	return;
  	}
    
    private void handleReject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get booking details from the form
      	Integer booking_id = Integer.parseInt(request.getParameter("booking_id"));
      	
    	BookingModel model = new BookingModel();
      	boolean success = model.setReject(booking_id);
      	model.releaseConnection();
      	
      	if(success) response.sendRedirect(request.getContextPath() + "/booking?err=ok");
     	else response.sendRedirect(request.getContextPath() + "/booking?err=fail");
     	return;
  	}
    
    
//    private void handleCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Get booking details from the form
//        Integer customer_id = Integer.parseInt(request.getParameter("customer_id"));
//        Integer room_id = Integer.parseInt(request.getParameter("room_id"));
//        Integer booking_state = request.getParameter("booking_state") != null 
//                                    ? Integer.parseInt(request.getParameter("booking_state")) 
//                                    : null;
//        String booking_comment = request.getParameter("booking_comment");
//        Integer booking_rate = request.getParameter("booking_rate") != null 
//                                    ? Integer.parseInt(request.getParameter("booking_rate")) 
//                                    : null;
//        String booking_start_date = request.getParameter("booking_start_date");
//        String booking_end_date = request.getParameter("booking_end_date");
//        Integer booking_people_count = Integer.parseInt(request.getParameter("booking_people_count"));
//        String booking_note = request.getParameter("booking_note");
//
//        // Fill BookingObject
//        BookingObject bookingObject = new BookingObject();
//        bookingObject.setCustomerId(customer_id);
//        bookingObject.setRoomId(room_id);
//        bookingObject.setBookingState(booking_state);
//        bookingObject.setBookingComment(booking_comment);
//        bookingObject.setBookingRate(booking_rate);
//        bookingObject.setBookingStartDate(Date.valueOf(booking_start_date)); // Convert to SQL Date
//        bookingObject.setBookingEndDate(Date.valueOf(booking_end_date)); // Convert to SQL Date
//        bookingObject.setBookingPeopleCount(booking_people_count);
//        bookingObject.setBookingNote(booking_note);
//
//        // Add to database
//        boolean success = model.addBooking(bookingObject);
//
//        // Return page
//        if (success) {
//            request.getSession().setAttribute("success", "Tạo thành công.");
//        } else {
//            request.getSession().setAttribute("failure", "Tạo thất bại.");
//        }
//        response.sendRedirect(request.getContextPath() + "/booking");
//    }
//    
//    
//    
//    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	
//    	// Get booking details from the form
//    	Integer booking_id = Integer.parseInt(request.getParameter("booking_id"));
//        Integer customer_id = Integer.parseInt(request.getParameter("customer_id"));
//        Integer room_id = Integer.parseInt(request.getParameter("room_id"));
//        Integer booking_state = request.getParameter("booking_state") != null 
//                                    ? Integer.parseInt(request.getParameter("booking_state")) 
//                                    : null;
//        String booking_comment = request.getParameter("booking_comment");
//        Integer booking_rate = request.getParameter("booking_rate") != null 
//                                    ? Integer.parseInt(request.getParameter("booking_rate")) 
//                                    : null;
//        String booking_start_date = request.getParameter("booking_start_date");
//        String booking_end_date = request.getParameter("booking_end_date");
//        Integer booking_people_count = Integer.parseInt(request.getParameter("booking_people_count"));
//        String booking_note = request.getParameter("booking_note");
//
//        // Fill BookingObject
//        BookingObject bookingObject = new BookingObject();
//        bookingObject.setBookingId(booking_id);
//        bookingObject.setCustomerId(customer_id);
//        bookingObject.setRoomId(room_id);
//        bookingObject.setBookingState(booking_state);
//        bookingObject.setBookingComment(booking_comment);
//        bookingObject.setBookingRate(booking_rate);
//        bookingObject.setBookingStartDate(Date.valueOf(booking_start_date)); 
//        bookingObject.setBookingEndDate(Date.valueOf(booking_end_date)); 
//        bookingObject.setBookingPeopleCount(booking_people_count);
//        bookingObject.setBookingNote(booking_note);
//
//        // Add to database
//    	boolean success = model.editBooking(bookingObject);
//     	
//     	// return page
//    	if(success) request.getSession().setAttribute("success", "Sửa thành công.");
//    	else request.getSession().setAttribute("failure", "Sửa không thành công.");
//     	response.sendRedirect(request.getContextPath() + "/room");
//     	return;
//    }
//
//    private void handleDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    	// lấy thông tin từ form
//        Integer room_id = Integer.parseInt(request.getParameter("room_id"));
//        
//        // delete trong db
//    	boolean success = model.delBooking(room_id);
//    	
//    	// return page
//    	if(success) request.getSession().setAttribute("success", "Xoá thành công.");
//    	else request.getSession().setAttribute("failure", "Xoá không thành công.");
//     	response.sendRedirect(request.getContextPath() + "/room");
//     	return;
//    }
}

