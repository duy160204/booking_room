package booking;

import java.sql.*;
import java.util.*;
import org.javatuples.*;

import objects.BookingDetailObject;
import objects.BookingObject;

public class BookingModel {
	private BookingImpl booking;
	
	public BookingModel() {
		this.booking = new BookingImpl();
	}
	
	public boolean addBooking(BookingObject item) {
		return this.booking.addBooking(item);
	}

	public boolean editBooking(BookingObject item) {
		return this.booking.editBooking(item);
	}
	
	public boolean setAccept(int id) {
		return this.booking.editState(id, 1);
	}
	
	public boolean setReject(int id) {
		return this.booking.editState(id, -1);
	}
	

	public boolean delBooking(int id) {
		return this.booking.delBooking(id);
	}
	
	public BookingDetailObject getBookingObject(int id) {
		BookingDetailObject item = null;
		
		ResultSet rs = this.booking.getBooking(id);
		if(rs != null) {
			try {
				if(rs.next()) {
					item = new BookingDetailObject();
					item.setBookingId(rs.getInt("booking_id"));
					item.setCustomerContact(rs.getString("customer_contact"));
			        item.setRoomId(rs.getInt("room_id"));
			        item.setRoomName(rs.getString("room_name"));
			        item.setBookingState(rs.getInt("booking_state"));
			        item.setBookingComment(rs.getString("booking_comment"));
			        item.setBookingRate(rs.getInt("booking_rate"));
			        item.setBookingStartDate(rs.getDate("booking_start_date")); 
			        item.setBookingEndDate(rs.getDate("booking_end_date")); 
			        item.setBookingPeopleCount(rs.getInt("booking_people_count"));
			        item.setBookingNote(rs.getString("booking_note"));
			        item.setBookingUuid(rs.getString("booking_uuid"));
			        item.setBookingCreatedAt(rs.getTimestamp("booking_created_at"));
			        item.setBookingCreatedAt(rs.getTimestamp("booking_updated_at"));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return item;
	}
	
	
	// arraylist bookingobject là các bản ghi đã phân trang,
	// integer là tổng
	public Pair<ArrayList<BookingDetailObject>, Integer> getBookingDetailObjects(BookingObject similar, short page, byte total) {
		ArrayList<BookingDetailObject> items = new ArrayList<>(); 
		int at = (page - 1) * total;
		ArrayList<ResultSet> res = this.booking.getBookings(similar, at, total);
		ResultSet rs = res.get(0);
		if(rs != null) {
			try {
				while (rs.next()) {
					BookingDetailObject item = new BookingDetailObject();
					item = new BookingDetailObject();
					item.setBookingId(rs.getInt("booking_id"));
					item.setCustomerContact(rs.getString("customer_contact"));
			        item.setRoomId(rs.getInt("room_id"));
			        item.setRoomName(rs.getString("room_name"));
			        item.setBookingState(rs.getInt("booking_state"));
			        item.setBookingComment(rs.getString("booking_comment"));
			        item.setBookingRate(rs.getInt("booking_rate"));
			        item.setBookingStartDate(rs.getDate("booking_start_date")); 
			        item.setBookingEndDate(rs.getDate("booking_end_date")); 
			        item.setBookingPeopleCount(rs.getInt("booking_people_count"));
			        item.setBookingNote(rs.getString("booking_note"));
			        item.setBookingUuid(rs.getString("booking_uuid"));
			        item.setBookingCreatedAt(rs.getTimestamp("booking_created_at"));
			        item.setBookingCreatedAt(rs.getTimestamp("booking_updated_at"));
					items.add(item);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		// lấy tổng số bản ghitrong cdsl
		int all = 0;
		rs = res.get(1);
		if(rs != null) {
			try {
				if(rs.next()) {
					all = rs.getInt("total");
				}
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return new Pair<>(items, all);
	}

	public void releaseConnection() {
		this.booking.releaseConnection();
	}

	public BookingObject getBookingObjectByUuid(String uuid) {
		BookingObject item = null;
		
		ResultSet rs = this.booking.getBookingByUuid(uuid);
		if(rs != null) {
			try {
				if(rs.next()) {
					item = new BookingDetailObject();
					item.setBookingId(rs.getInt("booking_id"));
					item.setCustomerContact(rs.getString("customer_contact"));
			        item.setRoomId(rs.getInt("room_id"));
			        item.setBookingState(rs.getInt("booking_state"));
			        item.setBookingComment(rs.getString("booking_comment"));
			        item.setBookingRate(rs.getInt("booking_rate"));
			        item.setBookingStartDate(rs.getDate("booking_start_date")); 
			        item.setBookingEndDate(rs.getDate("booking_end_date")); 
			        item.setBookingPeopleCount(rs.getInt("booking_people_count"));
			        item.setBookingNote(rs.getString("booking_note"));
			        item.setBookingUuid(rs.getString("booking_uuid"));
			        item.setBookingCreatedAt(rs.getTimestamp("booking_created_at"));
			        item.setBookingCreatedAt(rs.getTimestamp("booking_updated_at"));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return item;
	}

	public boolean editBookingRateAndComment(BookingObject bookingObject) {
		return this.booking.editBookingRateAndComment(bookingObject);
	}
}