package controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.AdminImpl;
import basicUtil.BasicImpl;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	
        boolean flag = authenticate(request);
//        System.out.print(flag);
        if (!flag) {
        	response.sendRedirect(request.getContextPath() + "/login");
            return; 
        }
        StringBuilder sql = new StringBuilder();
        String booking_today = "SELECT COUNT(*) AS booking_today FROM tblbooking WHERE booking_start_date = CURDATE();";
        sql.append(booking_today);
        String booking_thisweek = "SELECT COUNT(*) AS booking_thisweek FROM tblbooking WHERE YEARWEEK(booking_start_date, 1) = YEARWEEK(CURDATE(), 1);";
        sql.append(booking_thisweek);
        String booking_thismonth = "SELECT COUNT(*) AS booking_thismonth FROM tblbooking WHERE YEAR(booking_start_date) = YEAR(CURDATE()) AND MONTH(booking_start_date) = MONTH(CURDATE());";
        sql.append(booking_thismonth);
        
        String revenue_today = "SELECT SUM(r.room_price_per_hour_vnd) AS revenue_today FROM tblbooking b JOIN tblroom r ON b.room_id = r.room_id WHERE DATE(b.booking_start_date) = CURRENT_DATE;";
        sql.append(revenue_today);
        String revenue_thisweek = "SELECT SUM(r.room_price_per_hour_vnd) AS revenue_thisweek FROM tblbooking b JOIN tblroom r ON b.room_id = r.room_id WHERE YEARWEEK(b.booking_start_date, 1) = YEARWEEK(CURRENT_DATE, 1);";
        sql.append(revenue_thisweek);
        String revenue_thismonth = "SELECT SUM(r.room_price_per_hour_vnd) AS revenue_thismonth FROM tblbooking b JOIN tblroom r ON b.room_id = r.room_id WHERE YEAR(b.booking_start_date) = YEAR(CURRENT_DATE) AND MONTH(b.booking_start_date) = MONTH(CURRENT_DATE);";
        sql.append(revenue_thismonth);
        
        String customer_today = "SELECT COUNT(*) AS customer_today FROM tblcustomer WHERE DATE(customer_created_at) = CURRENT_DATE;";
        sql.append(customer_today);
        String customer_thisweek = "SELECT COUNT(*) AS customer_thisweek FROM tblcustomer WHERE YEARWEEK(customer_created_at, 1) = YEARWEEK(CURRENT_DATE, 1);";
        sql.append(customer_thisweek);
        String customer_thismonth = "SELECT COUNT(*) AS customer_thismonth FROM tblcustomer WHERE YEAR(customer_created_at) = YEAR(CURRENT_DATE) AND MONTH(customer_created_at) = MONTH(CURRENT_DATE);";
        sql.append(customer_thismonth);
        
        BasicImpl basicImpl = new BasicImpl("dashboard info");
        List<ResultSet> listResultSet = basicImpl.gets(sql.toString());
        
        ResultSet result;
        
        try {
        	result = listResultSet.get(0); result.next(); request.setAttribute("booking_today", result.getInt("booking_today"));
            result = listResultSet.get(1); result.next(); request.setAttribute("booking_thisweek", result.getInt("booking_thisweek"));
            result = listResultSet.get(2); result.next(); request.setAttribute("booking_thismonth", result.getInt("booking_thismonth"));
            
            result = listResultSet.get(3); result.next(); request.setAttribute("revenue_today", result.getInt("revenue_today"));
            result = listResultSet.get(4); result.next(); request.setAttribute("revenue_thisweek", result.getInt("revenue_thisweek"));
            result = listResultSet.get(5); result.next(); request.setAttribute("revenue_thismonth", result.getInt("revenue_thismonth"));
            
            result = listResultSet.get(6); result.next(); request.setAttribute("customer_today", result.getInt("customer_today"));
            result = listResultSet.get(7); result.next(); request.setAttribute("customer_thisweek", result.getInt("customer_thisweek"));
            result = listResultSet.get(8); result.next(); request.setAttribute("customer_thismonth", result.getInt("customer_thismonth"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        
    }
    
    // TODO: thay bằng cơ chế token
    private boolean authenticate(HttpServletRequest request) {
//    	System.out.print(1);
    	HttpSession session = request.getSession(false);
    	if(session == null) return false;
//    	System.out.print(2);
    	String username = (String)session.getAttribute("username");
    	if(username == null) return false;
//    	System.out.print(3);
    	String password = (String)session.getAttribute("password");
        if(password == null) return false;
//        System.out.print(4);
        AdminImpl admin = new AdminImpl();
        ResultSet resultSet = admin.getAdminByHashPass(username, password);
        if(resultSet == null) return false;
//        System.out.print(5);
        try {
			if(!resultSet.next()) {
//				System.out.print(6);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return true;
    }
}

