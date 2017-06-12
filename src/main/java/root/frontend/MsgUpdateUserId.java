package root.frontend;

import root.base.Address;
import root.base.Frontend;
import root.messageSystem.MsgToFrontend;

/**
 * Created by Alexey on 05.06.2017.
 */
public class MsgUpdateUserId extends MsgToFrontend {

    final private String username;
    final int userId;

    public MsgUpdateUserId(Address from, Address to, String username, int userId) {
        super(from, to);
        this.username = username;
        this.userId = userId;
    }

    @Override
    public void exec(Frontend frontend) {
        frontend.setId(username, userId);
    }

}
