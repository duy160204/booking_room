package booking;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import basicUtil.BasicImpl;
import objects.BookingObject;

public class BookingImpl extends BasicImpl implements Booking {

    public BookingImpl() {
        super("Booking");
    }
    
    @Override
    public boolean addBooking(BookingObject item) {
    	StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO tblbooking (");
        sql.append("room_id, booking_state, ");
        sql.append("booking_comment, booking_rate, booking_start_date, ");
        sql.append("booking_end_date, booking_people_count, booking_note, ");
        sql.append("booking_uuid, customer_contact ");
        sql.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        System.out.println(sql.toString());
    	PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, item.getRoomId());
			stmt.setInt(2, item.getBookingState());
			stmt.setString(3, item.getBookingComment());
			stmt.setInt(4, item.getBookingRate());
			stmt.setDate(5, item.getBookingStartDate());
			stmt.setDate(6, item.getBookingEndDate());
			stmt.setInt(7, item.getBookingPeopleCount());
			stmt.setString(8, item.getBookingNote());
			stmt.setString(9, item.getBookingUuid());
			stmt.setString(10, item.getCustomerContact());
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return this.add(stmt);
    }

    @Override
    public boolean editBooking(BookingObject item) {
    	StringBuilder sql = new StringBuilder();
    	sql.append("UPDATE tblbooking SET ");
    	sql.append("room_id=?, booking_state=?, ");
        sql.append("booking_comment=?, booking_rate=?, booking_start_date=?, ");
        sql.append("booking_end_date=?, booking_people_count=?, booking_note=? ");
        sql.append("booking_uuid=?, customer_contact=? ");
        sql.append("WHERE booking_id=?");
        PreparedStatement stmt = null;
        try {
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, item.getRoomId());
			stmt.setInt(2, item.getBookingState());
			stmt.setString(3, item.getBookingComment());
			stmt.setInt(4, item.getBookingRate());
			stmt.setDate(5, item.getBookingStartDate());
			stmt.setDate(6, item.getBookingEndDate());
			stmt.setInt(7, item.getBookingPeopleCount());
			stmt.setString(8, item.getBookingNote());
			stmt.setString(9, item.getBookingUuid());
			stmt.setString(10, item.getCustomerContact());
			stmt.setInt(11, item.getBookingId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return this.edit(stmt);
    }
    
    public boolean editBookingRateAndComment(BookingObject item) {
    	StringBuilder sql = new StringBuilder();
    	sql.append("UPDATE tblbooking SET ");
        sql.append("booking_comment=?, booking_rate=? ");
        sql.append("WHERE booking_uuid=?");
        PreparedStatement stmt = null;
        try {
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, item.getBookingComment());
			stmt.setInt(2, item.getBookingRate());
			stmt.setString(3,  item.getBookingUuid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return this.edit(stmt);
    }
    
    public boolean editState(int id, int state) {
    	StringBuilder sql = new StringBuilder();
    	sql.append("UPDATE tblbooking SET ");
    	sql.append("booking_state=? ");
        sql.append("WHERE booking_id=?");
        PreparedStatement stmt = null;
        try {
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, state);
			stmt.setInt(2, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return this.edit(stmt);
    }

    @Override
    public boolean delBooking(int id) {
        String sql = "DELETE FROM tblbooking WHERE booking_id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.del(stmt);
    }

    @Override
    public ArrayList<ResultSet> getBookings(String multiSelect) {
        if (multiSelect != null && !"".equalsIgnoreCase(multiSelect)) {
            return this.gets(multiSelect);
        } else {
            StringBuilder sql = new StringBuilder();

            // Main SELECT query with JOINs
            sql.append("SELECT ")
               .append("b.*, ")  // All columns from tblbooking
//               .append("c.customer_fullname, ")  // Add customer's full name
               .append("r.room_name ")  // Add room's name
               .append("FROM tblbooking b ")
//               .append("INNER JOIN tblcustomer c ON b.customer_id = c.customer_id ")
               .append("INNER JOIN tblroom r ON b.room_id = r.room_id ")
               .append("ORDER BY b.booking_id DESC ")
               .append("LIMIT 10;");

            return this.gets(sql.toString());
        }
    }


    @Override
    public ResultSet getBooking(int id) {
//    	String sql = """
//                SELECT 
//    			    b.*,
//    			    c.customer_fullname,
//    			    r.room_name
//    			FROM 
//    			    tblbooking b
//    			INNER JOIN tblcustomer c ON b.customer_id = c.customer_id
//    			INNER JOIN 
//    			    tblroom r ON b.room_id = r.room_id
//    			WHERE
//            		booking_id=?
//                """;
    	StringBuilder sql = new StringBuilder();
    	sql.append("SELECT ")
    		.append("b.*, ")
    		.append("r.room_name ")
    		.append("FROM tblbooking b ")
    		.append("INNER JOIN tblroom ON b.room_id = r.room_id ")
    		.append("WHERE booking_id=? ");
        return this.get(sql.toString(), id);
    }

    @Override
    public ArrayList<ResultSet> getBookings(BookingObject similar, int at, byte total) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
           .append("b.*, ")  // All columns from tblbooking
           .append("r.room_name ")  // Add room's name
           .append("FROM tblbooking b ")
           .append("INNER JOIN tblroom r ON b.room_id = r.room_id ")
           .append("ORDER BY b.booking_id DESC ")
           .append("LIMIT ").append(at).append(", ").append(total).append(";");

        // Second query for counting total rows
        sql.append("SELECT COUNT(b.booking_id) AS total FROM tblbooking b;");

        return this.gets(sql.toString());
    }

	public ResultSet getBookingByUuid(String uuid) {
		String sql = "SELECT * FROM tblbooking where booking_uuid=?";
        return this.get(sql.toString(), uuid);
	}
}
