package room;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import basicUtil.BasicImpl;
import objects.RoomObject;

public class RoomImpl extends BasicImpl implements Room {

    public RoomImpl() {
        super("Room");
    }
    
    @Override
    public boolean addRoom(RoomObject item) {
    	StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO tblroom (");
        sql.append("room_name, admin_id, room_image, ");
        sql.append("room_size, room_bed_count, room_star_count, ");
        sql.append("room_price_per_hour_vnd, room_is_available, room_note");
        sql.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
    	PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, item.getRoomName());
			stmt.setInt(2, item.getAdminId());
			stmt.setBytes(3, item.getRoomImage());
			stmt.setDouble(4, item.getRoomSize());
			stmt.setInt(5, item.getRoomBedCount());
			stmt.setInt(6, item.getRoomStarCount());
			stmt.setDouble(7, item.getRoomPricePerHourVnd());
			stmt.setBoolean(8, item.isRoomIsAvailable());
			stmt.setString(9, item.getRoomNote());
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return this.add(stmt);
    }

    @Override
    public boolean editRoom(RoomObject item) {
    	StringBuilder sql = new StringBuilder();
    	sql.append("UPDATE tblroom SET ");
        sql.append("room_name=?, admin_id=?, room_image=?, ");
        sql.append("room_size=?, room_bed_count=?, room_star_count=?, ");
        sql.append("room_price_per_hour_vnd=?, room_is_available=?, room_note=? ");
        sql.append("WHERE room_id=?");
        PreparedStatement stmt = null;
        try {
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, item.getRoomName());
			stmt.setInt(2, item.getAdminId());
			stmt.setBytes(3, item.getRoomImage());
			stmt.setDouble(4, item.getRoomSize());
			stmt.setInt(5, item.getRoomBedCount());
			stmt.setInt(6, item.getRoomStarCount());
			stmt.setDouble(7, item.getRoomPricePerHourVnd());
			stmt.setBoolean(8, item.isRoomIsAvailable());
			stmt.setString(9, item.getRoomNote());
			stmt.setInt(10, item.getRoomId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return this.edit(stmt);
    }

    @Override
    public boolean delRoom(int id) {
        String sql = "DELETE FROM tblroom WHERE room_id = ?";
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
    public ArrayList<ResultSet> getRooms(String multiSelect) {
        if(multiSelect != null && !"".equalsIgnoreCase(multiSelect)) {
            return this.gets(multiSelect);
        }
        else {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM tblroom ");
            sql.append("");
            sql.append("ORDER BY room_id DESC ");
            sql.append("LIMIT 10;");
            return this.gets(sql.toString());
        }
    }

    @Override
    public ResultSet getRoom(int id) {
        String sql = "SELECT * FROM tblroom WHERE room_id=?";
        return this.get(sql, id);
    }

//    @Override
//    public ResultSet getRoom(String Roomname, String Roompass) {
//        String sql = "SELCT * FROM tblroom WHERE (room_username=?) AND (room_password=md5(?))";
//        return this.get(sql, Roomname, Roompass);
//    }

    @Override
    public ArrayList<ResultSet> getRooms(RoomObject similar, int at, byte total) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM tblroom ");
        sql.append("");
        sql.append("ORDER BY room_id DESC ");
        sql.append("LIMIT ").append(at).append(", ").append(total).append(";");

        sql.append("SELECT COUNT(room_id) AS total FROM tblroom");
        return this.gets(sql.toString());
    }

    public static void main(String[] args) {
        Room u = new RoomImpl();
        
//         thêm
//        Admin a = new AdminImpl();
//        AdminObject adminObject = a.getAdmin("gangplank", "orange");
        
        
        File file = new File("C:\\Users\\Sweet\\Desktop\\avatar\\a5.jpg");
        byte[] content = null;
		try {
			content = Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        RoomObject item = new RoomObject();
        item.setRoomName("room1");
		item.setAdminId(1);
		item.setRoomImage(content);
		item.setRoomSize(20.5);
		item.setRoomBedCount(3);
		item.setRoomStarCount(5);
		item.setRoomPricePerHourVnd(250000);
		item.setRoomIsAvailable(true);
		item.setRoomNote("phòng cực đẹp");
		
		
        item.setRoomNote("just a note.");
        u.addRoom(item);
        
//        u.editRoom(item);
        
        // sửa
        
        
        

        // list results
//        ArrayList<ResultSet> results = u.getRooms(null, 0, (byte)15);
//
//        
//        ResultSet result = results.get(0);
//        
//        try {
//            while(result.next()) {
//            	StringBuilder row = new StringBuilder();
//                row.append("ID: " + result.getInt("room_id") + "\t");
//                
//                row.append("Note: " + result.getString("room_note") + "\t");
//                
//                row.append("CreatedAt: " + result.getString("room_created_at") + "\t");
//                row.append("UpdatedAt: " + result.getString("room_updated_at") + "\t");
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
//                    System.out.println("Tổng số room: " + result2.getInt("total"));
//                }
//                result2.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
