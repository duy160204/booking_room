package api;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.javatuples.Pair;

import objects.UserObject;
import user.UserModel;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet("/api/user")
public class UserServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private UserModel userModel;

    @Override
    public void init() throws ServletException {
        userModel = new UserModel(); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "add":
                    addUser(request, response);
                    break;
                case "edit":
                    editUser(request, response);
                    break;
                case "editWithoutPassword":
                    editUserWithoutPassword(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "getUser":
                    getUser(request, response);
                    break;
                case "getUsers":
                    getUsers(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
            }
        }
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        try {
            UserObject item = new UserObject();
            item.setUserUsername(request.getParameter("username"));
            item.setUserPassword(request.getParameter("password"));
            item.setUserFullname(request.getParameter("fullname"));
            item.setUserPhone(request.getParameter("phone"));
            item.setUserEmail(request.getParameter("email"));
            item.setUserAddress(request.getParameter("address"));
            item.setUserBirthday(Date.valueOf(request.getParameter("birthday")));
            item.setUserGender(request.getParameter("gender"));
            item.setUserNote(request.getParameter("note"));
            item.setUserRole(Integer.parseInt(request.getParameter("role")));

            boolean success = userModel.addUser(item);

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print("{\"success\": " + success + "}");
            out.flush();
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding user");
        }
    }

    private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	int userId = Integer.parseInt(request.getParameter("userId"));
            UserObject item = new UserObject();
            item.setUserId(userId);
            item.setUserUsername(request.getParameter("username"));
            item.setUserPassword(request.getParameter("password"));
            item.setUserFullname(request.getParameter("fullname"));
            item.setUserPhone(request.getParameter("phone"));
            item.setUserEmail(request.getParameter("email"));
            item.setUserAddress(request.getParameter("address"));
            item.setUserBirthday(Date.valueOf(request.getParameter("birthday")));
            item.setUserGender(request.getParameter("gender"));
            item.setUserNote(request.getParameter("note"));
            item.setUserRole(Integer.parseInt(request.getParameter("role")));

            boolean success = userModel.editUser(item);

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print("{\"success\": " + success + "}");
            out.flush();
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error editing user");
        }
    }

    private void editUserWithoutPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            UserObject item = new UserObject();
            item.setUserId(userId);
            item.setUserUsername(request.getParameter("username"));
            item.setUserFullname(request.getParameter("fullname"));
            item.setUserPhone(request.getParameter("phone"));
            item.setUserEmail(request.getParameter("email"));
            item.setUserAddress(request.getParameter("address"));
            item.setUserBirthday(Date.valueOf(request.getParameter("birthday")));
            item.setUserGender(request.getParameter("gender"));
            item.setUserNote(request.getParameter("note"));
            item.setUserRole(Integer.parseInt(request.getParameter("role")));

            boolean success = userModel.editUserWithoutPassword(item);

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print("{\"success\": " + success + "}");
            out.flush();
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error editing user without password");
        }
    }

    private void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            UserObject user = userModel.getUserObject(userId);

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();

            if (user != null) {
                out.print("{");
                out.print("\"userId\": " + user.getUserId() + ", ");
                out.print("\"username\": \"" + user.getUserUsername() + "\", ");
                out.print("\"fullname\": \"" + user.getUserFullname() + "\", ");
                out.print("\"phone\": \"" + user.getUserPhone() + "\", ");
                out.print("\"email\": \"" + user.getUserEmail() + "\", ");
                out.print("\"address\": \"" + user.getUserAddress() + "\", ");
                out.print("\"birthday\": \"" + user.getUserBirthday() + "\", ");
                out.print("\"gender\": \"" + user.getUserGender() + "\", ");
                out.print("\"note\": \"" + user.getUserNote() + "\", ");
                out.print("\"role\": " + user.getUserRole() + ", ");
                out.print("\"createdAt\": \"" + user.getUserCreatedAt() + "\", ");
                out.print("\"updatedAt\": \"" + user.getUserUpdatedAt() + "\"");
                out.print("}");
            } else {
                out.print("{\"error\": \"User not found\"}");
            }
            out.flush();
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving user");
        }
    }


    private void getUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            short page = Short.parseShort(request.getParameter("page"));
            byte total = Byte.parseByte(request.getParameter("total"));

            UserObject similar = new UserObject();
            similar.setUserUsername(username); // Filter by username if provided

            Pair<ArrayList<UserObject>, Integer> result = userModel.getUserObjects(similar, page, total);
            ArrayList<UserObject> users = result.getValue0();  // List of users
            Integer all = result.getValue1();  // Total count of users

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();

            // Start building the JSON response
            StringBuilder jsonResponse = new StringBuilder();
            jsonResponse.append("{");
            jsonResponse.append("\"users\": [");

            // Loop through users and add each user to the JSON array
            for (int i = 0; i < users.size(); i++) {
                UserObject user = users.get(i);
                jsonResponse.append("{");
                jsonResponse.append("\"userId\": ").append(user.getUserId()).append(",");
                jsonResponse.append("\"username\": \"").append(user.getUserUsername()).append("\",");
                jsonResponse.append("\"fullname\": \"").append(user.getUserFullname()).append("\",");
                jsonResponse.append("\"phone\": \"").append(user.getUserPhone()).append("\",");
                jsonResponse.append("\"email\": \"").append(user.getUserEmail()).append("\",");
                jsonResponse.append("\"address\": \"").append(user.getUserAddress()).append("\",");
                jsonResponse.append("\"birthday\": \"").append(user.getUserBirthday()).append("\",");
                jsonResponse.append("\"gender\": \"").append(user.getUserGender()).append("\",");
                jsonResponse.append("\"note\": \"").append(user.getUserNote()).append("\",");
                jsonResponse.append("\"role\": ").append(user.getUserRole()).append(",");
                jsonResponse.append("\"createdAt\": \"").append(user.getUserCreatedAt()).append("\",");
                jsonResponse.append("\"updatedAt\": \"").append(user.getUserUpdatedAt()).append("\"");
                jsonResponse.append("}");

                if (i < users.size() - 1) {
                    jsonResponse.append(","); // Add comma if not last user
                }
            }

            jsonResponse.append("],");
            jsonResponse.append("\"total\": ").append(all);
            jsonResponse.append("}");

            // Output the JSON response
            out.print(jsonResponse.toString());
            out.flush();
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving users");
        }
    }


    @SuppressWarnings("unused")
	private void delUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            boolean success = userModel.delUser(userId);

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print("{\"success\": " + success + "}");
            out.flush();
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting user");
        }
    }
}
