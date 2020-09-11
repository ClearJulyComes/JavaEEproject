package Controllers;

import Entities.Friends;
import Entities.Login;
import Entities.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
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
        if (!sessionLogin.equals(request.getParameter("login"))) {
            AddFriend friendship = new AddFriend(request.getParameter("login"), sessionLogin);
            friendship.addMethod();
        }else {
            response.sendError(500);
        }
    };

    @POST
    @Path("/delete")
    public void deleteFriend (@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
        response.setContentType("text;charset=UTF-8");
        HttpSession session = request.getSession();
        String sessionLogin = (String) session.getAttribute("userLogin");
        DeleteFriend friendship = new DeleteFriend(sessionLogin, request.getParameter("userLogin"));
        friendship.deleteUserFriend();
    };

    @GET
    @Path("/get")
    public void sendFriendList(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String sessionLogin = (String) session.getAttribute("userLogin");
        FriendList friendList = new FriendList(sessionLogin);
        //List<UserInfo> myFriendsList = friendList.getFriendList();
        List<Login> myFriendsList = friendList.getFriendList();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonString = ow.writeValueAsString(myFriendsList);
        //String jsonString = new JSONObject().toString();
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

}
