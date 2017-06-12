package root.messageSystem;

import root.base.Abonent;
import root.base.AccountService;
import root.base.Address;
import root.base.Msg;

/**
 * Created by Alexey on 05.06.2017.
 */
public abstract class MsgToAccountService extends Msg {

    public void exec(Abonent abonent) {
        if(abonent instanceof AccountService) {
            exec((AccountService) abonent);
        }
    }

    public MsgToAccountService(Address from, Address to) {
        super(from, to);
    }

    public abstract void exec(AccountService accountService);

}
