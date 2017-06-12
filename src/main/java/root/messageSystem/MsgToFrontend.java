package root.messageSystem;

import root.base.Abonent;
import root.base.Address;
import root.base.Frontend;
import root.base.Msg;

/**
 * Created by Alexey on 05.06.2017.
 */
public abstract class MsgToFrontend extends Msg {

    public MsgToFrontend(Address from, Address to) {
        super(from, to);
    }

    public void exec(Abonent abonent) {
        if(abonent instanceof Frontend) {
            exec((Frontend) abonent);
        }
    }

    public abstract void exec(Frontend frontend);

}
