package Controllers;

import Entities.Login;
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

/**
 * Контроллер для обработки HTTP запросов по получению списка друзей и удалению друга.
 */
@Path("/friend")
public class FriendController {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(FriendController.class);

    /**
     * Метод для обработки POST запроса по удалению друзей. Вызывает метод {@link DeleteFriend#deleteUserFriend()} для
     * удаления из БД.
     * @param request данные с клиента,
     * @param response ответ сервера.
     */
    @POST
    @Path("/delete")
    public void deleteFriend (@Context HttpServletRequest request, @Context HttpServletResponse response) {
        response.setContentType("text;charset=UTF-8");
        HttpSession session = request.getSession();
        String sessionLogin = (String) session.getAttribute("userLogin");
        DeleteFriend friendship = new DeleteFriend(sessionLogin, request.getParameter("userLogin"));
        friendship.deleteUserFriend();
    }

    /**
     * Метод для GET запроса для получения списка друзей.
     * @param request данные с клиента
     * @param response ответ сервера
     * @throws IOException исключение
     */
    @GET
    @Path("/get")
    public void sendFriendList(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String sessionLogin = (String) session.getAttribute("userLogin");
        FriendList friendList = new FriendList(sessionLogin);
        List<Login> myFriendsList = friendList.getFriendList();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonString = ow.writeValueAsString(myFriendsList);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

}
