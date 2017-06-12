package root.messageSystem;

import root.base.Abonent;
import root.base.Address;
import root.base.AddressService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexey on 05.06.2017.
 */
public class AddressServiceImpl implements AddressService {

    private Map<Class<?>, Address> addresses = new HashMap<>();

    @Override
    public Address getAddress(Class<?> abonentClass) {
        return addresses.get(abonentClass);
    }

    @Override
    public void setAddress(Abonent abonent) {
        addresses.put(abonent.getClass(), abonent.getAddress());
    }

}
