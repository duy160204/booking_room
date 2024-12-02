package controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.Admin;
import admin.AdminImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
        String username = request.getParameter("username");
        if(username == null) {
        	request.setAttribute("error", "username cant be null.");
        	request.getRequestDispatcher("login.jsp").forward(request, response);
        	return;
        }
        String password = request.getParameter("password");
        if(password == null) {
        	request.setAttribute("error", "password cant be null.");
        	request.getRequestDispatcher("login.jsp").forward(request, response);
        	return;
        }
        
        Admin admin = new AdminImpl();
        ResultSet resultSet = admin.getAdmin(username, password);
        if(resultSet == null) {
        	request.setAttribute("error", "failed to read from db.");
        	request.getRequestDispatcher("login.jsp").forward(request, response);
        	return;
        }
        
        Integer id = -1;
        
        try {
        	if(!resultSet.next()) {
        		request.setAttribute("error", "user not found.");
            	request.getRequestDispatcher("login.jsp").forward(request, response);
            	return;
        	}
        	username = resultSet.getString("admin_username");
        	password = resultSet.getString("admin_password");
        	id = resultSet.getInt("admin_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        HttpSession session = request.getSession();
        session.setAttribute("admin_id", id);
        session.setAttribute("admin_username", username);
        session.setAttribute("error", "đăng nhập thành công nhưng t d redirect.");
        response.sendRedirect(request.getContextPath() + "/dashboard");  // Redirect to default controller
    }
}

