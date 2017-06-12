package root.main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import root.VFS.VFS;
import root.VFS.VFSImpl;
import root.base.MessageSystem;
import root.base.ServiceContext;
import root.items.Hummer;
import root.messageSystem.MessageSystemImpl;
import root.accountService.AccountServiceImpl;
import root.frontend.FrontendImpl;
import root.index_html.Index;
import root.resourceSystem.ResourceSystem;
import root.utils.ReflectionHelper;
import root.utils.TimerService;

/**
 * Created by Alexey on 04.06.2017.
 */
public class Main {

    public static void main(String[] args) throws Exception {

//        Object instance = ReflectionHelper.createInstance("root.items.Hummer");
//        System.out.println(instance);
//        ReflectionHelper.setFieldValue(instance, "id", "3");
//        ReflectionHelper.setFieldValue(instance, "name", "Alex");
//        ReflectionHelper.setFieldValue(instance, "strength", "555");
//        System.out.println(instance);

        ResourceSystem resourceSystem = new ResourceSystem();
        Hummer hummer = (Hummer) resourceSystem.workWithSAX("src/main/resources/items/Hummer.xml");
        System.out.println(hummer);

        MessageSystem messageSystem = new MessageSystemImpl();
        TimerService timerService = TimerService.getInstance();
        VFS vfs = new VFSImpl("");

        ServiceContext serviceContext = new ServiceContext();
        serviceContext.addService(messageSystem.getClass(), messageSystem);
        serviceContext.addService(timerService.getClass(), timerService);
        serviceContext.addService(vfs.getClass(), vfs);

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
