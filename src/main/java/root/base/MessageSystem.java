package root.base;

/**
 * Created by Alexey on 07.06.2017.
 */
public interface MessageSystem {

    void addService(Abonent abonent);

    void sendMessage(Msg message);

    void execForAbonent(Abonent abonent);

    AddressService getAddressService();

}
