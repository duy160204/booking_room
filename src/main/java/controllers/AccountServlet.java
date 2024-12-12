package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.UserObject;
import java.sql.Date;
import user.UserModel;

@WebServlet("/accountsdfasdfasdf")
public class AccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserModel userModel;

    @Override
    public void init() throws ServletException {
        super.init();
        userModel = new UserModel(); // Assuming UserModel is initialized properly
    }

    // Handle login, register, and logout actions
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            sendJsonResponse(response, HttpServletResponse.SC_BAD_REQUEST, "{\"message\": \"Action required.\"}");
            return;
        }

        switch (action) {
            case "register":
                registerUser(request, response);
                break;
            case "login":
                loginUser(request, response);
                break;
            case "logout":
                logoutUser(request, response);
                break;
            default:
                sendJsonResponse(response, HttpServletResponse.SC_BAD_REQUEST, "{\"message\": \"Action not found.\"}");
        }
    }

    // Register user (similar to before)
    private void registerUser(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Retrieve required parameters
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // Validate required parameters
            if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                sendJsonResponse(response, HttpServletResponse.SC_BAD_REQUEST, "{\"message\": \"Username and password are required.\"}"); 
                return;
            }

            // Retrieve optional parameters
            String fullname = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String gender = request.getParameter("gender");
            String note = request.getParameter("note");
            int role = 0;
            try {
                role = Integer.parseInt(request.getParameter("role"));
            } catch (NumberFormatException e) {
            	role = 0;
            }

            Date birthday = null;
            try {
                String birthdayStr = request.getParameter("birthday");
                if (birthdayStr != null && !birthdayStr.isEmpty()) {
                    birthday = Date.valueOf(birthdayStr);
                }
            } catch (IllegalArgumentException e) {
                sendJsonResponse(response, HttpServletResponse.SC_BAD_REQUEST,"{\"message\": \"Invalid birthday format.\"}");
                return;
            }

            // Check if username already exists
            if (userModel.isUsernameExists(username)) {
                sendJsonResponse(response, HttpServletResponse.SC_CONFLICT, "Username already exists");
                return;
            }

            // Create and populate a new UserObject
            UserObject newUser = new UserObject();
            newUser.setUserUsername(username);
            newUser.setUserPassword(password);
            newUser.setUserFullname(fullname);
            newUser.setUserPhone(phone);
            newUser.setUserEmail(email);
            newUser.setUserAddress(address);
            newUser.setUserGender(gender);
            newUser.setUserNote(note);
            newUser.setUserRole(role);
            newUser.setUserBirthday(birthday);

            // Attempt to register the user
            boolean isRegistered = userModel.addUser(newUser);
            if (isRegistered) {
                sendJsonResponse(response, HttpServletResponse.SC_OK, "{\"message\": \"User registered successfully.\"}");
            } else {
                sendJsonResponse(response, HttpServletResponse.SC_CONFLICT, "{\"message\": \"Register failed.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            sendJsonResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error when register.");
        }
    }

    // Login user and set session
    private void loginUser(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                sendJsonResponse(response, HttpServletResponse.SC_BAD_REQUEST, "{\"message\": \"Username and password are required.\"}"); 
                return;
            }

            // Fetch the user from the database based on username and password
            UserObject user = userModel.getUserObject(username, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("username", user.getUserUsername());
                session.setAttribute("role", user.getUserRole());

                sendJsonResponse(response, HttpServletResponse.SC_OK, "{\"message\": \"User logined successfully.\"}");
            } else {
                sendJsonResponse(response, HttpServletResponse.SC_CONFLICT, "{\"message\": \"Login failed.\"}");
            }
        } catch (Exception e) {
        	e.printStackTrace();
            sendJsonResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error when login.");
        }
    }

    // Logout user by invalidating the session
    private void logoutUser(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            sendJsonResponse(response, HttpServletResponse.SC_OK, "{\"message\": \"User logout successfully.\"}");
        } catch (Exception e) {
        	e.printStackTrace();
            sendJsonResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error when logout.");
        }
    }

//    // Handle GET requests to check if the user is logged in and return user details
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
//        throws ServletException, IOException {
//        try {
//            HttpSession session = request.getSession(false);
//            if (session != null && session.getAttribute("userId") != null) {
//                int userId = (int) session.getAttribute("userId");
//                UserObject user = userModel.getUserObject(userId);
//
//                sendResponse(response, HttpServletResponse.SC_OK, String.format(
//                    "{\"userId\": %d, \"username\": \"%s\", \"role\": %d}",
//                    user.getUserId(), user.getUserUsername(), user.getUserRole()));
//            } else {
//                sendResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Chưa đăng nhập.");
//            }
//        } catch (Exception e) {
//        	e.printStackTrace();
//            sendResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi nội bộ khi lấy thông tin.");
//        }
//    }

    // Utility method to send a JSON error response with exception details
    private void sendJsonResponse(HttpServletResponse response, int statusCode, String message) throws IOException {
        response.setContentType("application/json");
        response.setStatus(statusCode);
        PrintWriter out = response.getWriter();
        out.print(message);
        out.flush();
    }
}
