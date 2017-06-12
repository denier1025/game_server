package root.base;

/**
 * Created by Alexey on 07.06.2017.
 */
public interface AccountService extends Abonent {

    MessageSystem getMessageSystem();

    Integer getUserId(String name);

}
