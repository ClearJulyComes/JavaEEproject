package Controllers;

import DBMethods.GetMessagesClass;
import DBMethods.MessagesStore;
import Entities.MessageEntity;
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

@Path("messages")
public class MessagesController {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(MessagesController.class);

    @GET
    @Path("/get")
    public void getMessages(@Context HttpServletRequest request,
                            @Context HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String sessionLogin = (String) session.getAttribute("userLogin");
        GetMessagesClass getMessagesClass = new GetMessagesClass(sessionLogin);
        List<MessageEntity> myMessages = getMessagesClass.getMessagesMethod();
        logger.warn(myMessages.size());
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonString = ow.writeValueAsString(myMessages);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    @POST
    @Path("/set")
    public void setMessage (@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
        response.setContentType("text;charset=UTF-8");
        HttpSession session = request.getSession();
        String sessionLogin = (String) session.getAttribute("userLogin");
        MessagesStore messagesStore = new MessagesStore(sessionLogin, request.getParameter("to"),
                request.getParameter("message"));
        messagesStore.saveMessage();
        PrintWriter writer = response.getWriter();
        writer.println(messagesStore.getId());
        writer.flush();
        writer.close();
    }
}
