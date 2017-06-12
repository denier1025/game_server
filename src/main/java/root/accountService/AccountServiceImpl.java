package root.accountService;

import root.base.ServiceContext;
import root.base.AccountService;
import root.base.Address;
import root.base.MessageSystem;
import root.messageSystem.MessageSystemImpl;
import root.utils.SleepHelper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Alexey on 05.06.2017.
 */
public class AccountServiceImpl implements AccountService, Runnable {

    private static final long TICK_TIME = 100;
    private Address address;
    private final MessageSystem messageSystem;

    private AtomicInteger atomicInteger = new AtomicInteger(100);
    private Map<String, Integer> userTable = new ConcurrentHashMap<>();

    public AccountServiceImpl(ServiceContext serviceContext) {
        MessageSystem messageSystem = (MessageSystem) serviceContext.getService(MessageSystemImpl.class);
        this.messageSystem = messageSystem;
        this.address = new Address();
        messageSystem.addService(this);
    }

    @Override
    public void run() {
        while(true) {
            long startTime = System.currentTimeMillis();
            getMessageSystem().execForAbonent(this);
            long deltaTime = System.currentTimeMillis() - startTime;
//            System.out.println("AccountService loading: " + deltaTime);
            if(deltaTime < TICK_TIME) {
                SleepHelper.sleep(TICK_TIME - deltaTime);
            }
        }
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageSystem getMessageSystem() {
        return messageSystem;
    }

    @Override
    public Integer getUserId(String name) {
        Integer userId = userTable.get(name);
        if(userId == null) {
            int generatedUserId = atomicInteger.incrementAndGet();
            userTable.put(name, generatedUserId);
            return generatedUserId;
        } else {
            return userId;
        }
    }

}
