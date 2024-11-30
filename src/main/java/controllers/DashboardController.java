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
        
        BasicImpl basicImpl = new BasicImpl("dashboard info");
        List<ResultSet> listResultSet = basicImpl.gets(sql.toString());
        
        ResultSet result;
        
        try {
        	result = listResultSet.get(0); result.next(); request.setAttribute("booking_today", result.getInt("booking_today"));
            result = listResultSet.get(1); result.next(); request.setAttribute("booking_thisweek", result.getInt("booking_thisweek"));
            result = listResultSet.get(2); result.next(); request.setAttribute("booking_thismonth", result.getInt("booking_thismonth"));
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

