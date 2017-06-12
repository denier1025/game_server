package root.frontend;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import root.base.ServiceContext;
import root.accountService.AccountServiceImpl;
import root.base.Address;
import root.base.Frontend;
import root.base.MessageSystem;
import root.messageSystem.MessageSystemImpl;
import root.utils.SleepHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Alexey on 05.06.2017.
 */
public class FrontendImpl extends AbstractHandler implements Frontend, Runnable {

    private static String GAME_NAME = "/test/";
    private static final long TICK_TIME = 100;
    private Address address;
    private final MessageSystem messageSystem;

    private Map<String, Integer> nameToId = new ConcurrentHashMap<>();

    public FrontendImpl(ServiceContext serviceContext) {
        MessageSystem messageSystem = (MessageSystem) serviceContext.getService(MessageSystemImpl.class);
        this.messageSystem = messageSystem;
        this.address = new Address();
        this.messageSystem.addService(this);
    }

    @Override
    public void run() {
        while(true) {
            long startTime = System.currentTimeMillis();
            getMessageSystem().execForAbonent(this);
            long deltaTime = System.currentTimeMillis() - startTime;
//            System.out.println("Frontend loading: " + deltaTime);
//            System.out.println("Users online: " + nameToId.size());
//            System.out.println("Free memory: " + Runtime.getRuntime().freeMemory());
//            System.out.println("Max accessible memory: " + Runtime.getRuntime().totalMemory());
//            System.out.println("Free memory: " + Runtime.getRuntime().maxMemory());
            if(deltaTime < TICK_TIME) {
                SleepHelper.sleep(TICK_TIME - deltaTime);
            }
        }
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");

        boolean flag = !target.equals(GAME_NAME);
        if(flag) {
            return;
        }

        String username = request.getParameter("username");
        Integer userId = nameToId.get(username);
        PrintWriter printWriter = response.getWriter();
        if(userId != null) {
            printWriter.println("<html>" +
                    "<head><title></title><META HTTP-EQUIV=\"refresh\" CONTENT=\"1\"></head>" +
                    "<body>" +
                    "<h1>Username: " + username + "; Id: " + userId + "</h1>" +
                    "</body>" +
                    "</html>");
        } else {
            printWriter.println("<html>" +
                    "<head><title></title><META HTTP-EQUIV=\"refresh\" CONTENT=\"1\"></head>" +
                    "<body>" +
                    "<h1>Wait for authorization</h1>" +
                    "</body>" +
                    "</html>");
            Address addressAS = getMessageSystem().getAddressService().getAddress(AccountServiceImpl.class);
            getMessageSystem().sendMessage(new MsgGetUserId(getAddress(), addressAS, username));
        }

        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public void setId(String username, int userId) {
        nameToId.put(username, userId);
    }

    @Override
    public MessageSystem getMessageSystem() {
        return messageSystem;
    }

}
