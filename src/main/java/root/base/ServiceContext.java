package root.base;

import root.base.AccountService;
import root.frontend.FrontendImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexey on 10.06.2017.
 */
public class ServiceContext{

    private Map<Class, Object> context = new HashMap<>();

    public void addService(Class clazz, Object obj) {
        if(context.containsKey(obj)) {
            return;
        }
        context.put(clazz, obj);
    }

    public Object getService(Class clazz) {
        return context.get(clazz);
    }

}
