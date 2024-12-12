package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javatuples.Pair;

import room.RoomModel;
import objects.RoomObject;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@WebServlet("/roomapi")
public class RoomApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RoomModel roomModel = new RoomModel();
    
    public RoomApi() {
        super();
       
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String pageParameter = request.getParameter("page");
        short currentPage = (pageParameter != null && !pageParameter.isEmpty())
                ? Short.parseShort(pageParameter)
                : 1;
        if (currentPage < 1) currentPage = 1;

        byte totalPerPage = 6;

        RoomModel roomModel = new RoomModel();
        Pair<ArrayList<RoomObject>, Integer> returnSet = roomModel.getRoomObjects(new RoomObject(), currentPage, totalPerPage);
        ArrayList<RoomObject> itemsOfCurrentPage = returnSet.getValue0();
        Integer totalItems = returnSet.getValue1();
        int totalPages = (int) Math.ceil((double) totalItems / totalPerPage);

        // Prepare response data
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("itemsOfCurrentPage", itemsOfCurrentPage);
        responseData.put("totalItems", totalItems);
        responseData.put("totalPages", totalPages);
        responseData.put("currentPage", currentPage);

        // Set response headers
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Convert data to JSON using Gson
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(RoomObject.class, new JsonSerializer<RoomObject>() {
                    @Override
                    public JsonElement serialize(RoomObject room, Type typeOfSrc, JsonSerializationContext context) {
                        JsonObject json = new JsonObject();
                        json.addProperty("roomId", room.getRoomId());
                        json.addProperty("roomName", room.getRoomName());
                        json.addProperty("roomImage", room.getRoomImageBase64());
                        // Add other properties
                        json.addProperty("roomSize", room.getRoomSize());
                        json.addProperty("roomBedCount", room.getRoomBedCount());
                        json.addProperty("roomStarCount", room.getRoomStarCount());
                        json.addProperty("roomPricePerHourVnd", room.getRoomPricePerHourVnd());
                        json.addProperty("roomIsAvailable", room.isRoomIsAvailable());
                        json.addProperty("roomNote", room.getRoomNote());
                        json.addProperty("roomCreatedAt", room.getRoomCreatedAt().toString());
                        json.addProperty("roomUpdatedAt", room.getRoomUpdatedAt().toString());
                        return json;
                    }
                })
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .create();


        String jsonResponse = gson.toJson(responseData);

        // Write JSON to the response
        response.getWriter().write(jsonResponse);
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
