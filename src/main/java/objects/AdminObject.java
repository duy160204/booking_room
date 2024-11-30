package objects;

import java.sql.Timestamp;

public class AdminObject {
    private int adminId;
    private String adminUsername;
    private String adminPassword;
    private String adminNote;
    private Timestamp adminCreatedAt;
    private Timestamp adminUpdatedAt;

    // Getters and Setters
    public int getAdminId() {
        return adminId;
    }

//    public void setAdminId(int adminId) {
//        this.adminId = adminId;
//    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminNote() {
        return adminNote;
    }

    public void setAdminNote(String adminNote) {
        this.adminNote = adminNote;
    }

    public Timestamp getAdminCreatedAt() {
        return adminCreatedAt;
    }

//    public void setAdminCreatedAt(Timestamp adminCreatedAt) {
//        this.adminCreatedAt = adminCreatedAt;
//    }

    public Timestamp getAdminUpdatedAt() {
        return adminUpdatedAt;
    }

//    public void setAdminUpdatedAt(Timestamp adminUpdatedAt) {
//        this.adminUpdatedAt = adminUpdatedAt;
//    }
}

