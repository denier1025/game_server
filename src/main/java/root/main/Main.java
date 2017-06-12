package root.main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import root.base.VFS;
import root.VFS.VFSImpl;
import root.base.MessageSystem;
import root.base.ServiceContext;
import root.GameSession.equipments.Hummer;
import root.messageSystem.MessageSystemImpl;
import root.accountService.AccountServiceImpl;
import root.frontend.FrontendImpl;
import root.index_html.Index;
import root.base.Resource;
import root.resource.ResourceFactory;
import root.utils.TimerService;

/**
 * Created by Alexey on 04.06.2017.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        MessageSystem messageSystem = new MessageSystemImpl();
        TimerService timerService = TimerService.getInstance();
        Resource resource = ResourceFactory.getInstance();

        ServiceContext serviceContext = new ServiceContext();
        serviceContext.addService(messageSystem.getClass(), messageSystem);
        serviceContext.addService(timerService.getClass(), timerService);
        serviceContext.addService(resource.getClass(), resource);

        FrontendImpl frontend = new FrontendImpl(serviceContext);
        AccountServiceImpl accountService = new AccountServiceImpl(serviceContext);

        new Thread(frontend).start();
        new Thread(accountService).start();

        HandlerCollection handlerCollection = new HandlerCollection();
        handlerCollection.setHandlers(new Handler[] {new Index(), frontend});

        Server server = new Server(8080);
        server.setHandler(handlerCollection);

        server.start();
        server.join();
    }

}
