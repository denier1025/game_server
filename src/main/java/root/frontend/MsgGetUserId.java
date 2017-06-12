package root.frontend;

import root.base.AccountService;
import root.base.Address;
import root.messageSystem.MsgToAccountService;

/**
 * Created by Alexey on 05.06.2017.
 */
public class MsgGetUserId extends MsgToAccountService {

    private String name;

    public MsgGetUserId(Address from, Address to, String name) {
        super(from, to);
        this.name = name;
    }

    @Override
    public void exec(AccountService accountService) {
        Integer id = accountService.getUserId(name);
        accountService.getMessageSystem().sendMessage(new MsgUpdateUserId(getTo(), getFrom(), name, id));
    }

}
