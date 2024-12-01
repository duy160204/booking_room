package objects;

import java.sql.Timestamp;

public class RoomObject {
    private int roomId;
    private String roomName;
    private int adminId;
    private byte[] roomImage; // For blob type
    private double roomSize;
    private int roomBedCount;
    private int roomStarCount;
    private double roomPricePerHourVnd;
    private boolean roomIsAvailable;
    private String roomNote;
    private Timestamp roomCreatedAt;
    private Timestamp roomUpdatedAt;

    // Getters and Setters
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public byte[] getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(byte[] roomImage) {
        this.roomImage = roomImage;
    }

    public double getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(double roomSize) {
        this.roomSize = roomSize;
    }

    public int getRoomBedCount() {
        return roomBedCount;
    }

    public void setRoomBedCount(int roomBedCount) {
        this.roomBedCount = roomBedCount;
    }

    public int getRoomStarCount() {
        return roomStarCount;
    }

    public void setRoomStarCount(int roomStarCount) {
        this.roomStarCount = roomStarCount;
    }

    public double getRoomPricePerHourVnd() {
        return roomPricePerHourVnd;
    }

    public void setRoomPricePerHourVnd(double roomPricePerHourVnd) {
        this.roomPricePerHourVnd = roomPricePerHourVnd;
    }

    public boolean isRoomIsAvailable() {
        return roomIsAvailable;
    }

    public void setRoomIsAvailable(boolean roomIsAvailable) {
        this.roomIsAvailable = roomIsAvailable;
    }

    public String getRoomNote() {
        return roomNote;
    }

    public void setRoomNote(String roomNote) {
        this.roomNote = roomNote;
    }

    public Timestamp getRoomCreatedAt() {
        return roomCreatedAt;
    }

//    public void setRoomCreatedAt(Timestamp roomCreatedAt) {
//        this.roomCreatedAt = roomCreatedAt;
//    }

    public Timestamp getRoomUpdatedAt() {
        return roomUpdatedAt;
    }

//    public void setRoomUpdatedAt(Timestamp roomUpdatedAt) {
//        this.roomUpdatedAt = roomUpdatedAt;
//    }
}

