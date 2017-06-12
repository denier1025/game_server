package root.base;

/**
 * Created by Alexey on 07.06.2017.
 */
public interface AddressService {

    Address getAddress(Class<?> abonentClass);

    void setAddress(Abonent abonent);

}
