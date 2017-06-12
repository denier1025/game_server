package root.base;

/**
 * Created by Alexey on 07.06.2017.
 */
public interface Frontend extends Abonent {

    MessageSystem getMessageSystem();

    void setId(String name, int userId);

}
