package root.base;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Alexey on 05.06.2017.
 */
public class Address {

    static private AtomicInteger abonentIdCreator = new AtomicInteger();
    final private int abonentId;

    public Address(){
        this.abonentId = abonentIdCreator.incrementAndGet();
    }

    public int getAbonentId() {
        return abonentId;
    }

}
