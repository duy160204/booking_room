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

    // Thêm đặt phòng
    public boolean addBooking(BookingObject item) {
        return this.booking.addBooking(item);
    }

    // Chỉnh sửa đặt phòng
    public boolean editBooking(BookingObject item) {
        return this.booking.editBooking(item);
    }

    // Xác nhận đặt phòng
    public boolean setAccept(int id) {
        return this.booking.editState(id, 1);
    }

    // Từ chối đặt phòng
    public boolean setReject(int id) {
        return this.booking.editState(id, -1);
    }

    // Xóa đặt phòng
    public boolean delBooking(int id) {
        return this.booking.delBooking(id);
    }

    // Lấy chi tiết booking theo ID
    public BookingDetailObject getBookingObject(int id) {
        ResultSet rs = this.booking.getBooking(id);
        return extractBookingDetail(rs);
    }

    // Lấy chi tiết bookings theo các tham số lọc
    public Pair<ArrayList<BookingDetailObject>, Integer> getBookingDetailObjects(BookingObject similar, short page, byte total) {
        ArrayList<BookingDetailObject> items = new ArrayList<>();
        int at = (page - 1) * total;
        ArrayList<ResultSet> res = this.booking.getBookings(similar, at, total);
        
        ResultSet rs = res.get(0);
        if (rs != null) {
            try {
                while (rs.next()) {
                    items.add(extractBookingDetail(rs));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        int totalRecords = 0;
        rs = res.get(1);
        if (rs != null) {
            try {
                if (rs.next()) {
                    totalRecords = rs.getInt("total");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new Pair<>(items, totalRecords);
    }

    // Giải phóng kết nối
    public void releaseConnection() {
        this.booking.releaseConnection();
    }

    // Lấy booking theo UUID
    public BookingObject getBookingObjectByUuid(String uuid) {
        ResultSet rs = this.booking.getBookingByUuid(uuid);
        return extractBookingDetail(rs);
    }

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/khach_san";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "123456";

    public BookingObject getBookingObjectByUuid2(String uuid) {
        BookingObject item = null;
        String sql = "SELECT * FROM tblbooking WHERE booking_uuid = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, uuid);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                item = new BookingDetailObject();
                item.setBookingId(rs.getInt("booking_id"));
                item.setCustomerContact(rs.getString("customer_contact"));
                item.setCustomerContact1(rs.getString("customer_contact1"));
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
                item.setBookingUpdatedAt(rs.getTimestamp("booking_updated_at")); // Sửa lỗi ghi đè
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return item;
    }


    public ArrayList<BookingObject> getBookingObjectsByContact(String uuid) {
        ArrayList<BookingObject> items = new ArrayList<>();
        String sql = "SELECT * FROM tblbooking WHERE customer_contact = ? OR customer_contact1 = ?";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            // Gán UUID vào cả 2 tham số
            stmt.setString(1, uuid);
            stmt.setString(2, uuid);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                BookingObject item = new BookingDetailObject();
                item.setBookingId(rs.getInt("booking_id"));
                item.setCustomerContact(rs.getString("customer_contact"));
                item.setCustomerContact1(rs.getString("customer_contact1"));
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
                item.setBookingUpdatedAt(rs.getTimestamp("booking_updated_at"));
                
                // Add item to the list
                items.add(item);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return items;
    }
    public ArrayList<BookingObject> getBookingObjectsByContact(String uuid, int page, int pageSize) {
        ArrayList<BookingObject> allItems = new ArrayList<>();
        String sql = "SELECT * FROM tblbooking WHERE customer_contact = ? OR customer_contact1 = ?";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, uuid);
            stmt.setString(2, uuid);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                BookingObject item = new BookingDetailObject();
                item.setBookingId(rs.getInt("booking_id"));
                item.setCustomerContact(rs.getString("customer_contact"));
                item.setCustomerContact1(rs.getString("customer_contact1"));
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
                item.setBookingUpdatedAt(rs.getTimestamp("booking_updated_at"));

                allItems.add(item);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Phân trang
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, allItems.size());

        if (startIndex >= allItems.size()) {
            return new ArrayList<>(); // Không còn dữ liệu
        }

        return new ArrayList<>(allItems.subList(startIndex, endIndex));
    }

    // Chỉnh sửa thông tin đánh giá và nhận xét
    public boolean editBookingRateAndComment(BookingObject bookingObject) {
        return this.booking.editBookingRateAndComment(bookingObject);
    }

    // Phương thức dùng chung để tách dữ liệu từ ResultSet thành BookingDetailObject
    private BookingDetailObject extractBookingDetail(ResultSet rs) {
        BookingDetailObject item = null;
        try {
            if (rs != null && rs.next()) {
                item = new BookingDetailObject();
                item.setBookingId(rs.getInt("booking_id"));
                item.setCustomerContact(rs.getString("customer_contact"));
                item.setCustomerContact1(rs.getString("customer_contact1"));
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
                item.setBookingUpdatedAt(rs.getTimestamp("booking_updated_at"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return item;
    }
}
