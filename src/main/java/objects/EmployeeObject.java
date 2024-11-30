package objects;

import java.sql.Timestamp;
import java.sql.Date;

public class EmployeeObject {
    private int employeeId;
    private String employeeUsername;
    private String employeePassword;
    private String employeeFullname;
    private String employeePhone;
    private String employeeEmail;
    private String employeeAddress;
    private Date employeeBirthday;
    private String employeeGender;
    private String employeeNote;
    private Timestamp employeeCreatedAt;
    private Timestamp employeeUpdatedAt;

    // Getters and Setters
    public int getEmployeeId() {
        return employeeId;
    }

//    public void setEmployeeId(int employeeId) {
//        this.employeeId = employeeId;
//    }

    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public void setEmployeeUsername(String employeeUsername) {
        this.employeeUsername = employeeUsername;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public String getEmployeeFullname() {
        return employeeFullname;
    }

    public void setEmployeeFullname(String employeeFullname) {
        this.employeeFullname = employeeFullname;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public Date getEmployeeBirthday() {
        return employeeBirthday;
    }

    public void setEmployeeBirthday(Date employeeBirthday) {
        this.employeeBirthday = employeeBirthday;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public String getEmployeeNote() {
        return employeeNote;
    }

    public void setEmployeeNote(String employeeNote) {
        this.employeeNote = employeeNote;
    }

    public Timestamp getEmployeeCreatedAt() {
        return employeeCreatedAt;
    }

//    public void setEmployeeCreatedAt(Timestamp employeeCreatedAt) {
//        this.employeeCreatedAt = employeeCreatedAt;
//    }

    public Timestamp getEmployeeUpdatedAt() {
        return employeeUpdatedAt;
    }

//    public void setEmployeeUpdatedAt(Timestamp employeeUpdatedAt) {
//        this.employeeUpdatedAt = employeeUpdatedAt;
//    }
}
