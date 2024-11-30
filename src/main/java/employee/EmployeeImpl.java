package employee;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import basicUtil.BasicImpl;
import objects.EmployeeObject;

public class EmployeeImpl extends BasicImpl implements Employee {

    public EmployeeImpl() {
        super("Employee");
    }
    
    @Override
    public boolean addEmployee(EmployeeObject item) {
    	StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO tblemployee (");
        sql.append("employee_username, employee_password, employee_fullname, ");
        sql.append("employee_phone, employee_email, employee_address, ");
        sql.append("employee_birthday, employee_gender, employee_note");
        sql.append(") VALUES (?, md5(?), ?, ?, ?, ?, ?, ?, ?)");
    	PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, item.getEmployeeUsername());
			stmt.setString(2, item.getEmployeePassword());
			stmt.setString(3, item.getEmployeeFullname());
			stmt.setString(4, item.getEmployeePhone());
			stmt.setString(5, item.getEmployeeEmail());
			stmt.setString(6, item.getEmployeeAddress());
			stmt.setDate(7, item.getEmployeeBirthday());
			stmt.setString(8, item.getEmployeeGender());
			stmt.setString(9, item.getEmployeeNote());
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return this.add(stmt);
    }

    @Override
    public boolean editEmployee(EmployeeObject item) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE tblemployee SET ");
        sql.append("employee_username=?, employee_password=md5(?), employee_fullname=?, ");
        sql.append("employee_phone=?, employee_email=?, employee_address=?, ");
        sql.append("employee_birthday=?, employee_gender=?, employee_note=? ");
        sql.append("WHERE employee_id=?");
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql.toString());
            stmt.setString(1, item.getEmployeeUsername());
            stmt.setString(2, item.getEmployeePassword());
        	stmt.setString(3, item.getEmployeeFullname());
			stmt.setString(4, item.getEmployeePhone());
			stmt.setString(5, item.getEmployeeEmail());
			stmt.setString(6, item.getEmployeeAddress());
			stmt.setDate(7, item.getEmployeeBirthday());
			stmt.setString(8, item.getEmployeeGender());
			stmt.setString(9, item.getEmployeeNote());
			stmt.setInt(10, item.getEmployeeId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.edit(stmt);
    }

    @Override
    public boolean delEmployee(int id) {
        String sql = "DELETE FROM tblemployee WHERE employee_id = ?";
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
    public ArrayList<ResultSet> getEmployees(String multiSelect) {
        if(multiSelect != null && !"".equalsIgnoreCase(multiSelect)) {
            return this.gets(multiSelect);
        }
        else {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM tblemployee ");
            sql.append("");
            sql.append("ORDER BY employee_id DESC ");
            sql.append("LIMIT 10;");
            return this.gets(sql.toString());
        }
    }

    @Override
    public ResultSet getEmployee(int id) {
        String sql = "SELECT * FROM tblemployee WHERE employee_id=?";
        return this.get(sql, id);
    }

    @Override
    public ResultSet getEmployee(String Employeename, String Employeepass) {
        String sql = "SELECT * FROM tblemployee WHERE (employee_username=?) AND (employee_password=md5(?))";
        return this.get(sql, Employeename, Employeepass);
    }

    @Override
    public ArrayList<ResultSet> getEmployees(EmployeeObject similar, int at, byte total) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM tblemployee ");
        sql.append("");
        sql.append("ORDER BY employee_id DESC ");
        sql.append("LIMIT ").append(at).append(", ").append(total).append(";");

        sql.append("SELECT COUNT(employee_id) AS total FROM tblemployee");
        return this.gets(sql.toString());
    }

    public static void main(String[] args) {
        Employee u = new EmployeeImpl();
        
//         thêm
        EmployeeObject item = new EmployeeObject();
        item.setEmployeeUsername("gangplank");
        item.setEmployeePassword("orange");
        item.setEmployeeBirthday(Date.valueOf("2003-12-31"));
        item.setEmployeeNote("just a note.");
//        u.addEmployee(item);
        
        u.editEmployee(item);
        
        // sửa
        
        
        

        // list results
        ArrayList<ResultSet> results = u.getEmployees(null, 0, (byte)15);

        
        ResultSet result = results.get(0);
        
        try {
            while(result.next()) {
            	StringBuilder row = new StringBuilder();
                row.append("ID: " + result.getInt("employee_id") + "\t");
                row.append("Username: " + result.getString("employee_username") + "\t");
                row.append("Password: " + result.getString("employee_password") + "\t");
                row.append("Note: " + result.getString("employee_note") + "\t");
                row.append("Birthday: " + result.getDate("employee_birthday") + "\t");
                row.append("CreatedAt: " + result.getString("employee_created_at") + "\t");
                row.append("UpdatedAt: " + result.getString("employee_updated_at") + "\t");
                System.out.println(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet result2 = results.get(1);
        if(result2 != null) {
            try {
                while(result2.next()) {
                    System.out.println("Tổng số employee: " + result2.getInt("total"));
                }
                result2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
