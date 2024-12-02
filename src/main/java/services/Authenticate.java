package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Authenticate {
	public static boolean isLoggedInAsAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
//		System.out.println(session);
        if(session == null) return false; // doesnt even have session => not logged in.
        
        Object admin_username = session.getAttribute("admin_username");
//        System.out.println(admin_username);
        if(!(admin_username instanceof String)) return false; // invalid 
        
        return true;
	}
	
	public static boolean clearSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return true;
	}
	
	public static Integer getAdminId(HttpServletRequest request) {
	    HttpSession session = request.getSession(false); 
	    if (session == null) return null;
	    
	    Object object = session.getAttribute("admin_id");
	    if (object instanceof Integer) {
	        return (Integer) object;
	    } else {
	        return null;
	    }
	}

}
