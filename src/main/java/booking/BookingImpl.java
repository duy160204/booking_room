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
        sql.append("customer_id, room_id, booking_state, ");
        sql.append("booking_comment, booking_rate, booking_start_date, ");
        sql.append("booking_end_date, booking_people_count, booking_note ");
        sql.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
    	PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, item.getCustomerId());
			stmt.setInt(2, item.getRoomId());
			stmt.setInt(3, item.getBookingState());
			stmt.setString(4, item.getBookingComment());
			stmt.setInt(5, item.getBookingRate());
			stmt.setDate(6, item.getBookingStartDate());
			stmt.setDate(7, item.getBookingEndDate());
			stmt.setInt(8, item.getBookingPeopleCount());
			stmt.setString(9, item.getBookingNote());
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return this.add(stmt);
    }

    @Override
    public boolean editBooking(BookingObject item) {
    	StringBuilder sql = new StringBuilder();
    	sql.append("UPDATE tblbooking SET ");
    	sql.append("customer_id=?, room_id=?, booking_state=?, ");
        sql.append("booking_comment=?, booking_rate=?, booking_start_date=?, ");
        sql.append("booking_end_date=?, booking_people_count=?, booking_note=? ");
        sql.append("WHERE booking_id=?");
        PreparedStatement stmt = null;
        try {
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, item.getCustomerId());
			stmt.setInt(2, item.getRoomId());
			stmt.setInt(3, item.getBookingState());
			stmt.setString(4, item.getBookingComment());
			stmt.setInt(5, item.getBookingRate());
			stmt.setDate(6, item.getBookingStartDate());
			stmt.setDate(7, item.getBookingEndDate());
			stmt.setInt(8, item.getBookingPeopleCount());
			stmt.setString(9, item.getBookingNote());
			stmt.setInt(10, item.getBookingId());
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
        if(multiSelect != null && !"".equalsIgnoreCase(multiSelect)) {
            return this.gets(multiSelect);
        }
        else {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM tblbooking ");
            sql.append("");
            sql.append("ORDER BY booking_id DESC ");
            sql.append("LIMIT 10;");
            return this.gets(sql.toString());
        }
    }

    @Override
    public ResultSet getBooking(int id) {
        String sql = "SELECT * FROM tblbooking WHERE booking_id=?";
        return this.get(sql, id);
    }

//    @Override
//    public ResultSet getBooking(String Bookingname, String Bookingpass) {
//        String sql = "SELCT * FROM tblbooking WHERE (booking_username=?) AND (booking_password=md5(?))";
//        return this.get(sql, Bookingname, Bookingpass);
//    }

    @Override
    public ArrayList<ResultSet> getBookings(BookingObject similar, int at, byte total) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM tblbooking ");
        sql.append("");
        sql.append("ORDER BY booking_id DESC ");
        sql.append("LIMIT ").append(at).append(", ").append(total).append(";");

        sql.append("SELECT COUNT(booking_id) AS total FROM tblbooking");
        return this.gets(sql.toString());
    }

    public static void main(String[] args) {
        Booking u = new BookingImpl();
        
//         thêm
//        Admin a = new AdminImpl();
//        AdminObject adminObject = a.getAdmin("gangplank", "orange");
        
        
//        File file = new File("C:\\Users\\Sweet\\Desktop\\avatar\\a5.jpg");
//        byte[] content = null;
//		try {
//			content = Files.readAllBytes(file.toPath());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
        
        BookingObject item = new BookingObject();
//        item.setBookingName("booking1");
//		item.setAdminId(1);
//		item.setBookingImage(content);
//		item.setBookingSize(20.5);
//		item.setBookingBedCount(3);
//		item.setBookingStarCount(5);
//		item.setBookingPricePerHourVnd(250000);
//		item.setBookingIsAvailable(true);
		item.setBookingNote("phòng cực đẹp");
		
		
        item.setBookingNote("just a note.");
        u.addBooking(item);
        
//        u.editBooking(item);
        
        // sửa
        
        
        

        // list results
//        ArrayList<ResultSet> results = u.getBookings(null, 0, (byte)15);
//
//        
//        ResultSet result = results.get(0);
//        
//        try {
//            while(result.next()) {
//            	StringBuilder row = new StringBuilder();
//                row.append("ID: " + result.getInt("booking_id") + "\t");
//                
//                row.append("Note: " + result.getString("booking_note") + "\t");
//                
//                row.append("CreatedAt: " + result.getString("booking_created_at") + "\t");
//                row.append("UpdatedAt: " + result.getString("booking_updated_at") + "\t");
//                System.out.println(row);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        ResultSet result2 = results.get(1);
//        if(result2 != null) {
//            try {
//                while(result2.next()) {
//                    System.out.println("Tổng số booking: " + result2.getInt("total"));
//                }
//                result2.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
