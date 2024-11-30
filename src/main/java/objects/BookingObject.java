package objects;

import java.sql.Date;
import java.sql.Timestamp;

public class BookingObject {
    private int bookingId;
    private int customerId;
    private int roomId;
    private int bookingState;
    private String bookingComment;
    private int bookingRate;
    private Date bookingStartDate;
    private Date bookingEndDate;
    private int bookingPeopleCount;
    private String bookingNote;
    private Timestamp bookingCreatedAt;
    private Timestamp bookingUpdatedAt;

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

//    public void setBookingId(int bookingId) {
//        this.bookingId = bookingId;
//    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getBookingState() {
        return bookingState;
    }

    public void setBookingState(int bookingState) {
        this.bookingState = bookingState;
    }

    public String getBookingComment() {
        return bookingComment;
    }

    public void setBookingComment(String bookingComment) {
        this.bookingComment = bookingComment;
    }

    public int getBookingRate() {
        return bookingRate;
    }

    public void setBookingRate(int bookingRate) {
        this.bookingRate = bookingRate;
    }

    public Date getBookingStartDate() {
        return bookingStartDate;
    }

    public void setBookingStartDate(Date bookingStartDate) {
        this.bookingStartDate = bookingStartDate;
    }

    public Date getBookingEndDate() {
        return bookingEndDate;
    }

    public void setBookingEndDate(Date bookingEndDate) {
        this.bookingEndDate = bookingEndDate;
    }

    public int getBookingPeopleCount() {
        return bookingPeopleCount;
    }

    public void setBookingPeopleCount(int bookingPeopleCount) {
        this.bookingPeopleCount = bookingPeopleCount;
    }

    public String getBookingNote() {
        return bookingNote;
    }

    public void setBookingNote(String bookingNote) {
        this.bookingNote = bookingNote;
    }

    public Timestamp getBookingCreatedAt() {
        return bookingCreatedAt;
    }

//    public void setBookingCreatedAt(Timestamp bookingCreatedAt) {
//        this.bookingCreatedAt = bookingCreatedAt;
//    }

    public Timestamp getBookingUpdatedAt() {
        return bookingUpdatedAt;
    }

//    public void setBookingUpdatedAt(Timestamp bookingUpdatedAt) {
//        this.bookingUpdatedAt = bookingUpdatedAt;
//    }
}

