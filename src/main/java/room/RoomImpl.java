package room;

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
        sql.append("room_name, room_image, ");
        sql.append("room_size, room_bed_count, room_star_count, ");
        sql.append("room_price_per_hour_vnd, room_is_available, room_note");
        sql.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
    	PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, item.getRoomName());
			stmt.setBytes(2, item.getRoomImage());
			stmt.setDouble(3, item.getRoomSize());
			stmt.setInt(4, item.getRoomBedCount());
			stmt.setInt(5, item.getRoomStarCount());
			stmt.setDouble(6, item.getRoomPricePerHourVnd());
			stmt.setBoolean(7, item.isRoomIsAvailable());
			stmt.setString(8, item.getRoomNote());
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return this.add(stmt);
    }

    @Override
    public boolean editRoom(RoomObject item) {
    	StringBuilder sql = new StringBuilder();
    	sql.append("UPDATE tblroom SET ");
        sql.append("room_name=?, room_image=?, ");
        sql.append("room_size=?, room_bed_count=?, room_star_count=?, ");
        sql.append("room_price_per_hour_vnd=?, room_is_available=?, room_note=? ");
        sql.append("WHERE room_id=?");
        PreparedStatement stmt = null;
        try {
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, item.getRoomName());
			stmt.setBytes(2, item.getRoomImage());
			stmt.setDouble(3, item.getRoomSize());
			stmt.setInt(4, item.getRoomBedCount());
			stmt.setInt(5, item.getRoomStarCount());
			stmt.setDouble(6, item.getRoomPricePerHourVnd());
			stmt.setBoolean(7, item.isRoomIsAvailable());
			stmt.setString(8, item.getRoomNote());
			stmt.setInt(9, item.getRoomId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return this.edit(stmt);
    }
    
    public boolean editRoomWithoutImage(RoomObject item) {
    	StringBuilder sql = new StringBuilder();
    	sql.append("UPDATE tblroom SET ");
        sql.append("room_name=?, ");
        sql.append("room_size=?, room_bed_count=?, room_star_count=?, ");
        sql.append("room_price_per_hour_vnd=?, room_is_available=?, room_note=? ");
        sql.append("WHERE room_id=?");
        PreparedStatement stmt = null;
        try {
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, item.getRoomName());
			stmt.setDouble(2, item.getRoomSize());
			stmt.setInt(3, item.getRoomBedCount());
			stmt.setInt(4, item.getRoomStarCount());
			stmt.setDouble(5, item.getRoomPricePerHourVnd());
			stmt.setBoolean(6, item.isRoomIsAvailable());
			stmt.setString(7, item.getRoomNote());
			stmt.setInt(8, item.getRoomId());
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
}
