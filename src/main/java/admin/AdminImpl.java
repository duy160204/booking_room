package admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import basicUtil.BasicImpl;
import objects.AdminObject;

public class AdminImpl extends BasicImpl implements Admin {

    public AdminImpl() {
        super("Admin");
    }
    
    @Override
    public boolean addAdmin(AdminObject item) {
    	String sql = "INSERT INTO tbladmin (admin_username, admin_password, admin_note) values (?, md5(?), ?)";
        PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, item.getAdminUsername());
			stmt.setString(2, item.getAdminPassword());
			stmt.setString(3, item.getAdminNote());
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return this.add(stmt);
    }

    @Override
    public boolean editAdmin(AdminObject item) {
        String sql = "UPDATE tbladmin SET admin_username = ?, admin_password = md5(?), admin_note = ? WHERE admin_id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, item.getAdminUsername());
            stmt.setString(2, item.getAdminPassword());
            stmt.setString(3, item.getAdminNote());
            stmt.setInt(4, item.getAdminId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.edit(stmt);
    }

    @Override
    public boolean delAdmin(int id) {
        String sql = "DELETE FROM tbladmin WHERE admin_id = ?";
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
    public ArrayList<ResultSet> getAdmins(String multiSelect) {
        if(multiSelect != null && !"".equalsIgnoreCase(multiSelect)) {
            return this.gets(multiSelect);
        }
        else {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM tbladmin ");
            sql.append("");
            sql.append("ORDER BY admin_id DESC ");
            sql.append("LIMIT 10;");
            return this.gets(sql.toString());
        }
    }

    @Override
    public ResultSet getAdmin(int id) {
        String sql = "SELECT * FROM tbladmin WHERE admin_id=?";
        return this.get(sql, id);
    }

    @Override
    public ResultSet getAdmin(String Adminname, String Adminpass) {
        String sql = "SELECT * FROM tbladmin WHERE (admin_username=?) AND (admin_password=md5(?))";
        return this.get(sql, Adminname, Adminpass);
    }
    
    public ResultSet getAdminByHashPass(String Adminname, String Adminpass) {
        String sql = "SELECT * FROM tbladmin WHERE (admin_username=?) AND (admin_password=?)";
        return this.get(sql, Adminname, Adminpass);
    }

    @Override
    public ArrayList<ResultSet> getAdmins(AdminObject similar, int at, byte total) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM tbladmin ");
        sql.append("");
        sql.append("ORDER BY admin_id DESC ");
        sql.append("LIMIT ").append(at).append(", ").append(total).append(";");

        sql.append("SELECT COUNT(admin_id) AS total FROM tbladmin");
        return this.gets(sql.toString());
    }
    
//    public boolean isExisting(AdminObject item) {
//    	boolean flag = false;
//    	String sql = "SELECT admin_id FROM tbluser WHERE admin_name='" + item.getAdminUsername() + "' ";
//    	ResultSet resultSet = this.get(sql, 0); // 0 means the sql does not have any paramater.
//    	if(resultSet != null) {
//    		try {
//				if(resultSet.next()) { // Operation not allowed after ResultSet closed
//					flag = true;
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//    	}
//    	return flag;
//    }

    public static void main(String[] args) {
        Admin u = new AdminImpl();
        
        // thêm
//        AdminObject item = new AdminObject();
//        item.setAdminUsername("admin");
//        item.setAdminPassword("admin");
//        item.setAdminNote("just a note.");
//        u.addAdmin(item);
        
        // sửa
        
        u.delAdmin(2);
        

        // list results
        ArrayList<ResultSet> results = u.getAdmins(null, 0, (byte)15);

        
        ResultSet result = results.get(0);
        
        try {
            while(result.next()) {
            	StringBuilder row = new StringBuilder();
                row.append("ID: " + result.getInt("admin_id") + "\t");
                row.append("Username: " + result.getString("admin_username") + "\t");
                row.append("Password: " + result.getString("admin_password") + "\t");
                row.append("Note: " + result.getString("admin_note") + "\t");
                row.append("CreatedAt: " + result.getString("admin_created_at") + "\t");
                row.append("UpdatedAt: " + result.getString("admin_updated_at") + "\t");
                System.out.println(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet result2 = results.get(1);
        if(result2 != null) {
            try {
                while(result2.next()) {
                    System.out.println("Tổng số admin: " + result2.getInt("total"));
                }
                result2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
