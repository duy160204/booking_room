package customer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import basicUtil.BasicImpl;
import objects.CustomerObject;

public class CustomerImpl extends BasicImpl implements Customer {

    public CustomerImpl() {
        super("Customer");
    }
    
    @Override
    public boolean addCustomer(CustomerObject item) {
    	StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO tblcustomer (");
        sql.append("customer_username, customer_password, customer_fullname, ");
        sql.append("customer_phone, customer_email, customer_address, ");
        sql.append("customer_birthday, customer_gender, customer_note");
        sql.append(") VALUES (?, md5(?), ?, ?, ?, ?, ?, ?, ?)");
    	PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, item.getCustomerUsername());
			stmt.setString(2, item.getCustomerPassword());
			stmt.setString(3, item.getCustomerFullname());
			stmt.setString(4, item.getCustomerPhone());
			stmt.setString(5, item.getCustomerEmail());
			stmt.setString(6, item.getCustomerAddress());
			stmt.setDate(7, item.getCustomerBirthday());
			stmt.setString(8, item.getCustomerGender());
			stmt.setString(9, item.getCustomerNote());
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return this.add(stmt);
    }

    @Override
    public boolean editCustomer(CustomerObject item) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE tblcustomer SET ");
        sql.append("customer_username=?, customer_password=md5(?), customer_fullname=?, ");
        sql.append("customer_phone=?, customer_email=?, customer_address=?, ");
        sql.append("customer_birthday=?, customer_gender=?, customer_note=? ");
        sql.append("WHERE customer_id=?");
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql.toString());
            stmt.setString(1, item.getCustomerUsername());
            stmt.setString(2, item.getCustomerPassword());
        	stmt.setString(3, item.getCustomerFullname());
			stmt.setString(4, item.getCustomerPhone());
			stmt.setString(5, item.getCustomerEmail());
			stmt.setString(6, item.getCustomerAddress());
			stmt.setDate(7, item.getCustomerBirthday());
			stmt.setString(8, item.getCustomerGender());
			stmt.setString(9, item.getCustomerNote());
			stmt.setInt(10, item.getCustomerId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.edit(stmt);
    }

    @Override
    public boolean delCustomer(int id) {
        String sql = "DELETE FROM tblcustomer WHERE customer_id = ?";
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
    public ArrayList<ResultSet> getCustomers(String multiSelect) {
        if(multiSelect != null && !"".equalsIgnoreCase(multiSelect)) {
            return this.gets(multiSelect);
        }
        else {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM tblcustomer ");
            sql.append("");
            sql.append("ORDER BY customer_id DESC ");
            sql.append("LIMIT 10;");
            return this.gets(sql.toString());
        }
    }

    @Override
    public ResultSet getCustomer(int id) {
        String sql = "SELECT * FROM tblcustomer WHERE customer_id=?";
        return this.get(sql, id);
    }

    @Override
    public ResultSet getCustomer(String Customername, String Customerpass) {
        String sql = "SELECT * FROM tblcustomer WHERE (customer_username=?) AND (customer_password=md5(?))";
        return this.get(sql, Customername, Customerpass);
    }

    @Override
    public ArrayList<ResultSet> getCustomers(CustomerObject similar, int at, byte total) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM tblcustomer ");
        sql.append("");
        sql.append("ORDER BY customer_id DESC ");
        sql.append("LIMIT ").append(at).append(", ").append(total).append(";");

        sql.append("SELECT COUNT(customer_id) AS total FROM tblcustomer");
        return this.gets(sql.toString());
    }

    public static void main(String[] args) {
        Customer u = new CustomerImpl();
        
//         thêm
        CustomerObject item = new CustomerObject();
        item.setCustomerUsername("gangplank");
        item.setCustomerPassword("orange");
        item.setCustomerBirthday(Date.valueOf("2003-12-31"));
        item.setCustomerNote("just a note.");
//        u.addCustomer(item);
        
        u.editCustomer(item);
        
        // sửa
        
        
        

        // list results
        ArrayList<ResultSet> results = u.getCustomers(null, 0, (byte)15);

        
        ResultSet result = results.get(0);
        
        try {
            while(result.next()) {
            	StringBuilder row = new StringBuilder();
                row.append("ID: " + result.getInt("customer_id") + "\t");
                row.append("Username: " + result.getString("customer_username") + "\t");
                row.append("Password: " + result.getString("customer_password") + "\t");
                row.append("Note: " + result.getString("customer_note") + "\t");
                row.append("Birthday: " + result.getDate("customer_birthday") + "\t");
                row.append("CreatedAt: " + result.getString("customer_created_at") + "\t");
                row.append("UpdatedAt: " + result.getString("customer_updated_at") + "\t");
                System.out.println(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet result2 = results.get(1);
        if(result2 != null) {
            try {
                while(result2.next()) {
                    System.out.println("Tổng số customer: " + result2.getInt("total"));
                }
                result2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
