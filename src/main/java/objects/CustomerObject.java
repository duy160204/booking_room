package objects;

import java.sql.Date;
import java.sql.Timestamp;

public class CustomerObject {
    private int customerId;
    private String customerUsername;
    private String customerPassword;
    private String customerFullname;
    private String customerPhone;
    private String customerEmail;
    private String customerAddress;
    private Date customerBirthday;
    private String customerGender;
    private String customerNote;
    private Timestamp customerCreatedAt;
    private Timestamp customerUpdatedAt;

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

//    public void setCustomerId(int customerId) {
//        this.customerId = customerId;
//    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerFullname() {
        return customerFullname;
    }

    public void setCustomerFullname(String customerFullname) {
        this.customerFullname = customerFullname;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Date getCustomerBirthday() {
        return customerBirthday;
    }

    public void setCustomerBirthday(Date customerBirthday) {
        this.customerBirthday = customerBirthday;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    public Timestamp getCustomerCreatedAt() {
        return customerCreatedAt;
    }

//    public void setCustomerCreatedAt(Timestamp customerCreatedAt) {
//        this.customerCreatedAt = customerCreatedAt;
//    }

    public Timestamp getCustomerUpdatedAt() {
        return customerUpdatedAt;
    }

//    public void setCustomerUpdatedAt(Timestamp customerUpdatedAt) {
//        this.customerUpdatedAt = customerUpdatedAt;
//    }
}


