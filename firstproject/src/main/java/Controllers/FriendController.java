package Controllers;

import Entities.Friends;
import Entities.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import DBMethods.*;
import org.json.JSONObject;

@Path("/friend")
public class FriendController {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(FriendController.class);

    @POST
    @Path("/add")
    public void addFriend (@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
        response.setContentType("text;charset=UTF-8");
        HttpSession session = request.getSession();
        String sessionLogin = (String) session.getAttribute("userLogin");
        AddFriend friendship = new AddFriend(request.getParameter("login"), sessionLogin);
        friendship.addMethod();
    };

   /* @GET
    @Path("/get")
    public void sendFriendList(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String sessionLogin = (String) session.getAttribute("userLogin");
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String jsonString = new JSONObject()
                .put("friendshipId", "6")
                .put("hisFriend", "Kity")
                .put("userLogin", "me")
                .toString();
        out.print(jsonString);
        out.flush();
    } */
    @GET
    @Path("/get")
    public void sendFriendList2(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String sessionLogin = (String) session.getAttribute("userLogin");
        FriendList friendList = new FriendList(sessionLogin);
        List<UserInfo> myFriendsList = friendList.getFriendList();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonString = ow.writeValueAsString(myFriendsList);
        PrintWriter out = response.getWriter();
        logger.warn("OOOOOOOOOOOOOOOOOOOOOOOO " +jsonString);
        out.print(jsonString);
        out.flush();
    }
}
