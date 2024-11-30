package admin;

import java.sql.ResultSet;

import objects.AdminObject;

public class AdminModel {
	private Admin admin;
	
	public AdminModel() {
		this.admin = new AdminImpl();
	}
	
	public boolean addAdmin(AdminObject item) {
		return this.admin.addAdmin(item);
	}

	public boolean editAdmin(AdminObject item) {
		return this.admin.editAdmin(item);
	}

	public boolean delAdmin(int id) {
		return this.admin.delAdmin(id);
	}
	
//	public AdminObject getAdmin(String username, String userpassword) {
//		ResultSet temp = this.admin.getAdmin(username, userpassword);
//		try {
//	        if (!temp.next()) return null;
//	        
//            // Retrieve the username and password from the result set
//            String dbUsername = temp.getString("admin_username");  // column name as in the database
//            String dbPassword = temp.getString("admin_password");  // column name as in the database
//            
//            // Optionally, you can now create and return an AdminObject
//            AdminObject adminObject = new AdminObject();
//            adminObject.setAdminUsername(dbUsername);
//            adminObject.setAdminPassword(dbPassword);
//            return admin;
//        
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	        return null;
//	    }
//	}
}
